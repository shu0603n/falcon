package com.example.demo.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Shuho;
import com.example.demo.repository.ShuhoRepository;

@Controller
public class DeleteController {
	
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
    
	@RequestMapping(value = "/DeleteDone", method = RequestMethod.POST)
    public String changeDone(Model model,@RequestParam("shuhoId") Integer shuhoId) {

		Optional<Shuho> shuho = shuhoRepository.findById(shuhoId);
		if(shuho.isEmpty()) {
			model.addAttribute("msg","既にデータが削除されています。");
			model.addAttribute("shuho", shuho);
			return "html/list";
		}
		
		try {
			shuhoRepository.deleteById(shuhoId);
			 return "html/done";
		}catch(Exception e){
			model.addAttribute("msg","予期せぬエラーが発生しました。");
			model.addAttribute("shuho", shuho);
			return "html/myPage/list";
		}
    }

}
