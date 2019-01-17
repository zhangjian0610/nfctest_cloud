package com.bupt.cardtest.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @RequestMapping("/")
    String home(HttpServletRequest request) {
        return "/login";
    }

    @RequestMapping("/index")
    String index() {
        return "/index";
    }

}
