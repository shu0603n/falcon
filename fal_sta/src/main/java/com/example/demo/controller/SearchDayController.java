package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchDayController {
	@RequestMapping(value = "/SearchDay", method = RequestMethod.GET)
    public String searchDay(Model model) {

        return "html/list_name2";
    }

}
