package uz.jjp.O.quv.markazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.jjp.O.quv.markazi.entity.Oqituvchi;
import uz.jjp.O.quv.markazi.entity.Search;
import uz.jjp.O.quv.markazi.service.GuruhService;
import uz.jjp.O.quv.markazi.service.OqituvchiService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/oqituvchilar")
public class OqituvchiController {
    @Autowired
    OqituvchiService oqituvchiService;

    @Autowired
    GuruhService guruhService;

    @GetMapping()
    public String royxat(Model model) throws IOException {
        model.addAttribute("oqituvchilar",oqituvchiService.getAll());
        return "oqituvchi";
    }

    @PostMapping("/izla")
    public String izla(Search s, Model model){
        model.addAttribute("oqituvchilar",oqituvchiService.izla(s.getSatr()));
        return "oqituvchi";
    }

    @PostMapping()
    public void yarat(Oqituvchi o, HttpServletResponse hsr) throws IOException {
        oqituvchiService.create(o);
        hsr.sendRedirect("/oqituvchilar");
    }

    @GetMapping("/ochirish/{id}")
    public void ochirish(@PathVariable Long id, HttpServletResponse hsr) throws IOException {
        oqituvchiService.delete(id);
        hsr.sendRedirect("/oqituvchilar");
    }

    @GetMapping("/edit/{id}")
    public String ozgartiriluvchi(@PathVariable Long id,Model model) throws IOException {
        model.addAttribute("oqituvchi",oqituvchiService.getById(id));
        model.addAttribute("guruhlar",guruhService.getAllByOqituvchi(id));
        return "oqituvchiTahrirlash";
    }

    @PostMapping("/edit")
    public String ozgartirish(Oqituvchi o,Model model) throws IOException {
        oqituvchiService.update(o);
        return ozgartiriluvchi(o.getId(),model);
    }

}