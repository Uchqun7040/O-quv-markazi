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
import uz.jjp.O.quv.markazi.entity.Guruh;
import uz.jjp.O.quv.markazi.entity.Sessiya;
import uz.jjp.O.quv.markazi.service.*;

import javax.servlet.http.HttpServletResponse;
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

    String satr="";
    Long guruhId= 0L;
    @GetMapping()
    public String royxat(@PageableDefault(value = 10,page = 0) Pageable pageable, Model model) throws IOException {
        model.addAttribute("surov","");
        return findPaginated(pageable,guruhService.getAll(pageable),model);
    }

    @GetMapping("/izla")
    public String izla(@PageableDefault(value = 10,page = 0) Pageable pageable, @Param("satr") String satr, Model model) throws IOException {
        if (satr ==null) {
            satr=this.satr;
        }
        else {
            this.satr=satr;
        }
        model.addAttribute("surov","/izla");
        return findPaginated(pageable,guruhService.izla(satr,pageable),model);
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
    @GetMapping("/onguruh/izla")
    public String izlaOnguruh(@PageableDefault(value = 10,page = 0) Pageable pageable, @Param("satr") String satr, Model model) throws IOException {
        if (satr ==null) satr=this.satr;
        else this.satr=satr;
        return forPageableOnguruh(pageable,sessiyaService.izlaOnGuruh(guruhId,satr,pageable),model,"/izla");
    }
    @GetMapping("/onguruh/{id}")
    public String onguruh(@PageableDefault(value = 15,page = 0) Pageable pageable,@PathVariable Long id,Model model){
        this.guruhId=id;
        return forPageableOnguruh(pageable,sessiyaService.getAllByGuruhId(id,pageable),model,"");
    }
    String forPageableOnguruh(Pageable pageable, Page<Sessiya> sessiyalar, Model model,String surov){
        model.addAttribute("surov",surov);
        model.addAttribute("sessiyalar",sessiyalar);
        model.addAttribute("oqituvchilar",oqituvchiService.getAll());
        model.addAttribute("guruh",guruhService.getById(guruhId));
        model.addAttribute("pages",sessiyaService.getAllByGuruhId(guruhId,pageable).getTotalPages());
        model.addAttribute("pageNumber",pageable.getPageNumber());
        model.addAttribute("totalItems",sessiyaService.getAllByGuruhId(guruhId,pageable).getTotalElements());
        return "onguruh";
    }
    @PostMapping("/edit")
    public String ozgartirish(@PageableDefault(value = 15,page = 0) Pageable pageable,Guruh o,Model model) throws IOException {

        try {
            guruhService.update(o);
        }catch (Exception ignored){
            System.err.println(LocalDateTime.now()+" : Mavjud guruh nomi kiritildi! ");
        }
        return onguruh(pageable,o.getId(),model);
    }

    @GetMapping("/ochirishSessiya/{id}")
    public String ochirishY(@PageableDefault(value = 15,page = 0) Pageable pageable,@PathVariable Long id,Model model) throws IOException {
        sessiyaService.delete(id);
        return onguruh(pageable,sessiyaService.getById(id).getGuruh().getId(),model);
    }

    public String findPaginated(Pageable pageable, Page<Guruh> guruhlar, Model model){
        model.addAttribute("guruhlar",guruhlar);
        model.addAttribute("oqituvchilar",oqituvchiService.getAll());
        model.addAttribute("fanlar",fanService.getAll());
        model.addAttribute("pages",guruhlar.getTotalPages());
        model.addAttribute("pageNumber",pageable.getPageNumber());
        model.addAttribute("totalItems",guruhlar.getTotalElements());
        return "guruh";
    }
}