package uz.jjp.O.quv.markazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.jjp.O.quv.markazi.entity.Guruh;
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

    String satr="";

    @GetMapping()
    public String royxat(@PageableDefault(value = 15,page = 0) Pageable pageable, Model model) throws IOException {
        model.addAttribute("surov","");
        return findPaginated(pageable,oquvchiService.getAll(pageable),model);
    }

    @GetMapping("/izla")
    public String izla(@PageableDefault(value = 15,page = 0) Pageable pageable, @Param("satr") String satr, Model model) throws IOException {
        if (satr ==null) satr=this.satr;
        else this.satr=satr;
        model.addAttribute("surov","/izla");
        return findPaginated(pageable,oquvchiService.izla(satr,pageable),model);
    }


    @PostMapping()
    public void yarat(Oquvchi o, HttpServletResponse hsr) throws IOException {
        oquvchiService.create(o);
        hsr.sendRedirect("/oquvchilar");
    }

    @GetMapping("/ochirish/{id}")
    public void ochirish(@PathVariable Long id, HttpServletResponse hsr) throws IOException {
//        sessiyaService.deleteByOquvchiId(id);
        oquvchiService.delete(id);
        hsr.sendRedirect("/oquvchilar");
    }

    @GetMapping("/edit/{id}")
    public String ozgartiriluvchi(@PathVariable Long id,Model model) throws IOException {
        Oquvchi o=oquvchiService.getById(id);

        model.addAttribute("oquvchi",o);
        model.addAttribute("sessiyalar",sessiyaService.getAllByOquvchiId(id));
        model.addAttribute("guruhlar",guruhService.getAllByNotOquvchiId(id));
        return "oquvchiTahrirlash";
    }

    @PostMapping("/edit")
    public String ozgartirish(Oquvchi o,Model model) throws IOException {
        oquvchiService.update(o);
       return ozgartiriluvchi(o.getId(),model);
    }

    public String findPaginated(Pageable pageable, Page<Oquvchi> oquvchilar, Model model){
        model.addAttribute("oquvchilar",oquvchilar);
        model.addAttribute("pages",oquvchilar.getTotalPages());
        model.addAttribute("pageNumber",pageable.getPageNumber());
        model.addAttribute("totalItems",oquvchilar.getTotalElements());
        return "oquvchi";
    }
}