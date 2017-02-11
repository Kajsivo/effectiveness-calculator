package com.kajs.domain;

import com.kajs.domain.model.Effectiveness;
import com.kajs.domain.repository.EffectivenessRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.util.List;

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
            List<Effectiveness> effectivenessList = effectivenessRepository.getByMonthAndYear(month, year);
            FileOutputStream fos = new FileOutputStream("temp/report_" + month + "_" + year + ".xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Skutecznosc za okres " + month + " " + year);
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Domena");
            header.createCell(1).setCellValue("Kwota FV");
            header.createCell(2).setCellValue("Barykada");
            header.createCell(3).setCellValue("Opiekun");
            header.createCell(4).setCellValue("Czy dluznik");
            header.createCell(5).setCellValue("Skutecznosc");

            for(int i = 0; i < effectivenessList.size(); i++) {
                Row row = sheet.createRow(i+1);
                row.createCell(0).setCellValue(effectivenessList.get(i).getDomain());
                row.createCell(1).setCellValue(effectivenessList.get(i).getAmount());
                row.createCell(2).setCellValue(effectivenessList.get(i).getMax());
                row.createCell(3).setCellValue(effectivenessList.get(i).getKeeper());
                if(effectivenessList.get(i).isDebtor()) {
                    row.createCell(4).setCellValue("1");
                }
                row.createCell(5).setCellValue(effectivenessList.get(i).getEffectiveness());
            }
            workbook.write(fos);
        } catch (Exception e) {
            logger.info("Exception during saving file: ", e);
        }
    }
}
