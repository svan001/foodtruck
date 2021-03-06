package com.svan001.foodtruck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {

    @RequestMapping("")
    public String home(){
        return "home";
    }

    @RequestMapping("test")
    public String helloTest(Model model) {
        model.addAttribute("toDisplay", "If this string is displayed, we are good");

        return "hello";
    }

    @RequestMapping("admin")
    public String helloAdmin(Model model) {
        return "admin";
    }

    @RequestMapping("manager")
    public String helloManager(Model model) {
        return "manager";
    }

    @RequestMapping("auth1")
    public String helloAuth1(Model model) {
        return "auth1";
    }

}
