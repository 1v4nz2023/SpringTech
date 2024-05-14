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
	
	@GetMapping("/computadoras/pc-gamer")
	public String computadorasgamer(HttpServletRequest request,Model model) {
		
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
		
		
		return "pc-gamer";
	}	
	
	@GetMapping("/computadoras/pc-ingenieriadiseño")
	public String computadorasingenieriadiseño(HttpServletRequest request,Model model) {
		
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
		
		
		return "pc-ingenieriadiseño";
	}	
	
	
	
	
	@GetMapping("/computadoras/producto")
	public String producto(HttpServletRequest request,Model model) {
		
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
		
		
		return "producto";
	}	
	
	
	@GetMapping("/laptops/producto")
	public String productolaptops(HttpServletRequest request,Model model) {
		
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
		
		
		return "producto";
	}
	
	
	@GetMapping("/laptops")
	public String laptops(HttpServletRequest request,Model model) {
		
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
		
		
		return "laptops";
	}	
	
	
	
	@GetMapping("/impresoras")
	public String impresoras(HttpServletRequest request,Model model) {
		
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
		
		
		return "impresoras";
	}	
	
	
	@GetMapping("/garantía")
	public String garantia(HttpServletRequest request,Model model) {
		
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
		
		
		return "garantía";
	}	
	
	
	
	@GetMapping("/formulario-garantia")
	public String formularioGarantia(HttpServletRequest request,Model model) {
		
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
		
		
		return "formulario-garantia";
	}	
	
	
	
	@GetMapping("/addProductos")
	public String addProductos(HttpServletRequest request,Model model) {
		
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
		
		
		return "addProductos";
	}	
	
	
	@GetMapping("/editProductos")
	public String editProductos(HttpServletRequest request,Model model) {
		
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
		
		
		return "editProductos";
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/listaUsuarios")
	public String mostrarUsuarios(HttpServletRequest request,Model model) {
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
		return "listaUsuarios";
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

