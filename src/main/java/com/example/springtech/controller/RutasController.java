package com.example.springtech.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class RutasController {
	
	
	@GetMapping({"/index","/","home"})
	public String index(HttpServletRequest request,Model model) {
	    // Obtener el rol del usuario de la sesión
	    HttpSession session = request.getSession();
	    Integer idUsuario = (Integer) session.getAttribute("idUsuario");
	    String nombre = (String) session.getAttribute("nombre");
	    String apellido = (String) session.getAttribute("apellido");
	    String rol = (String) session.getAttribute("rol");

	    // Agregar el rol al modelo para pasarlo a la vista
	    model.addAttribute("idUsuario", idUsuario);
	    model.addAttribute("nombre", nombre);
	    model.addAttribute("apellido", apellido);
	    model.addAttribute("rol", rol);

		return "index";
	}
	
	@GetMapping("/computadoras")
	public String computadoras(HttpServletRequest request,Model model) {
		
	    HttpSession session = request.getSession();
	    Integer idUsuario = (Integer) session.getAttribute("idUsuario");
	    String nombre = (String) session.getAttribute("nombre");
	    String apellido = (String) session.getAttribute("apellido");
	    String rol = (String) session.getAttribute("rol");

	    // Agregar el rol al modelo para pasarlo a la vista
	    model.addAttribute("idUsuario", idUsuario);
	    model.addAttribute("nombre", nombre);
	    model.addAttribute("apellido", apellido);
	    model.addAttribute("rol", rol);
		
		
		return "computadoras";
	}

	@GetMapping("/computadoras/pc-oficina")
	public String computadorasoficina(HttpServletRequest request,Model model) {
		
	    HttpSession session = request.getSession();
	    Integer idUsuario = (Integer) session.getAttribute("idUsuario");
	    String nombre = (String) session.getAttribute("nombre");
	    String apellido = (String) session.getAttribute("apellido");
	    String rol = (String) session.getAttribute("rol");

	    // Agregar el rol al modelo para pasarlo a la vista
	    model.addAttribute("idUsuario", idUsuario);
	    model.addAttribute("nombre", nombre);
	    model.addAttribute("apellido", apellido);
	    model.addAttribute("rol", rol);
		
		
		return "pc-oficina";
	}

	//Ronaldo (metodos de pago static)
	@GetMapping("/metodosPago")
	public String mostrarMetodosPago(){
		return "metodosPago";
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
	public String userperfil(HttpServletRequest request, Model model) {
	    // Obtener el rol del usuario de la sesión
	    HttpSession session = request.getSession();
	    Integer idUsuario = (Integer) session.getAttribute("idUsuario");
	    String nombre = (String) session.getAttribute("nombre");
	    String apellido = (String) session.getAttribute("apellido");
	    String rol = (String) session.getAttribute("rol");

	    // Agregar el rol al modelo para pasarlo a la vista
	    model.addAttribute("idUsuario", idUsuario);
	    model.addAttribute("nombre", nombre);
	    model.addAttribute("apellido", apellido);
	    model.addAttribute("rol", rol);

	    return "usuario"; // Nombre de la vista a la que deseas redirigir
	}

	
	@GetMapping("/admin")
	public String adminperfil(HttpServletRequest request,Model model) {
	    // Obtener el rol del usuario de la sesión
	    HttpSession session = request.getSession();
	    Integer idUsuario = (Integer) session.getAttribute("idUsuario");
	    String nombre = (String) session.getAttribute("nombre");
	    String apellido = (String) session.getAttribute("apellido");
	    String rol = (String) session.getAttribute("rol");

	    // Agregar el rol al modelo para pasarlo a la vista
	    model.addAttribute("idUsuario", idUsuario);
	    model.addAttribute("nombre", nombre);
	    model.addAttribute("apellido", apellido);
	    model.addAttribute("rol", rol);
	    return "admin"; // Nombre de la vista a la que deseas redirigir
	}
	
	
	@GetMapping("/logout")
	public String cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
	    // Invalidar la sesión en el servidor
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }

	    // Redireccionar a la página de inicio de sesión
	    return "redirect:/Login";
	}
	

	
}

