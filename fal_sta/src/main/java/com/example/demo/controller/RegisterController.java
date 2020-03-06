package com.example.demo.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

		//現在時刻取（形式：2018-02-11T13:02:49.957）
		LocalDate now = LocalDate.now();
		//変換（形式：2018-02-11）
		DateTimeFormatter dtformat1 = 
				DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String toDay = dtformat1.format(now);
		// 1ヶ月前の日付を取得
        LocalDate targetDate = now.minusMonths(1);
        // 現在日の週の月曜日を取得
        LocalDate monday = now.with(DayOfWeek.MONDAY);
        // 現在日の週の木曜日を取得
        LocalDate thursday = now.with(DayOfWeek.THURSDAY);
        // 現在日の週の金曜日を取得
        LocalDate friday = now.with(DayOfWeek.FRIDAY);
        String taisyo = monday + " ～ " + friday;
        
        while (!friday.isBefore(targetDate)) {
        	
        	//今日の日付が（今週）月曜～木曜なら、月曜～金曜を返す
        	if(!(monday.minusDays(7).isAfter(now) || thursday.minusDays(7).isBefore(now))) {
        		taisyo = monday + " ～ " + friday;
        	}
        	
        	//曜日を一週間前にする
            monday = monday.minusDays(7);
            thursday = thursday.minusDays(7);
            friday = friday.minusDays(7);
        }
        
		model.addAttribute("taishoWeek", taisyo.replace("-", "/"));
		model.addAttribute("postedDay", toDay);
		
        return "html/insert";
    }
	
	/**
     *週報情報 Repository
     */
    @Autowired
    ShuhoRepository shuhoRepository;

	@RequestMapping(value = "/RegisterDone", method = RequestMethod.POST)
    public String registerDone(Model model,@ModelAttribute Shuho shuho,HttpSession session) {

		//週報のデータを入れる
//		shuho.setShuhoId(0);
		shuho.setUserId(session.getAttribute("loginId").toString());

		System.out.println(shuho.getTaishoWeek());
		try {
			shuhoRepository.save(shuho);
		}catch(Exception e){
			model.addAttribute("msg","入力項目に誤りがあります");
			return "html/insert";
		}
		model.addAttribute("msg","登録が完了しました。");
        return "html/done";
    }
}
