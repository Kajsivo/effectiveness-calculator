package com.kajs.domain;

import com.google.common.io.Files;
import com.kajs.api.error.WrongExtensionException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CalculateService {
    public String calculateEffectiveness(MultipartFile globalEffectivenes, MultipartFile singleEffectiveness){

        validateFile(globalEffectivenes);
        validateFile(singleEffectiveness);
        Optional<Sheet> firstGlobalSheet = Optional.ofNullable(prepareSheet(globalEffectivenes, 0));
        Optional<Sheet> firstSingleSheet = Optional.ofNullable(prepareSheet(singleEffectiveness, 0));



        return "";
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
