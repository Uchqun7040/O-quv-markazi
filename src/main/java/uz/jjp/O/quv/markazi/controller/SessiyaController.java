package uz.jjp.O.quv.markazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.jjp.O.quv.markazi.entity.Sessiya;
import uz.jjp.O.quv.markazi.service.GuruhService;
import uz.jjp.O.quv.markazi.service.OquvchiService;
import uz.jjp.O.quv.markazi.service.SessiyaService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/sessiyalar")
public class SessiyaController {
    @Autowired
    GuruhService guruhService;

    @Autowired
    OquvchiService oquvchiService;

    @Autowired
    SessiyaService sessiyaService;


    @GetMapping()
    public String royxat(Model model) throws IOException {
        model.addAttribute("guruhlar",guruhService.getAll());
        model.addAttribute("oquvchilar",oquvchiService.getAll());
        model.addAttribute("sessiyalar",sessiyaService.getAll());
        return "sessiya";
    }

    @GetMapping("/tolanganlar")
    public String tolanganlar( Model model) throws IOException {
        model.addAttribute("guruhlar",guruhService.getAll());
        model.addAttribute("oquvchilar",oquvchiService.getAll());
        model.addAttribute("sessiyalar",sessiyaService.tolovUchun(true));
        return "sessiya";
    }
    @GetMapping("/tolanmaganlar")
    public String tolanmaganlar(Model model) throws IOException {
        model.addAttribute("guruhlar",guruhService.getAll());
        model.addAttribute("oquvchilar",oquvchiService.getAll());
        model.addAttribute("sessiyalar",sessiyaService.tolovUchun(false));
        return "sessiya";
    }


    @PostMapping()
    public void yarat(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Sessiya o, HttpServletResponse hsr) throws IOException {
        sessiyaService.create(o);
        guruhService.guruhlash(o.getGuruh().getId());
        hsr.sendRedirect("/oquvchilar");
    }

    @GetMapping("/ochirish/{id}")
    public void ochirish(@PathVariable Long id, HttpServletResponse hsr) throws IOException {
        guruhService.unguruhlash(sessiyaService.getById(id).getGuruh().getId());
        sessiyaService.delete(id);

        hsr.sendRedirect("/sessiyalar");
    }

    @GetMapping("/edit/{id}")
    public String ozgartiriluvchi(@PathVariable Long id,Model model,HttpServletResponse hsr) throws IOException {
        Sessiya o=sessiyaService.getById(id);
        model.addAttribute("sessiya",o);
        return royxat(model);
    }

    @PostMapping("/edit")
    public void ozgartirish(Sessiya o,HttpServletResponse hsr) throws IOException {
        sessiyaService.update(o);
        hsr.sendRedirect("/sessiyalar");
    }



}