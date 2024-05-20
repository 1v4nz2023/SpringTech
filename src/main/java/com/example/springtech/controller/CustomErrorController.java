package com.example.springtech.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        // Redirigir al index en caso de error
        return "redirect:/";
    }
    
    // Método adicional para desactivar el mapeo de "/error"
    public String getErrorPath() {
        return "/error";
    }
}
