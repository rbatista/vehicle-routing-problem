package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String redirectToApiDocumentation() {

        return "redirect:swagger-ui.html";
    }

}
