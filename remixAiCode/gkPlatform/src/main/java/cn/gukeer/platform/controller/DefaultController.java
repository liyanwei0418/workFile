package cn.gukeer.platform.controller;

import cn.gukeer.common.controller.BasicController;
import cn.gukeer.platform.persistence.entity.User;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
public class DefaultController extends BasicController {

    @RequestMapping(value = "/err")
    public String err() {
        return "error";
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String root(HttpServletRequest request, Model model) {
        boolean isLogin = false;
        Subject subject = getSubject();
        if(subject != null){
            User user = (User) subject.getPrincipal();
            isLogin = user != null;
        }
        if (isLogin) {
            return "redirect:" + "home/index";
        } else {
            return "redirect:" + "login";
        }
    }

}
