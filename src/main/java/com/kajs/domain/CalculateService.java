package com.kajs.domain;

import com.google.common.io.Files;
import com.kajs.api.error.SheetNotFoundException;
import com.kajs.api.error.WrongCellTypeException;
import com.kajs.api.error.WrongExtensionException;
import com.kajs.domain.model.Effectiveness;
import com.kajs.domain.repository.EffectivenessRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
public class CalculateService extends ExcelFileService{
    private static final Logger logger = LoggerFactory.getLogger(CalculateService.class);
    private final EffectivenessRepository effectivenessRepository;

    @Autowired
    public CalculateService(EffectivenessRepository effectivenessRepository) {
        this.effectivenessRepository = effectivenessRepository;
    }

    public String calculateEffectiveness(MultipartFile globalEffectiveness){
        logger.info("Start processing file");
        validateFile(globalEffectiveness);
        Optional<Sheet> firstGlobalSheet = Optional.ofNullable(prepareSheet(globalEffectiveness, 0));

        if(firstGlobalSheet.isPresent() ) {
            logger.info("Start processing first sheet");
            Sheet globalSheet = firstGlobalSheet.get();
            List<Effectiveness> domainList = new ArrayList<>();

            globalSheet.rowIterator().forEachRemaining((row) -> {
                Effectiveness effectiveness = new Effectiveness();
                effectiveness.setDomain(getCell(row, 0));
                effectiveness.setAmount(getCell(row, 1));
                effectiveness.setMax(getCell(row, 2));
                effectiveness.setEffectiveness(getCell(row, 3));
                effectiveness.setKeeper(getCell(row, 4));
                domainList.add(effectiveness);
            });
            domainList.forEach(effectivenessRepository::save);
            return domainList.get(0).toString();
        } else {
            throw new SheetNotFoundException();
        }
    }
}
