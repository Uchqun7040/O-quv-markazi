package uz.jjp.O.quv.markazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.jjp.O.quv.markazi.entity.Oquvchi;
import uz.jjp.O.quv.markazi.entity.Search;
import uz.jjp.O.quv.markazi.service.GuruhService;
import uz.jjp.O.quv.markazi.service.OquvchiService;
import uz.jjp.O.quv.markazi.service.SessiyaService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/oquvchilar")
public class OquvchiController {
    @Autowired
    OquvchiService oquvchiService;

    @Autowired
    SessiyaService sessiyaService;

    @Autowired
    GuruhService guruhService;

    @GetMapping()
    public String royxat(Model model) throws IOException {
        model.addAttribute("oquvchilar",oquvchiService.getAll());
        return "oquvchi";
    }

    @PostMapping("/izla")
    public String izla(Search s, Model model){
        model.addAttribute("oquvchilar",oquvchiService.izla(s.getSatr()));
        return "oquvchi";
    }

    @PostMapping()
    public void yarat(Oquvchi o, HttpServletResponse hsr) throws IOException {
        oquvchiService.create(o);
        hsr.sendRedirect("/oquvchilar");
    }

    @GetMapping("/ochirish/{id}")
    public void ochirish(@PathVariable Long id, HttpServletResponse hsr) throws IOException {
        oquvchiService.delete(id);
        hsr.sendRedirect("/oquvchilar");
    }

    @GetMapping("/edit/{id}")
    public String ozgartiriluvchi(@PathVariable Long id,Model model,HttpServletResponse hsr) throws IOException {
        Oquvchi o=oquvchiService.getById(id);
        model.addAttribute("oquvchi",o);
        model.addAttribute("sessiyalar",sessiyaService.getByOquvchiId(id));
        model.addAttribute("guruhlar",guruhService.getAll());
        return royxat(model);
    }

    @PostMapping("/edit")
    public void ozgartirish(Oquvchi o,HttpServletResponse hsr) throws IOException {
        oquvchiService.update(o);
        hsr.sendRedirect("/oquvchilar");
    }

}