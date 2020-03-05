package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Shuho;
import com.example.demo.entity.User;
import com.example.demo.repository.ShuhoRepository;

@Controller
public class RegisterController {
    
	@RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String register(Model model,@ModelAttribute User user) {

		
        return "html/insert";
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
    //本当はPOST↓　HTMLではAタグじゃなくFORMで書く
	@RequestMapping(value = "/Register/Done", method = RequestMethod.GET)
    public String registerDone(Model model,@ModelAttribute User user) {

		Shuho shuho = new Shuho();
		//週報のデータを入れる
		shuho.setUserId("hoge");
		shuho.setUserId("hoge");
		shuho.setUserId("hoge");
		shuho.setUserId("hoge");
		shuho.setUserId("hoge");
		shuho.setUserId("hoge");
		
		//userId
		shuho.setUserId(session.getAttribute("loginId").toString());

		
		try {
			shuhoRepository.save(shuho);
		}catch(Exception e){
			model.addAttribute("msg","入力項目に誤りがあります");
			return "html/insert";
		}

        return "html/done";
    }



}
