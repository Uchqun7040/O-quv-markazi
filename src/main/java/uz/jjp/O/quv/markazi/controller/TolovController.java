package uz.jjp.O.quv.markazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.jjp.O.quv.markazi.entity.Tolov;
import uz.jjp.O.quv.markazi.service.TolovService;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/tolovlar")
public class TolovController {
    @Autowired
    TolovService tolovService;


    @GetMapping()
    public String royxat(Model model) throws IOException {
        model.addAttribute("tolovlar",tolovService.getAll());
        return "tolov";
    }


    @PostMapping()
    public void yarat(Tolov o, HttpServletResponse hsr) throws IOException {
        tolovService.create(o);
        hsr.sendRedirect("/tolovlar");
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

}