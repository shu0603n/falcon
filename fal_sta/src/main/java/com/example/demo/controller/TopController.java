package com.example.demo.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Shuho;
import com.example.demo.entity.User;
import com.example.demo.repository.ShuhoRepository;
import com.example.demo.repository.UserRepository;



@Controller
public class TopController {
	
	/**
     * 週報情報 Repository
     */
    @Autowired
    ShuhoRepository shuhoRepository;
    
    /**
     * ユーザー情報 Repository
     */
    @Autowired
    UserRepository userRepository;
    
    @RequestMapping(value = "/Top", method = RequestMethod.GET)
    public String top(Model model,HttpSession session) {
    	List<Shuho> shuholist = shuhoRepository.findAllByOrderByPostedDayDesc();
    	
    	
    	
    	
    	//現在時刻取（形式：2018-02-11T13:02:49.957）
		LocalDate now = LocalDate.now();
        // 現在日の週の月曜日を取得
        LocalDate monday = now.with(DayOfWeek.MONDAY);
        // 現在日の週の金曜日を取得
        LocalDate friday = now.with(DayOfWeek.FRIDAY);
        //対象
        String taisyo = monday + " ～ " + friday;
        if(!(monday.isAfter(now) || friday.isBefore(now))) {
    		taisyo = monday.minusDays(7) + " ～ " + friday.minusDays(7);
    	}
                
        User user = new User();
        user.setUserId(session.getAttribute("loginId").toString());
        List<Shuho> lists = shuhoRepository.findByUserAndTaishoWeek(user,taisyo.replace("-", "/"));
        
        boolean flag = true;
        if(0 < lists.size()) {
        	flag  =false;
        }

    	model.addAttribute("shuholist", shuholist);
        model.addAttribute("msg", "");
        model.addAttribute("flag", flag);
        return "html/top";
    }
    
}
