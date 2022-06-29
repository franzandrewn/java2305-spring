package com.andrewn.java2305spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @Controller - аннотация для определения того, что внутри класса есть методы,
// которые надо вызывать в случае запросов по определенным адресам
@Controller
public class GreetingController {

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
}
