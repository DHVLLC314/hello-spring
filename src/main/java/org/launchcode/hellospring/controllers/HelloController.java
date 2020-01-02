package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller

public class HelloController {

    //Handles request @ path /hello - Lives @ /hello
    @GetMapping
    @ResponseBody
    public String hello() {
        return "Hello, Spring!";
    }

    //Handles request @ path /goodbye - Lives @ /hello/goodbye
    @GetMapping("goodbye")
    @ResponseBody
    public String goodbye() {
        return "Goodbye, Spring!";
    }

    //Handles request @ the path /hello?name=LaunchCode - Lives @ /hello?name=LaunchCode
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "hello")
    public String helloWithQueryParam(@RequestParam String name, Model model) {
        model.addAttribute("greeting", "Hello, " + name + "!");
        return "responseForm";
    }

    // handles request of the form /hello/LaunchCode - lives @ /hello/{name}
    @GetMapping("hello/{name}")
    public String helloWithPathParam(@PathVariable String name, Model model){
        model.addAttribute("greeting", "Hello, " + name + "!");
        return "responseForm";
    }

    // Handles request of the form type with POST for method - Lives @ /hello/form
    @GetMapping("form")
    public String helloForm() {
        return  "form";
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "responseForm")
    public static String createMessage(@RequestParam String name, @RequestParam String userLang, Model model){
        String html = "";
        if (userLang.equals("English")) {
            html = "Hello, " + name + "!";
        } else if (userLang.equals("Spanish")) {
            html = "Hola, " + name + "!";
        } else if (userLang.equals("French")) {
            html = "Bonjour, " + name + "!";
        } else {
            html =  "Hello, " + name + "!";
        }
        model.addAttribute("greeting", html);
        return "responseForm";
    }

    @GetMapping("hello-names")
    public String helloNames(Model model) {
        List<String> names = new ArrayList<>();
        names.add("Mellisa");
        names.add("Kaity");
        names.add("Storm");
        names.add("Jason");
        model.addAttribute("names",names);
        return "hello-list";
    }
}
