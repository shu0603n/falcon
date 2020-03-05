package com.example.controller.login;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//Controllerだよ！という注釈
@Controller
public class LoginController {
    //RequestMapping "/"→ルートURLにアクセスすると呼び出されるようにできる
    //→アクセスするアドレスごとにメソッドを指定できる!
    @RequestMapping(value="/",method=RequestMethod.GET)
    //ModelとViewを2つとも置ける（返り値にできる）便利なやつ
    public ModelAndView index(ModelAndView mav) {
        //ビューネームの設定
        mav.setViewName("login");
        //値（msg）とそれに対する出力文字を設定
        mav.addObject("msg", "名前を入力してください：");
        return mav;
    }

    @RequestMapping(value="/",method=RequestMethod.POST)
    //"name"でPOSTされたパラメータを取得し、それを引数に
    public ModelAndView send(@RequestParam Map<String, String> params,ModelAndView mav) {
    	
    	mav.addObject("user_id", params.get("user_id"));
        mav.addObject("msg", "ユーザーIDまたはパスワードが間違えています");
        System.out.println(params.get("user_id"));
        System.out.println(params.get("passwod"));
        
        
        if(params.get("password").equals("1111")) {
        	mav.setViewName("top");
        } else {
        	mav.setViewName("login");
        }
        
    	


        return mav;
    }
}