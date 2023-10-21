package br.com.edu.fiap.techchallengelanchonete.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/teste")
    public String teste()
    {
        return "teste";
    }
}
