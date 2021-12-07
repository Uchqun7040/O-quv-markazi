package uz.jjp.O.quv.markazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.jjp.O.quv.markazi.entity.Fan;
import uz.jjp.O.quv.markazi.entity.Search;
import uz.jjp.O.quv.markazi.service.FanService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/fanlar")
public class FanController {
    @Autowired
    FanService fanService;


    @GetMapping()
    public String royxat(Model model) throws IOException {
//        model.addAttribute("fanlar",fanService.getAll());
        return findPaginated(1,model);
    }

    @PostMapping("/izla")
    public String izla(Search s, Model model){
        model.addAttribute("fanlar",fanService.izla(s.getSatr()));
        return "fan";
    }

    @PostMapping()
    public void yarat(Fan o, HttpServletResponse hsr) throws IOException {

        try {
            fanService.create(o);
        }catch (Exception ignored){
            System.err.println(LocalDateTime.now()+" : Mavjud fan nomi kiritildi! ");
        }
        hsr.sendRedirect("/fanlar");
    }

    @GetMapping("/ochirish/{id}")
    public void ochirish(@PathVariable Long id, HttpServletResponse hsr) throws IOException {
        fanService.delete(id);
        hsr.sendRedirect("/fanlar");
    }

    @GetMapping("/edit/{id}")
    public String ozgartiriluvchi(@PathVariable Long id,Model model,HttpServletResponse hsr) throws IOException {
        Fan o=fanService.getById(id);
        model.addAttribute("fan",o);
        return royxat(model);
    }

    @PostMapping("/edit")
    public void ozgartirish(Fan o,HttpServletResponse hsr) throws IOException {

        try {
            fanService.update(o);
        }catch (Exception ignored){
            System.err.println(LocalDateTime.now()+" : Mavjud fan nomi kiritildi! ");
        }
        hsr.sendRedirect("/fanlar");
    }
    @GetMapping("/kirish")
    public String kirish(Model model) throws IOException {

        return "kirish";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo")int pageNo ,Model model){
        int pageSize = 5;
        Page<Fan> page =fanService.findPagination(pageNo,pageSize);
        List<Fan> fanlar = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("fanlar",fanlar);
        return "fan";

    }
}