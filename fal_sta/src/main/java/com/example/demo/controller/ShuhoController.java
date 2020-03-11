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
public class ShuhoController {
	
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
    
    @RequestMapping(value = "/MyList", method = RequestMethod.GET)
    public String displayList(Model model, HttpSession session) {

    	//ユーザーIDをもとにユーザー情報を取得
    	Optional<User> user = userRepository.findById(session.getAttribute("loginId").toString());
    	
    	//nullの場合
    	if(user.isEmpty()) {
    		model.addAttribute("msg", "データが登録されていません");
    		return "html/myPage/myList";
    	}
    	
		List<Shuho> shuholist = shuhoRepository.findByUserOrderByPostedDayDesc(user.get());
		
		//nullの場合
    	if(shuholist.isEmpty()) {
    		model.addAttribute("msg", "データが登録されていません");
    		return "html/myPage/myList";
    	}
		
		model.addAttribute("shuholist", shuholist);
		
		return "html/myPage/myList";
    }
    
    
	@RequestMapping(value = "/Change{shuhoId}", method = RequestMethod.GET)
    public String change(Model model,@ModelAttribute Shuho shuho) {
		Shuho shuhoData = shuhoRepository.getOne(shuho.getShuhoId());

        model.addAttribute("shuhoData", shuhoData);

        return "html/myPage/update";
    }
	
	
	@RequestMapping(value = "/Change", method = RequestMethod.GET)
    public String change2(Model model,@ModelAttribute Shuho shuho,HttpSession session) {
		
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
	    List<Shuho> shuholist = shuhoRepository.findByUserAndTaishoWeek(user,taisyo.replace("-", "/"));
	    Shuho shuhoData = shuholist.get(0);

        model.addAttribute("shuhoData", shuhoData);

        return "html/myPage/update";
    }
	
	
    
    
	@RequestMapping(value = "/ChangeDone", method = RequestMethod.POST)
    public String changeDone(Model model,@ModelAttribute Shuho shuho,HttpSession session) {

		User user = new User();
		user.setUserId(session.getAttribute("loginId").toString());
		
		//現在時刻取（形式：2018-02-11T13:02:49.957）
		LocalDate now = LocalDate.now();
		//現在時刻取（形式：2018-02-11）
		DateTimeFormatter dtformat1 = 
				DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String toDay = dtformat1.format(now);
				
		Shuho shuhoData = shuho;
		shuhoData.setPostedDay(toDay);
		shuhoData.setUser(user);
		System.out.println(shuhoData.getPostedDay());
		
		try {
			shuhoRepository.save(shuhoData);
		}catch(Exception e){
			model.addAttribute("msg","入力項目に誤りがあります");
			model.addAttribute("shuhoData", shuhoData);
			e.printStackTrace();
			return "html/myPage/update";
		}
		model.addAttribute("msg","更新が完了しました");
        return "html/myPage/done";
    }
	
	@RequestMapping(value = "/Delete{id}", method = RequestMethod.GET)
    public String changeDone(Model model,@ModelAttribute Shuho shuho) {

		Optional<Shuho> shuhoData = shuhoRepository.findById(shuho.getShuhoId());
		if(shuhoData.isEmpty()) {
			model.addAttribute("msg","既にデータが削除されています。");
			model.addAttribute("shuhoData", shuhoData);
			return "html/myPage/myList";
		}
		
		try {
			shuhoRepository.deleteById(shuho.getShuhoId());
			model.addAttribute("msg","データを削除しました。");
			 return "html/myPage/done";
		}catch(Exception e){
			model.addAttribute("msg","予期せぬエラーが発生しました。");
			model.addAttribute("shuho", shuhoData);
			return "html/myPage/myList";
		}
    }
	
	@RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String register(Model model,@ModelAttribute User user) {

		//現在時刻取（形式：2018-02-11T13:02:49.957）
		LocalDate now = LocalDate.now();
		//フォーマットを指定
		DateTimeFormatter dtformat1 = 
				DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//現在時刻を指定フォーマットに変換（形式：2018-02-11）
		String toDay = dtformat1.format(now);
		// 1ヶ月前の日付を取得
        LocalDate targetDate = now.minusMonths(3);
        // 現在日の週の月曜日を取得
        LocalDate monday = now.with(DayOfWeek.MONDAY);
        // 現在日の週の木曜日を取得
        LocalDate thursday = now.with(DayOfWeek.THURSDAY);
        // 現在日の週の金曜日を取得
        LocalDate friday = now.with(DayOfWeek.FRIDAY);
        //対象
        String taisyo = monday + " ～ " + friday;
        //
        List<String> week = new ArrayList<String>();

        //1カ月分（targetDate）繰り返し処理
        while (!friday.isBefore(targetDate)) {
        	
        	//今日の日付が（今週）月曜～木曜なら、（先週）月曜～金曜を返す
        	if(!(monday.isAfter(now) || friday.isBefore(now))) {
        		taisyo = monday.minusDays(7) + " ～ " + friday.minusDays(7);
        	}
        	//リストに追加
        	week.add(taisyo = monday.minusDays(7) + " ～ " + friday.minusDays(7));
        	
        	//曜日を一週間前にする
            monday = monday.minusDays(7);
            thursday = thursday.minusDays(7);
            friday = friday.minusDays(7);
        }
        System.out.println(week.size());
        
		model.addAttribute("taishoWeek", taisyo.replace("-", "/"));
		model.addAttribute("postedDay", toDay);
		model.addAttribute("week", week);
		
        return "html/myPage/insert";
    }

	@RequestMapping(value = "/RegisterDone", method = RequestMethod.POST)
    public String registerDone(Model model,@ModelAttribute Shuho shuho,HttpSession session) {

		//週報のデータを入れる
		User user = new User();
		user.setUserId(session.getAttribute("loginId").toString());
		shuho.setUser(user);

		try {
			shuhoRepository.save(shuho);
		}catch(Exception e){
			model.addAttribute("msg","入力項目に誤りがあります");
			return "html/insert";
		}
		model.addAttribute("msg","登録が完了しました。");
        return "html/myPage/done";
    }

}
