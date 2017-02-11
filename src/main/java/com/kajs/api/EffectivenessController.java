package com.kajs.api;

import com.kajs.api.error.InvalidPeriodException;
import com.kajs.domain.DomainService;
import com.kajs.domain.ExcelBuilder;
import com.kajs.domain.repository.EffectivenessRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.kajs.domain.CalculateService;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import java.io.FileInputStream;

import static org.apache.commons.io.IOUtils.copy;

@Controller
@RequestMapping("/")
public class EffectivenessController {

    private static final Logger logger = LoggerFactory.getLogger(EffectivenessController.class);

    private final CalculateService calculateService;

    private final DomainService domainService;

    private final ExcelBuilder excelBuilder;

    private final EffectivenessRepository effectivenessRepository;

    @Autowired
    public EffectivenessController(CalculateService calculateService, DomainService domainService, ExcelBuilder excelBuilder, EffectivenessRepository effectivenessRepository) {
        this.calculateService = calculateService;
        this.domainService = domainService;
        this.excelBuilder = excelBuilder;
        this.effectivenessRepository = effectivenessRepository;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadTemplate(Model model, @QueryParam("success") String success) {
        if (success != null) {
            model.addAttribute("uploadSuccess", true);
        } else {
            model.addAttribute("uploadSuccess", false);
        }
        return "uploadTemplate";
    }

    @RequestMapping(value = "/uploadInvoiceFile", method = RequestMethod.POST)
    public String uploadInvoiceFile(@RequestParam("invoiceFile") MultipartFile invoiceFile,
                                   @RequestParam("month") String month,
                                   @RequestParam("year") String year) {
        if (month == null || year == null) {
            throw new InvalidPeriodException();
        }
        return "redirect:/upload?success";
    }

    @RequestMapping(value = "/uploadKeeperAndDebtorFile", method = RequestMethod.POST)
    public String uploadKeeperAndDebtorFile(@RequestParam("keeperAndDebtorFile") MultipartFile keeperAndDebtorFile,
                                    @RequestParam("month") String month,
                                    @RequestParam("year") String year) {
        if (month == null || year == null) {
            throw new InvalidPeriodException();
        }
        return "redirect:/upload?success";
    }

    @RequestMapping(value = "/uploadStartDateFile", method = RequestMethod.POST)
    public String uploadStartDateFile(@RequestParam("startDateFile") MultipartFile startDateFile) {
        domainService.addStartDateToDomain(startDateFile);
        return "redirect:/upload?success";
    }

    @RequestMapping(value = "/uploadBarricadeFile", method = RequestMethod.POST)
    public String uploadBarricadeFile(@RequestParam("barricadeFile") MultipartFile barricadeFile,
                                   @RequestParam("month") String month,
                                   @RequestParam("year") String year) {
        if (month == null || year == null) {
            throw new InvalidPeriodException();
        }
        return "redirect:/upload?success";
    }

    @RequestMapping(value = "/uploadDomainFile", method = RequestMethod.POST)
    public String uploadDomainFile(@RequestParam("domainFile") MultipartFile domainFile) {
        domainService.saveDomains(domainFile);
        return "redirect:/upload?success";
    }

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public String generateReportTemplate() {
        return "generateReportTemplate";
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void downloadReportTemplate(@RequestParam("month") String month,
                                       @RequestParam("year") String year,
                                       HttpServletResponse response) {
        //TODO: check if file has been generated before if so than not generate again
        excelBuilder.writeExcelFile(month, year);
        String fileName = "report_" + month + "_" + year + ".xlsx";
        try {
            FileInputStream fis = new FileInputStream("temp/" + fileName);
            copy(fis, response.getOutputStream());
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
            response.flushBuffer();
        } catch (Exception e) {
            logger.info("Exception during downloading file", e);
        }
    }
}
