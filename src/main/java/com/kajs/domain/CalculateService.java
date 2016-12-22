package com.kajs.domain;

import com.google.common.io.Files;
import com.kajs.api.error.SheetNotFoundException;
import com.kajs.api.error.WrongCellTypeException;
import com.kajs.api.error.WrongExtensionException;
import com.kajs.domain.model.DomainEffectiveness;
import com.kajs.domain.repository.DomainRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

@Component
public class CalculateService {
    private final DomainRepository domainRepository;

    @Autowired
    public CalculateService(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    public String calculateEffectiveness(MultipartFile globalEffectiveness){
        validateFile(globalEffectiveness);
        Optional<Sheet> firstGlobalSheet = Optional.ofNullable(prepareSheet(globalEffectiveness, 0));

        if(firstGlobalSheet.isPresent() ) {
            Sheet globalSheet = firstGlobalSheet.get();
            List<DomainEffectiveness> domainList = new ArrayList<>();

            globalSheet.rowIterator().forEachRemaining((row) -> {
                DomainEffectiveness domainEffectiveness = new DomainEffectiveness();
                domainEffectiveness.setDomain(getCell(row, 0));
                domainEffectiveness.setAmount(getCell(row, 1));
                domainEffectiveness.setMax(getCell(row, 2));
                domainEffectiveness.setEffectiveness(getCell(row, 3));
                domainEffectiveness.setKeeper(getCell(row, 4));

                domainList.add(domainEffectiveness);
            });

            domainList.forEach(domainRepository::save);
            return domainList.get(0).toString();
        } else {
            throw new SheetNotFoundException();
        }
    }

    private String getCell(Row row, int cellNumber) {
        Optional<Cell> optionalCell = Optional.ofNullable(row.getCell(cellNumber));
        if(optionalCell.isPresent()) {
            Cell cell = optionalCell.get();
            try {
                switch (cell.getCellType()) {
                    case CELL_TYPE_NUMERIC:
                        return String.valueOf(cell.getNumericCellValue());
                    case CELL_TYPE_FORMULA:
                        return String.valueOf(cell.getNumericCellValue());
                    case CELL_TYPE_STRING:
                        return cell.getStringCellValue();
                }
            } catch (Exception ignored) {
            }
        }
        throw new WrongCellTypeException();
    }

    private Sheet prepareSheet(MultipartFile file, int sheetNumber) {

        try{
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(sheetNumber);

            sheet.removeRow(sheet.getRow(sheet.getFirstRowNum()));
            return sheet;
        } catch (Exception e){
            return null;
        }

    }

    private void validateFile(MultipartFile file) {
        List<String> availableExtensions = Arrays.asList("xls", "xlsx");
        String fileExtension = Files.getFileExtension(file.getOriginalFilename());
        if(!availableExtensions.stream().anyMatch(ext -> ext.equals(fileExtension.toLowerCase()))) {
            throw new WrongExtensionException();
        }

    }
}
