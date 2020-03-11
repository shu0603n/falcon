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

}
