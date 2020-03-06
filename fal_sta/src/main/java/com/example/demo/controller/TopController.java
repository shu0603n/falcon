package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Shuho;
import com.example.demo.repository.ShuhoRepository;



@Controller
public class TopController {
	
	/**
     * 週報情報 Repository
     */
    @Autowired
    ShuhoRepository shuhoRepository;
    
    @Autowired
    HttpSession session;
    @RequestMapping(value = "/Top", method = RequestMethod.GET)
    public String top(Model model) {
    	List<Shuho> shuholist = shuhoRepository.findAll();
    	
    	model.addAttribute("shuholist", shuholist);
        model.addAttribute("msg", "");
        return "html/top";
    }
    
}
