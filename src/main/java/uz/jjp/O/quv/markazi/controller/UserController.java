package uz.jjp.O.quv.markazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.jjp.O.quv.markazi.entity.User;
import uz.jjp.O.quv.markazi.service.UserService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping
    public String user(Model model){
        model.addAttribute("userlar", userService.getAll());
        return "user";
    }

    @PostMapping()
    public void create(User o, HttpServletResponse hsr) throws IOException {
        userService.create(o);
        hsr.sendRedirect("/user");
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id, HttpServletResponse hsr) throws IOException {
        userService.deleteById(id);
        hsr.sendRedirect("/user");
    }

    @GetMapping("/edit/{id}")
    public String ozgartiriluvchi(@PathVariable Long id,Model model) throws IOException {
        User o=userService.getById(id);
        model.addAttribute("user",o);
        return user(model);
    }

    @PostMapping("/edit")
    public void ozgartirish(User o,HttpServletResponse hsr) throws IOException {
        userService.update(o);
        hsr.sendRedirect("/user");
    }
}