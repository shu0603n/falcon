package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChangeController {
	@RequestMapping(value = "/Change", method = RequestMethod.GET)
    public String change(Model model) {
        model.addAttribute("msg", "");
        System.out.println("Change");
        return "html/update";
    }

}
