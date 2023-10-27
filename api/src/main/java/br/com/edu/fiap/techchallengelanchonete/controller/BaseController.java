package br.com.edu.fiap.techchallengelanchonete.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @GetMapping("/teste")
    public String teste()
    {
        return "teste";
    }
}
