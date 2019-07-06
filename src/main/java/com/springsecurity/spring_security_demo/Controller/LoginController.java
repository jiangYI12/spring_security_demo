package com.springsecurity.spring_security_demo.Controller;

import com.springsecurity.spring_security_demo.Util.sendSms;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("")
    public ModelAndView login(ModelAndView modelAndView){
        return new ModelAndView("Login");
    }

    @RequestMapping("/moble")
    public void moble(@RequestParam("phone") String phone){
        String code = (int)((Math.random()*9+1)*100000)+"";
        sendSms.sendMsg(phone,code);
    }
}
