package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchNameController {
	@RequestMapping(value = "/SearchName", method = RequestMethod.GET)
    public String searchName(Model model) {

        System.out.println("Change");
        return "html/list_name";
    }

}
