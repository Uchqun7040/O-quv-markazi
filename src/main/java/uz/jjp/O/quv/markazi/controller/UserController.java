package uz.jjp.O.quv.markazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.jjp.O.quv.markazi.entity.User;
import uz.jjp.O.quv.markazi.service.UserService;
import uz.jjp.O.quv.markazi.service.dto.UserDTO;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
//@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/login")
    public String login(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/oquvchilar";
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

        return null;
    }

    @PostMapping("/edit")
    public void ozgartirish(User o,HttpServletResponse hsr) throws IOException {
        userService.update(o);
        hsr.sendRedirect("/user");
    }
}