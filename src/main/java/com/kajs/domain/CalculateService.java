package com.kajs.domain;

import com.google.common.io.Files;
import com.kajs.api.error.SheetNotFoundException;
import com.kajs.api.error.WrongCellTypeException;
import com.kajs.api.error.WrongExtensionException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

@Service
public class CalculateService {
    public String calculateEffectiveness(MultipartFile globalEffectivenes, MultipartFile singleEffectiveness){

        validateFile(globalEffectivenes);
        validateFile(singleEffectiveness);
        Optional<Sheet> firstGlobalSheet = Optional.ofNullable(prepareSheet(globalEffectivenes, 0));
        Optional<Sheet> firstSingleSheet = Optional.ofNullable(prepareSheet(singleEffectiveness, 0));

        if(firstGlobalSheet.isPresent() && firstSingleSheet.isPresent()) {
            Sheet globalSheet = firstGlobalSheet.get();
            Sheet singleSheet = firstSingleSheet.get();


            globalSheet.rowIterator().forEachRemaining((row) -> {
                String firstGlobalCell = getCell(row, 0);
            });

        } else {
            throw new SheetNotFoundException();
        }
        return "";
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
