package com.andrewn.java2305spring;

// task1
// Создать класс DoSomethingController, который будет являться контроллером
// Определить, что он обрабатывает GET запрос на адрес "/something"
// В ответе надо дать строчку "Something is up" (то есть метод возвращает имя html файла)

// task2
// Добавить обработку GET запроса "/somethingX1"
// При этом надо определить, что в адресной строке должен быть обязательный параметр с именем something
// В ответе надо дать значение параметра something
// Например на запрос "localhost:8081/somethingX1?something=abcde" надо вернуть "abcde"

// task3 (*)
// Добавить обработку GET запроса "/somethingX2"
// При этом надо определить, что в адресной строке должен быть обязательный параметр с именем something
// В ответе надо дать два склеенных значения параметра something
// Например на запрос "localhost:8081/somethingX2?something=abcde" надо вернуть "abcdeabcde"

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DoSomethingController {
    @GetMapping("/something")
    public String something() {
        return "something";
    }

    @GetMapping("/somethingX1")
    public String somethingX1(Model model, @RequestParam(name="something") String something){
        model.addAttribute("something", something);
        return "somethingX1";
    }

    @GetMapping("/somethingX2")
    public String somethingX2(Model model, @RequestParam(name="something") String something){
        model.addAttribute("something1", something + " " +something);
        model.addAttribute("something2", something);
        return "somethingX2";
    }
}
