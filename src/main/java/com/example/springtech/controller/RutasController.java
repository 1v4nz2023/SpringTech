package com.example.springtech.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springtech.modelo.Productos;
import com.example.springtech.servicio.IProductoServicio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class RutasController {
	
	@Autowired
    private IProductoServicio productoServicio;
	
	
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
        model.addAttribute("currentPath", "Computadoras");

		
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
        model.addAttribute("currentPath", "Oficina");

		
		return "pc-oficina";
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
        model.addAttribute("currentPath", "Ingeniería y diseño");

		
		return "pc-ingenieriadiseño";
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
        model.addAttribute("currentPath", "Gamer");

		
		return "pc-gamer";
	}	
	

	

	@GetMapping("/computadoras/producto")
	public String producto(HttpServletRequest request,@RequestParam("id")String partNumber,Model model) {
		
	    HttpSession session = request.getSession();
	    Integer idUsuario = (Integer) session.getAttribute("idUsuario");
	    String nombre = (String) session.getAttribute("nombre");
	    String apellido = (String) session.getAttribute("apellido");
	    String rol = (String) session.getAttribute("rol");
        Productos producto = productoServicio.buscarProductoporPartNumber(partNumber);

	    // Agregar el rol al modelo para pasarlo a la vista
	    model.addAttribute("idUsuario", idUsuario);
	    model.addAttribute("nombre", nombre);
	    model.addAttribute("apellido", apellido);
	    model.addAttribute("rol", rol);
        model.addAttribute("producto", producto);
        model.addAttribute("currentPath", "computadoras/"+producto.getCategoria());
        model.addAttribute("currentPath2", "computadoras");
        model.addAttribute("listItemClasses", "breadcrumb-item");

		
		return "producto";
	}	
	
	
	@GetMapping("/laptops/producto")
	public String productolaptops(HttpServletRequest request,@RequestParam("id")String partNumber,Model model) {
		
	    HttpSession session = request.getSession();
	    Integer idUsuario = (Integer) session.getAttribute("idUsuario");
	    String nombre = (String) session.getAttribute("nombre");
	    String apellido = (String) session.getAttribute("apellido");
	    String rol = (String) session.getAttribute("rol");
	    
        Productos producto = productoServicio.buscarProductoporPartNumber(partNumber);
 

	    // Agregar el rol al modelo para pasarlo a la vista
	    model.addAttribute("idUsuario", idUsuario);
	    model.addAttribute("nombre", nombre);
	    model.addAttribute("apellido", apellido);
	    model.addAttribute("rol", rol);
        model.addAttribute("producto", producto);
        model.addAttribute("listItemClasses", "breadcrumb-item d-none");
        model.addAttribute("currentPath", "laptops");

		
		return "producto";
	}

	
	@GetMapping("/buscar/producto")
	public String buscarProductos(HttpServletRequest request,@RequestParam("id")String partNumber,Model model) {
		
	    HttpSession session = request.getSession();
	    Integer idUsuario = (Integer) session.getAttribute("idUsuario");
	    String nombre = (String) session.getAttribute("nombre");
	    String apellido = (String) session.getAttribute("apellido");
	    String rol = (String) session.getAttribute("rol");
	    
        Productos producto = productoServicio.buscarProductoporPartNumber(partNumber);
 

	    // Agregar el rol al modelo para pasarlo a la vista
	    model.addAttribute("idUsuario", idUsuario);
	    model.addAttribute("nombre", nombre);
	    model.addAttribute("apellido", apellido);
	    model.addAttribute("rol", rol);
        model.addAttribute("producto", producto);

        model.addAttribute("listItemClasses", "breadcrumb-item d-none");

        if(producto.getCategoria().equals("laptop") ||producto.getCategoria().equals("impresora") ) {
            model.addAttribute("currentPath", producto.getCategoria()+"s" );

        }
        else {
            model.addAttribute("currentPath", "computadoras/" + producto.getCategoria() );


        }
		
		return "producto";
	}

	
	@GetMapping("/impresoras/producto")
	public String productoimpresoras(HttpServletRequest request,@RequestParam("id")String partNumber,Model model) {
		
	    HttpSession session = request.getSession();
	    Integer idUsuario = (Integer) session.getAttribute("idUsuario");
	    String nombre = (String) session.getAttribute("nombre");
	    String apellido = (String) session.getAttribute("apellido");
	    String rol = (String) session.getAttribute("rol");
        Productos producto = productoServicio.buscarProductoporPartNumber(partNumber);

	    // Agregar el rol al modelo para pasarlo a la vista
	    model.addAttribute("idUsuario", idUsuario);
	    model.addAttribute("nombre", nombre);
	    model.addAttribute("apellido", apellido);
	    model.addAttribute("rol", rol);
        model.addAttribute("producto", producto);

        model.addAttribute("listItemClasses", "breadcrumb-item d-none");
        model.addAttribute("currentPath", "impresoras");
		
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
        model.addAttribute("currentPath", "Laptops");

		
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
        model.addAttribute("currentPath", "Impresoras");

		
		return "impresoras";
	}	
	
	@GetMapping("/buscar")
	public String buscar(HttpServletRequest request,Model model) {
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
        model.addAttribute("currentPath", "Resultados de la búsqueda");

		return "buscar";
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
	
	
	@GetMapping("/metodos")
	public String metodos(HttpServletRequest request,Model model) {
		
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
		
		
		return "metodosPago";
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

