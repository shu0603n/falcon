package com.example.demo.controller;



import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.example.demo.repository.UserRepository;

@Controller
public class UserMyController {
	
	/**
     * ユーザー Repository
     */
    @Autowired
    UserRepository userRepository;
	
	/**
     * 週報情報 Repository
     */
    @Autowired
    ShuhoRepository shuhoRepository;
    
    
	@RequestMapping(value = "/UserMyView", method = RequestMethod.GET)
    public String userMyView(Model model,HttpSession session) {
		User UserData = userRepository.getOne(session.getAttribute("loginId").toString());

        model.addAttribute("user", UserData);

        return "html/userPage/userMyView";
    }
	
    
	@RequestMapping(value = "/UserMyChangeDone", method = RequestMethod.POST)
    public String userMyChangeDone(Model model,@ModelAttribute User user,HttpSession session) {

		try {
			userRepository.save(user);
		}catch(Exception e){
			model.addAttribute("msg","入力項目に誤りがあります");
			model.addAttribute("user", user);
			e.printStackTrace();
			return "html/userPage/userMyView";
		}
		model.addAttribute("msg","更新が完了しました");
        return "html/myPage/done";
    }
	
}
