package uz.jjp.O.quv.markazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String yarat(Tolov o, HttpServletResponse hsr) throws IOException {
        tolovService.create(o);
        return "/tolovlar/sessiyaTolov/{id}(id="+o.getSessiya().getId()+")";

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
    public String sessiyaTolov(@PageableDefault(value = 15,page = 0) Pageable pageable, @PathVariable Long id, Model model){
        model.addAttribute("sessiya",sessiyaService.getById(id));
        model.addAttribute("oylar",oylar);
        model.addAttribute("tolovlar",tolovService.getAllBySessiyaId(id,pageable));
        model.addAttribute("pages",tolovService.getAllBySessiyaId(id,pageable).getTotalPages());
        model.addAttribute("pageNumber",pageable.getPageNumber());
        model.addAttribute("totalItems",tolovService.getAllBySessiyaId(id,pageable).getTotalElements());
        return "sessiyaTolov";
    }


    @GetMapping("/oquvchiTolov/{id}")
    public String oquvchiTolovlari(@PageableDefault(value = 15,page = 0) Pageable pageable,@PathVariable Long id,Model model){
        model.addAttribute("tolovlar",tolovService.getAllByOquvchiId(id,pageable));
        model.addAttribute("oquvchi",oquvchiService.getById(id));
        model.addAttribute("pages",tolovService.getAllByOquvchiId(id,pageable).getTotalPages());
        model.addAttribute("pageNumber",pageable.getPageNumber());
        model.addAttribute("totalItems",tolovService.getAllByOquvchiId(id,pageable).getTotalElements());
        return "oquvchiTolov";
    }
    @GetMapping("/guruhTolov/{id}")
    public String guruhTolovlari(@PageableDefault(value = 15,page = 0) Pageable pageable,@PathVariable Long id,Model model){
        model.addAttribute("tolovlar",tolovService.getAllByGuruhId(id,pageable));
        model.addAttribute("guruh",guruhService.getById(id));
        model.addAttribute("pages",tolovService.getAllByGuruhId(id,pageable).getTotalPages());
        model.addAttribute("pageNumber",pageable.getPageNumber());
        model.addAttribute("totalItems",tolovService.getAllByGuruhId(id,pageable).getTotalElements());
        return "guruhTolov";
    }

}