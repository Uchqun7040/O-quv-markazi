package uz.jjp.O.quv.markazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.jjp.O.quv.markazi.entity.Guruh;
import uz.jjp.O.quv.markazi.service.FanService;
import uz.jjp.O.quv.markazi.service.GuruhService;
import uz.jjp.O.quv.markazi.service.OqituvchiService;
import uz.jjp.O.quv.markazi.service.SessiyaService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/guruhlar")
public class GuruhController {
    @Autowired
    GuruhService guruhService;

    @Autowired
    OqituvchiService oqituvchiService;

    @Autowired
    FanService fanService;

    @GetMapping()
    public String royxat(Model model) throws IOException {
        model.addAttribute("guruhlar",guruhService.getAll());
        model.addAttribute("oqituvchilar",oqituvchiService.getAll());
        model.addAttribute("fanlar",fanService.getAll());
        return "guruh";
    }


    @PostMapping()
    public void yarat(Guruh o, HttpServletResponse hsr) throws IOException {
        guruhService.create(o);
        hsr.sendRedirect("/guruhlar");
    }

    @GetMapping("/ochirish/{id}")
    public void ochirish(@PathVariable Long id, HttpServletResponse hsr) throws IOException {
        guruhService.delete(id);
        hsr.sendRedirect("/guruhlar");
    }

    @GetMapping("/edit/{id}")
    public String ozgartiriluvchi(@PathVariable Long id,Model model,HttpServletResponse hsr) throws IOException {
        Guruh o=guruhService.getById(id);
        model.addAttribute("guruh",o);
        return royxat(model);
    }

    @PostMapping("/edit")
    public void ozgartirish(Guruh o,HttpServletResponse hsr) throws IOException {
        guruhService.update(o);
        hsr.sendRedirect("/guruhlar");
    }

}