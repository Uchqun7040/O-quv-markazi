package uz.jjp.O.quv.markazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;
import uz.jjp.O.quv.markazi.entity.Guruh;
import uz.jjp.O.quv.markazi.entity.Oquvchi;
import uz.jjp.O.quv.markazi.entity.Search;
import uz.jjp.O.quv.markazi.entity.Sessiya;
import uz.jjp.O.quv.markazi.service.GuruhService;
import uz.jjp.O.quv.markazi.service.OquvchiService;
import uz.jjp.O.quv.markazi.service.SessiyaService;



import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        model.addAttribute("sessiyalar",sessiyaService.getAll());
        return "guruhlash";
    }

//    @GetMapping("/tolanganlar")
//    public String tolanganlar( Model model) throws IOException {
//        model.addAttribute("sessiyalar",sessiyaService.tolovUchun(true));
//        return "sessiya";
//    }
//    @GetMapping("/tolanmaganlar")
//    public String tolanmaganlar(Model model) throws IOException {
//        model.addAttribute("sessiyalar",sessiyaService.tolovUchun(false));
//        return "sessiya";
//    }

    @PostMapping("/izla")
    public String izla(Search s, Model model){
        model.addAttribute("sessiyalar",sessiyaService.izla(s.getSatr()));
        return "guruhlash";
    }
    @PostMapping()
    public String yarat(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Sessiya o,Model model) throws IOException {
        sessiyaService.create(o);
        model.addAttribute("oquvchi",o.getOquvchi());
        model.addAttribute("sessiyalar",sessiyaService.getByOquvchiId(o.getOquvchi().getId()));
        model.addAttribute("guruhlar",guruhService.getAllByNotOquvchiId(o.getOquvchi().getId()));
        return "oquvchiTahrirlash";
    }

    @GetMapping("/ochirish/{id}")
    public void ochirish(@PathVariable Long id,HttpServletResponse hsr) throws IOException {
        sessiyaService.delete(id);
        hsr.sendRedirect("/sessiyalar");
    }
    @GetMapping("/ochirishX/{id}")
    public String ochirish(@PathVariable Long id,Model model) throws IOException {
        Oquvchi oquvchi=sessiyaService.getById(id).getOquvchi();
        sessiyaService.delete(id);
        model.addAttribute("oquvchi",oquvchi);
        model.addAttribute("sessiyalar",sessiyaService.getByOquvchiId(oquvchi.getId()));
        model.addAttribute("guruhlar",guruhService.getAllByNotOquvchiId(oquvchi.getId()));
        return "oquvchiTahrirlash";
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
