package uz.jjp.O.quv.markazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.jjp.O.quv.markazi.entity.Fan;
import uz.jjp.O.quv.markazi.entity.Oquvchi;
import uz.jjp.O.quv.markazi.entity.Search;
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
    SessiyaService sessiyaService;

    String satr="";

    @GetMapping()
    public String royxat(@PageableDefault(value = 15,page = 0) Pageable pageable, Model model) throws IOException {
        model.addAttribute("surov","");
        model.addAttribute("guruhlar",guruhService.getAll());
        return findPaginated(pageable,sessiyaService.getAll(pageable),model);
    }

    @GetMapping("/izla")
    public String izla(@PageableDefault(value = 15,page = 0) Pageable pageable, @Param("satr") String satr, Model model) throws IOException {
        if (satr ==null) satr=this.satr;
        else this.satr=satr;
        model.addAttribute("surov","/izla");
        return findPaginated(pageable,sessiyaService.izla(satr,pageable),model);
    }



    @GetMapping("/tolanganlar")
    public String tolanganlar(@PageableDefault(value = 15,page = 0) Pageable pageable, Model model) throws IOException {
        return findPaginated(pageable,sessiyaService.tolovUchun(true,pageable),model);
    }

    @GetMapping("/tolanmaganlar")
    public String tolanmaganlar(@PageableDefault(value = 15,page = 0) Pageable pageable,Model model) throws IOException {
        return findPaginated(pageable,sessiyaService.tolovUchun(false,pageable),model);

    }

    @PostMapping()
    public String yarat(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Sessiya o,Model model) throws IOException {
        sessiyaService.create(o);
        model.addAttribute("oquvchi",o.getOquvchi());
        model.addAttribute("sessiyalar",sessiyaService.getAllByOquvchiId(o.getOquvchi().getId()));
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
        model.addAttribute("sessiyalar",sessiyaService.getAllByOquvchiId(oquvchi.getId()));
        model.addAttribute("guruhlar",guruhService.getAllByNotOquvchiId(oquvchi.getId()));
        return "oquvchiTahrirlash";
    }


    @GetMapping("/edit/{id}")
    public String ozgartiriluvchi(@PageableDefault(value = 15,page = 0) Pageable pageable,@PathVariable Long id,Model model) throws IOException {
        Sessiya o=sessiyaService.getById(id);
        model.addAttribute("sessiya",o);
        return findPaginated(pageable,sessiyaService.getAll(pageable),model);
    }

    @PostMapping("/edit")
    public void ozgartirish(Sessiya o,HttpServletResponse hsr) throws IOException {
        sessiyaService.update(o);
        hsr.sendRedirect("/sessiyalar");
    }


    public String findPaginated(Pageable pageable, Page<Sessiya> sessiyalar, Model model){
        model.addAttribute("sessiyalar",sessiyalar);
        model.addAttribute("pages",sessiyalar.getTotalPages());
        model.addAttribute("pageNumber",pageable.getPageNumber());
        model.addAttribute("totalItems",sessiyalar.getTotalElements());
        return "guruhlash";
    }

}
