package com.kajs.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.kajs.domain.CalculateService;

@Controller
@RequestMapping("/")
public class EffectivenessController {

    private final CalculateService calculateService;

    @Autowired
    public EffectivenessController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.GET)
    public String calculateTemplate() {
        return "calculateTemplate";
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public String getFinalFile(Model model,
                               @RequestParam("globalFile") MultipartFile globalFile) {

        String result = calculateService.calculateEffectiveness(globalFile);

        model.addAttribute(
                "link", result);
        return "getFinalFile";
    }
}
