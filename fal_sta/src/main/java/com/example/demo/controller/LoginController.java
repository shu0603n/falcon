package com.example.demo.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;



@Controller
public class LoginController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("msg", "");
        return "login";
    }
    
    /**
     * ユーザー情報 Repository
     */
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    HttpSession session;
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String loginPost(Model model,@ModelAttribute User user) {
    	
    	Optional<User> userData = userRepository.findById(user.getUserId());
    	
    	if(userData.isEmpty()) {
    		//ユーザーIDが見つからない場合
    		model.addAttribute("msg", "ユーザーIDが見つかりません。");
    		model.addAttribute("userId", "");
    		return "login";
    	} else if(!userData.get().getPassword().equals(user.getPassword())) {
    		//ユーザーID、パスワードが間違えてる場合見つからない場合
    		model.addAttribute("msg", "ユーザーIDまたはパスワードが違います。");
    		model.addAttribute("userId", user.getUserId());
	        return "login";
    	} else {
    		//ユーザID、パスワードが正しい場合
    		session.setAttribute("loginId",userData.get().getUserId());
    		return "redirect:/Top";
    	} 	
    }
}
