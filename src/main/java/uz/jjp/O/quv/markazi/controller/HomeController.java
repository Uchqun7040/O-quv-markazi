package uz.jjp.O.quv.markazi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping()
    public String boshSahifa(){
        return "index";
    }
}
