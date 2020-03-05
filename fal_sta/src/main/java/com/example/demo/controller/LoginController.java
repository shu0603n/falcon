package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;


@Controller
public class LoginController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("msg", "");
        System.out.println("haro-kon");
        return "login";
    }
    
    /**
     * ユーザー情報 Service
     */
    @Autowired
    UserService userService;
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String loginPost(Model model,@ModelAttribute User user) {
    	
    	System.out.println(user.getUserId());
    	System.out.println(user.getPassword());

    	if("1111".equals(user.getPassword())) {
//    		DBの名前変えてから！
//    		List<User> userlist = userService.searchAll();
//            model.addAttribute("userlist", userlist);
    		return "html/top";
    	} else {
			model.addAttribute("msg", "ユーザーIDまたはパスワードが違います。");
	        return "login";
    	}

    }
}
