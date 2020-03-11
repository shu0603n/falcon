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
public class UserController {
	
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
    
    @RequestMapping(value = "/UserList", method = RequestMethod.GET)
    public String userList(Model model, HttpSession session) {
    	
		List<User> userlist = userRepository.findAll();
		//自分のリストは抜いたほうがいいかも
		
		//nullの場合
    	if(userlist.isEmpty()) {
    		model.addAttribute("msg", "データが登録されていません");
    	} else {
    		model.addAttribute("userlist", userlist);
    	}
		return "html/userPage/userList";
    }
    
    
	@RequestMapping(value = "/UserView{userId}", method = RequestMethod.GET)
    public String userView(Model model,@ModelAttribute User user) {
		User UserData = userRepository.getOne(user.getUserId());

        model.addAttribute("user", UserData);

        return "html/userPage/userView";
    }
	
    
	@RequestMapping(value = "/UserChangeDone", method = RequestMethod.POST)
    public String userChangeDone(Model model,@ModelAttribute User user,HttpSession session) {

		try {
			userRepository.save(user);
		}catch(Exception e){
			model.addAttribute("msg","入力項目に誤りがあります");
			model.addAttribute("user", user);
			e.printStackTrace();
			return "html/userPage/userView";
		}
		model.addAttribute("msg","更新が完了しました");
        return "html/myPage/done";
    }
	
	@RequestMapping(value = "/UserDelete{id}", method = RequestMethod.GET)
    public String userDelete(Model model,@ModelAttribute User user) {
		
		try {
			userRepository.deleteById(user.getUserId());
			model.addAttribute("msg","データを削除しました。");
			 return "html/myPage/done";
		}catch(Exception e){
			model.addAttribute("msg","予期せぬエラーが発生しました。");
			model.addAttribute("user", user);
			return "html/userPage/userList";
		}
    }
	
	@RequestMapping(value = "/UserRegister", method = RequestMethod.GET)
    public String userRegister(Model model) {
		User user = new User();
		model.addAttribute("user",user);
        return "html/userPage/userInsertView";
    }
	

	@RequestMapping(value = "/UserRegisterDone", method = RequestMethod.POST)
    public String userRegisterDone(Model model,@ModelAttribute User user) {
		
		Optional<User> userlist = userRepository.findById(user.getUserId());
		
		if(!userlist.isEmpty()) {
			userlist.get().setUserId("");
			model.addAttribute("msg","既に使用されているIDです。");
			model.addAttribute("user",user);
			return "html/userPage/userInsertView";
		}
		System.out.println(user.getUserId());

		try {
			userRepository.save(user);
		}catch(Exception e){
			model.addAttribute("msg","入力項目に誤りがあります");
			model.addAttribute("user",user);
			e.printStackTrace();
			return "html/userPage/userInsertView";
		}
		model.addAttribute("msg","登録が完了しました。");
        return "html/myPage/done";
    }

}
