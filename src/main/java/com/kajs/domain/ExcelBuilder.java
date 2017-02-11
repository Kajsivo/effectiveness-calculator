package com.kajs.domain;

import com.kajs.domain.repository.EffectivenessRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;

@Component
public class ExcelBuilder {
    private static final Logger logger = LoggerFactory.getLogger(ExcelBuilder.class);

    private EffectivenessRepository effectivenessRepository;

    @Autowired
    public ExcelBuilder(EffectivenessRepository effectivenessRepository) {
        this.effectivenessRepository = effectivenessRepository;
    }

    public void writeExcelFile(String month, String year) {
        try {
            effectivenessRepository.getByMonthAndYear(month, year);
            FileOutputStream fos = new FileOutputStream("temp/report_" + month + "_" + year + ".xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("tescior");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("1s3tcell");
            workbook.write(fos);
        } catch (Exception e) {
            logger.info("Exception during saving file: ", e);
        }
    }
}
