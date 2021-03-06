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
public class SearchController {
	
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
	@RequestMapping(value = "/SearchWeek", method = RequestMethod.GET)
    public String searchDay(Model model) {
		List<Shuho> searchWeek = shuhoRepository.findAllGropuByTaishoWeek();
		model.addAttribute("searchWeeklist", searchWeek);

        return "html/empPage/listWeek";
    }
	
    /**
     * ユーザーが投稿した週報の一覧画面を表示
     * @param model Model
     * @return 週報情報一覧画面のHTML
     */
	@RequestMapping(value = "/SearchWeekName{taishoWeek}", method = RequestMethod.GET)
    public String displayList(Model model, @ModelAttribute Shuho shuho) {
		List<Shuho> shuholist = shuhoRepository.findByTaishoWeek(shuho.getTaishoWeek());
		model.addAttribute("taishoWeek", shuho.getTaishoWeek());
		model.addAttribute("shuholist", shuholist);
        return "html/empPage/listWeekName";
    }
	
	/**
     * ユーザーが投稿した週報の一覧画面を表示
     * @param model Model
     * @return 週報情報一覧画面のHTML
     */
	@RequestMapping(value = "/SearchName", method = RequestMethod.GET)
    public String displayList(Model model) {
		List<User> searchNamelist = userRepository.findAll();
		model.addAttribute("searchNamelist", searchNamelist);
        return "html/empPage/listName";
    }
	
	/**
     * ユーザーが投稿した週報の一覧画面を表示
     * @param model Model
     * @return 週報情報一覧画面のHTML
     */
	@RequestMapping(value = "/SearchNameWeek{userId}", method = RequestMethod.GET)
    public String displayList(Model model, @ModelAttribute User user) {
		Optional<User> userData = userRepository.findById(user.getUserId().toString());
		List<Shuho> shuholist = shuhoRepository.findByUser(userData.get());
		model.addAttribute("empName", userData.get().getEmpName());
		model.addAttribute("shuhoList", shuholist);
        return "html/empPage/listNameWeek";
    }
}