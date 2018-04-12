package com.qwj.mychat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@MapperScan("com.qwj.mychat.mapper")
@SpringBootApplication
@Controller
public class MychatApplication {

    public static void main(String[] args) {
        SpringApplication.run(MychatApplication.class, args);
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }


}
