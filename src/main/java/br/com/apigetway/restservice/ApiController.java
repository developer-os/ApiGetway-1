package br.com.apigetway.restservice;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @RequestMapping({ "/greeting" })
    public String welcomePage() {
         return "Welcome!";
    }
}
