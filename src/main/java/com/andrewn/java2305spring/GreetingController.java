package com.andrewn.java2305spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// @Controller - аннотация для определения того, что внутри класса есть методы,
// которые надо вызывать в случае запросов по определенным адресам
@Controller
public class GreetingController {

    Map<String, String> users = new HashMap<>();

    public GreetingController() {
        users.put("ADMIN", "12345");
        users.put("moderator", "54321");
    }

    // @GetMapping() - аннотация, связывающая запрос на определенный адрес с методом, который надо запустить
    @GetMapping("/greeting")
    public String hello(Model model, @RequestParam(name="fullname", required=false, defaultValue="World") String name) {
        // Model model - объект класса Model, в который можно добавлять атрибуты (как в словарь),
        // эти атрибуты будут доступны из шаблонов (html файлов)
        model.addAttribute("nameInView", name);
        System.out.println(name);
        // return внутри метода даёт понять имя шаблона (html файла),
        // который необходимо пропустить через thymeleaf и после этого отправить в ответ пользователю
        return "greeting";
    }

    @GetMapping("/auth")
    public String auth(Model model) {
        model.addAttribute("existsUser", true);
        model.addAttribute("correctPassword", true);
        model.addAttribute("authForm", new AuthForm());
        return "auth";
    }

    @PostMapping("/index")
    public String index(Model model, @ModelAttribute AuthForm authForm) {
        boolean existsUser = users.containsKey(authForm.getLogin());
        boolean correctPassword = users.getOrDefault(authForm.getLogin(), "").equals(authForm.getPassword());
        model.addAttribute("existsUser", existsUser);
        model.addAttribute("correctPassword", correctPassword);
        if (existsUser && correctPassword) {
            model.addAttribute("userInfo", authForm);
            return "index";
        } else {
            return "auth";
        }
    }
}
