package org.kxnvg.http.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.kxnvg.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @GetMapping("/hello")
    public String hello(Model model, HttpServletRequest request) {
//        model.addAttribute("user", new UserReadDto(1L, "Ivan"));
        return "greeting/hello";
    }

    @GetMapping("/bye")
    public String bye(@SessionAttribute("users") UserReadDto user) {
        return "greeting/bye";
    }

    @GetMapping("/hello2/{id}")
    public ModelAndView hello2(ModelAndView modelAndView, @RequestParam(required = false) Integer age,
                              @RequestHeader String accept, @CookieValue("JSESSIONID") String jsessionId,
                              @PathVariable(required = false) Integer id) {
        modelAndView.setViewName("greeting/hello");

        return modelAndView;
    }
}
