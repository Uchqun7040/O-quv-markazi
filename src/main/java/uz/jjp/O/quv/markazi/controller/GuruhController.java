package uz.jjp.O.quv.markazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.jjp.O.quv.markazi.entity.Guruh;
import uz.jjp.O.quv.markazi.entity.Search;
import uz.jjp.O.quv.markazi.service.*;

import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/guruhlar")
public class GuruhController {
    @Autowired
    GuruhService guruhService;

    @Autowired
    OqituvchiService oqituvchiService;

    @Autowired
    SessiyaService sessiyaService;

    @Autowired
    FanService fanService;

    @GetMapping()
    public String royxat(Model model) throws IOException {
        model.addAttribute("guruhlar",guruhService.getAll());
        model.addAttribute("oqituvchilar",oqituvchiService.getAll());
        model.addAttribute("fanlar",fanService.getAll());
        return "guruh";
    }

    @PostMapping("/izla")
    public String izla(Search s, Model model){
        model.addAttribute("guruhlar",guruhService.izla(s.getSatr()));
        return "guruh";
    }

    @PostMapping()
    public void yarat(Guruh o, HttpServletResponse hsr) throws IOException {
        try {
            guruhService.create(o);
        }catch (Exception ignored){
            System.err.println(LocalDateTime.now()+" : Mavjud guruh nomi kiritildi! ");
        }
        hsr.sendRedirect("/guruhlar");
    }

    @GetMapping("/ochirish/{id}")
    public void ochirish(@PathVariable Long id, HttpServletResponse hsr) throws IOException {
        guruhService.delete(id);
        hsr.sendRedirect("/guruhlar");
    }


    @PostMapping("/edit")
    public String ozgartirish(Guruh o,Model model) throws IOException {

        try {
            guruhService.update(o);
        }catch (Exception ignored){
            System.err.println(LocalDateTime.now()+" : Mavjud guruh nomi kiritildi! ");
        }
        return onguruh(o.getId(),model);
    }
    @GetMapping("/onguruh/{id}")
    public String onguruh(@PathVariable Long id,Model model){
        model.addAttribute("sessiyalar",sessiyaService.getAllByGuruhId(id));
        model.addAttribute("oqituvchilar",oqituvchiService.getAll());
        model.addAttribute("guruh",guruhService.getById(id));
        return "onguruh";
    }
    @GetMapping("/ochirishSessiya/{id}")
    public String ochirishY(@PathVariable Long id,Model model) throws IOException {
        sessiyaService.delete(id);
        return onguruh(sessiyaService.getById(id).getGuruh().getId(),model);
    }
}