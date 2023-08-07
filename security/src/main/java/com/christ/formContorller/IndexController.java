package com.christ.formContorller;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 史偕成
 * @date 2023/08/04 11:33
 **/
@Controller
public class IndexController {

    @ResponseBody
    @RequestMapping("/")
    public String index() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return JSON.toJSONString(authentication);
    }



    @RequestMapping("/loginFail")
    public String loginFail() {
        return "login";
    }
}
