package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Shuho;
import com.example.demo.repository.ShuhoRepository;

@Controller
public class ChangeController {
	@RequestMapping(value = "/Change", method = RequestMethod.GET)
    public String change(Model model) {
		Shuho shuho = new Shuho();
		//表示するデータをセット
		shuho.setUserId("aaaa");
		shuho.setUserId("aaaa");
		shuho.setUserId("aaaa");
		shuho.setUserId("aaaa");
		shuho.setUserId("aaaa");
		shuho.setUserId("aaaa");
		shuho.setUserId("aaaa");
		
        model.addAttribute("shuho", shuho);

        return "html/update";
    }
	
	/**
     * ユーザー情報 Repository
     */
    @Autowired
    ShuhoRepository shuhoRepository;
    
    /**
     * セッション情報
     */
    @Autowired
    HttpSession session;
    
	@RequestMapping(value = "/ChangeDone", method = RequestMethod.POST)
    public String changeDone(Model model,@ModelAttribute Shuho shuho) {

		//表示するデータをセット
		shuho.setUserId("aaaa");
		shuho.setUserId("aaaa");
		shuho.setUserId("aaaa");
		shuho.setUserId("aaaa");
		shuho.setUserId("aaaa");
		shuho.setUserId("aaaa");
		shuho.setUserId("aaaa");
		
		//userId
		shuho.setUserId(session.getAttribute("loginId").toString());
		
		try {
			shuhoRepository.save(shuho);
		}catch(Exception e){
			model.addAttribute("msg","入力項目に誤りがあります");
			model.addAttribute("shuho", shuho);
			return "html/update";
		}
        return "html/done";
    }

}
