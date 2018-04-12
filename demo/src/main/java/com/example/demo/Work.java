package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Work {

    @RequestMapping(value = "/work", method = RequestMethod.GET)
    public String todo(){
        return "做事！";
    }

}
