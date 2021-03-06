package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Shuho;
import com.example.demo.entity.User;
import com.example.demo.repository.ShuhoRepository;
import com.example.demo.repository.UserRepository;





@Controller
public class ViewController {
	
	/**
     * ユーザー情報 Repository
     */
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    ShuhoRepository shuhoRepository;

    /**
     * ユーザーが投稿した週報の一覧画面を表示
     * @param model Model
     * @return 週報情報一覧画面のHTML
     */
	@RequestMapping(value = "/View{shuhoId}", method = RequestMethod.GET)
    public String displayList(Model model, @ModelAttribute Shuho shuho) {
		Optional<Shuho> shuhoData = shuhoRepository.findById(shuho.getShuhoId());
		model.addAttribute("shuhoData", shuhoData.get());
        return "html/empPage/view";
    }
}