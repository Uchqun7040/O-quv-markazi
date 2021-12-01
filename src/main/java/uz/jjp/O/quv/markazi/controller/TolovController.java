package uz.jjp.O.quv.markazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.jjp.O.quv.markazi.entity.Tolov;
import uz.jjp.O.quv.markazi.service.GuruhService;
import uz.jjp.O.quv.markazi.service.OquvchiService;
import uz.jjp.O.quv.markazi.service.SessiyaService;
import uz.jjp.O.quv.markazi.service.TolovService;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/tolovlar")
public class TolovController {
    List<String> oylar= Arrays.asList("Yanvar", "Fevral","Mart","Aprel","May","Iyun","Iyul","Avgust","Sentabr","Oktabr","Noyabr","Dekabr");

    @Autowired
    OquvchiService oquvchiService;

    @Autowired
    TolovService tolovService;

    @Autowired
    SessiyaService sessiyaService;

    @Autowired
    GuruhService guruhService;

    @GetMapping()
    public String royxat(Model model) throws IOException {
        model.addAttribute("tolovlar",tolovService.getAll());
        model.addAttribute("sessiyalar",sessiyaService.getAll());
        model.addAttribute("oylar",oylar);
        return "tolov";
    }


    @PostMapping()
    public String yarat(Tolov o, Model model) throws IOException {
        tolovService.create(o);
        return sessiyaTolov(o.getSessiya().getId(),model);
    }

    @GetMapping("/ochirish/{id}")
    public void ochirish(@PathVariable Long id, HttpServletResponse hsr) throws IOException {
        tolovService.delete(id);
        hsr.sendRedirect("/tolovlar");
    }

    @GetMapping("/edit/{id}")
    public String ozgartiriluvchi(@PathVariable Long id,Model model,HttpServletResponse hsr) throws IOException {
        Tolov o=tolovService.getById(id);
        model.addAttribute("tolov",o);
        return royxat(model);
    }

    @PostMapping("/edit")
    public void ozgartirish(Tolov o,HttpServletResponse hsr) throws IOException {
        tolovService.update(o);
        hsr.sendRedirect("/tolovlar");
    }


    @GetMapping("/sessiyaTolov/{id}")
    public String sessiyaTolov(@PathVariable Long id,Model model){
        model.addAttribute("sessiya",sessiyaService.getById(id));
        model.addAttribute("oylar",oylar);
        model.addAttribute("tolovlar",tolovService.getAllBySessiyaId(id));
        return "sessiyaTolov";
    }


    @GetMapping("/oquvchiTolov/{id}")
    public String oquvchiTolovlari(@PathVariable Long id,Model model){
        model.addAttribute("tolovlar",tolovService.getAllByOquvchiId(id));
        model.addAttribute("oquvchi",oquvchiService.getById(id));
        return "oquvchiTolov";
    }
    @GetMapping("/guruhTolov/{id}")
    public String guruhTolovlari(@PathVariable Long id,Model model){
        model.addAttribute("tolovlar",tolovService.getAllByGuruh(id));
        model.addAttribute("guruh",guruhService.getById(id));
        return "guruhTolov";
    }
}