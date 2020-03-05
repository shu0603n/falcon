package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;


/**
 * ユーザー情報 Controller
 */
@Controller
public class UserController {

	/**
     * ユーザー情報 Repository
     */
    @Autowired
    UserRepository userRepository;

    /**
     * ユーザー情報一覧画面を表示
     * @param model Model
     * @return ユーザー情報一覧画面のHTML
     */
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String displayList(Model model) {
        List<User> userlist = userRepository.findAll();
        model.addAttribute("userlist", userlist);
        return "user/list";
    }
}