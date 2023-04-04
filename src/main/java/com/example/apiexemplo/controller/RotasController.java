package com.example.apiexemplo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RotasController {
    @GetMapping("/listaClientes")
    public String listaClientes(){

        return "/listaClientes";
    }
}
