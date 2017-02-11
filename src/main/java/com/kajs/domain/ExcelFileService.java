package com.kajs.domain;

import com.google.common.io.Files;
import com.kajs.api.error.WrongCellTypeException;
import com.kajs.api.error.WrongExtensionException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

public class ExcelFileService {
    private static final Logger logger = LoggerFactory.getLogger(ExcelFileService.class);

    protected String getCell(Row row, int cellNumber) {
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
                    default:
                        return cell.getStringCellValue();
                }
            } catch (Exception ignored) {
                logger.info("exception during get cell number {}", cellNumber, ignored);
            }
        }
        throw new WrongCellTypeException();
    }

    protected Sheet prepareSheet(MultipartFile file, int sheetNumber) {

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

    protected void validateFile(MultipartFile file) {
        List<String> availableExtensions = Arrays.asList("xls", "xlsx");
        String fileExtension = Files.getFileExtension(file.getOriginalFilename());
        if(availableExtensions.stream().noneMatch(ext -> ext.equals(fileExtension.toLowerCase()))) {
            throw new WrongExtensionException();
        }

    }
}
