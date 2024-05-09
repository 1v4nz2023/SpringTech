package com.example.springtech.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RutasController {
	
	
	@GetMapping({"/index","/","home"})
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/Login")
	public String login(Model model) {
		model.addAttribute("titulo", "INICIAR SESIÓN");
		return "iniciarSesion";
	}
	
	@GetMapping("/Registrar")
	public String registro(Model model) {
		model.addAttribute("titulo", "REGÍSTRATE AQUÍ");
		return "crearCuenta";
	}
	
	
	@GetMapping("/usuario")
	public String userperfil(Model model) {
	    return "usuario"; // Nombre de la vista a la que deseas redirigir
	}
	
	@GetMapping("/admin")
	public String adminperfil(Model model) {
	    return "admin"; // Nombre de la vista a la que deseas redirigir
	}
	
}

