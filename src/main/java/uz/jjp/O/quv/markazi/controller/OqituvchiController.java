package uz.jjp.O.quv.markazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.jjp.O.quv.markazi.entity.Fan;
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

    String satr="";

    @GetMapping()
    public String royxat(@PageableDefault(value = 15,page = 0) Pageable pageable, Model model) throws IOException {
        model.addAttribute("surov","");
        return findPaginated(pageable,oqituvchiService.getAll(pageable),model);
    }

    @GetMapping("/izla")
    public String izla(@PageableDefault(value = 15,page = 0) Pageable pageable, @Param("satr") String satr, Model model) throws IOException {
        if (satr ==null) satr=this.satr;
        else this.satr=satr;
        model.addAttribute("surov","/izla");
        return findPaginated(pageable,oqituvchiService.izla(satr,pageable),model);
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

    public String findPaginated(Pageable pageable, Page<Oqituvchi> oqituvchilar, Model model){
        model.addAttribute("oqituvchilar",oqituvchilar);
        model.addAttribute("pages",oqituvchilar.getTotalPages());
        model.addAttribute("pageNumber",pageable.getPageNumber());
        model.addAttribute("totalItems",oqituvchilar.getTotalElements());
        return "oqituvchi";
    }
}