package com.example.springtech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springtech.modelo.Pedido;
import com.example.springtech.modelo.Productos;
import com.example.springtech.servicio.IPedidoServicio;
import com.example.springtech.servicio.IProductoServicio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@CrossOrigin(origins = "http://localhost:8090")

public class RutasController {

    @Autowired
    private IProductoServicio productoServicio;
    
    
    @Autowired
    private IPedidoServicio pedidoServicio;

    @GetMapping({"/index", "/", "home"})
    public String index(HttpServletRequest request, HttpServletResponse response,Model model) {
        setupModelWithSessionAttributes(request, model);
        setNoCacheHeaders(response);

        return "index";
    }
    

    @GetMapping("/computadoras")
    public String computadoras(HttpServletRequest request,  HttpServletResponse response,Model model) {
        setupModelWithSessionAttributes(request, model);
        model.addAttribute("currentPath", "Computadoras");
        setNoCacheHeaders(response);

        return "computadoras";
    }

    @GetMapping("/computadoras/pc-oficina")
    public String computadorasOficina(HttpServletRequest request,HttpServletResponse response, Model model) {
        setupModelWithSessionAttributes(request, model);
        model.addAttribute("currentPath", "Oficina");
        setNoCacheHeaders(response);

        return "pc-oficina";
    }

    @GetMapping("/computadoras/pc-ingenieriadiseño")
    public String computadorasIngenieriaDiseno(HttpServletRequest request,HttpServletResponse response, Model model) {
        setupModelWithSessionAttributes(request, model);
        model.addAttribute("currentPath", "Ingeniería y diseño");
        setNoCacheHeaders(response);

        return "pc-ingenieriadiseño";
    }

    @GetMapping("/computadoras/pc-gamer")
    public String computadorasGamer(HttpServletRequest request, HttpServletResponse response,Model model) {
        setupModelWithSessionAttributes(request, model);
        model.addAttribute("currentPath", "Gamer");
        setNoCacheHeaders(response);

        return "pc-gamer";
    }

    @GetMapping("/computadoras/producto")
    public String producto(HttpServletRequest request, @RequestParam("id") String partNumber,HttpServletResponse response, Model model) {
        setupModelWithSessionAttributes(request, model);
        Productos producto = productoServicio.buscarProductoporPartNumber(partNumber);
        model.addAttribute("producto", producto);
        model.addAttribute("currentPath", "computadoras/" + producto.getCategoria());
        model.addAttribute("currentPath2", "computadoras");
        model.addAttribute("listItemClasses", "breadcrumb-item");
        setNoCacheHeaders(response);

        return "producto";
    }

    @GetMapping("/laptops/producto")
    public String productoLaptops(HttpServletRequest request, @RequestParam("id") String partNumber, HttpServletResponse response,Model model) {
        setupModelWithSessionAttributes(request, model);
        Productos producto = productoServicio.buscarProductoporPartNumber(partNumber);
        model.addAttribute("producto", producto);
        model.addAttribute("listItemClasses", "breadcrumb-item d-none");
        model.addAttribute("currentPath", "laptops");
        setNoCacheHeaders(response);

        return "producto";
    }

    @GetMapping("/buscar/producto")
    public String buscarProductos(HttpServletRequest request, @RequestParam("id") String partNumber,HttpServletResponse response, Model model) {
        setupModelWithSessionAttributes(request, model);
        Productos producto = productoServicio.buscarProductoporPartNumber(partNumber);
        model.addAttribute("producto", producto);
        model.addAttribute("listItemClasses", "breadcrumb-item d-none");
        if (producto.getCategoria().equals("laptop") || producto.getCategoria().equals("impresora")) {
            model.addAttribute("currentPath", producto.getCategoria() + "s");
        } else {
            model.addAttribute("currentPath", "computadoras/" + producto.getCategoria());
        }
        setNoCacheHeaders(response);

        return "producto";
    }

    @GetMapping("/impresoras/producto")
    public String productoImpresoras(HttpServletRequest request, @RequestParam("id") String partNumber,HttpServletResponse response, Model model) {
        setupModelWithSessionAttributes(request, model);
        Productos producto = productoServicio.buscarProductoporPartNumber(partNumber);
        List<Pedido> pedido = pedidoServicio.buscarPedidosPorPartNumber(partNumber);
        model.addAttribute("producto", producto);
        model.addAttribute("pedido", pedido);
        model.addAttribute("listItemClasses", "breadcrumb-item d-none");
        model.addAttribute("currentPath", "impresoras");


        setNoCacheHeaders(response);

        return "producto";
    }

    @GetMapping("/laptops")
    public String laptops(HttpServletRequest request, HttpServletResponse response,Model model) {
        setupModelWithSessionAttributes(request, model);
        model.addAttribute("currentPath", "Laptops");
        setNoCacheHeaders(response);

        return "laptops";
    }

    @GetMapping("/impresoras")
    public String impresoras(HttpServletRequest request,HttpServletResponse response, Model model) {
        setupModelWithSessionAttributes(request, model);
        model.addAttribute("currentPath", "Impresoras");
        setNoCacheHeaders(response);

        return "impresoras";
    }

    @GetMapping("/buscar")
    public String buscar(HttpServletRequest request, HttpServletResponse response,Model model) {
        setupModelWithSessionAttributes(request, model);
        model.addAttribute("currentPath", "Resultados de la búsqueda");
        setNoCacheHeaders(response);

        return "buscar";
    }

    @GetMapping("/garantía")
    public String garantia(HttpServletRequest request, HttpServletResponse response,Model model) {
        setupModelWithSessionAttributes(request, model);
        setNoCacheHeaders(response);

        return "garantía";
    }

    @GetMapping("/metodos")
    public String metodos(HttpServletRequest request, HttpServletResponse response,Model model) {
        setupModelWithSessionAttributes(request, model);
        setNoCacheHeaders(response);

        return "metodosPago";
    }

    @GetMapping("/formulario-garantia")
    public String formularioGarantia(HttpServletRequest request, HttpServletResponse response,Model model) {
        setupModelWithSessionAttributes(request, model);
        setNoCacheHeaders(response);

        return "formulario-garantia";
    }

    @GetMapping("/admin-crearProducto")
    public String addProductos(HttpServletRequest request, HttpServletResponse response,Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("idUsuario") == null) {
            return "redirect:/Login";
        }
        setupModelWithSessionAttributes(request, model);
        setNoCacheHeaders(response);

        return "admin-crearProducto";
    }

    @GetMapping("/editProductos")
    public String editProductos(HttpServletRequest request, HttpServletResponse response,Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("idUsuario") == null) {
            return "redirect:/Login";
        }
        setupModelWithSessionAttributes(request, model);
        setNoCacheHeaders(response);

        return "editProductos";
    }

    @GetMapping("/listaUsuarios")
    public String mostrarUsuarios(HttpServletRequest request, HttpServletResponse response,Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("idUsuario") == null) {
            return "redirect:/Login";
        }
        setupModelWithSessionAttributes(request, model);
        setNoCacheHeaders(response);

        
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
    public String userPerfil(HttpServletRequest request,HttpServletResponse response, Model model) {
        if (!setupModelWithSessionAttributes(request, model, "usuario")) {
            return "redirect:/Login";
        }
        setNoCacheHeaders(response);

        return "usuario";
    }

    @GetMapping("/admin")
    public String adminPerfil(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!setupModelWithSessionAttributes(request, model, "admin")) {
            return "redirect:/Login";
        }
        
        setNoCacheHeaders(response);

        return "admin";
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/Login";
    }

    private boolean setupModelWithSessionAttributes(HttpServletRequest request, Model model) {
        return setupModelWithSessionAttributes(request, model, null);
    }

    private boolean setupModelWithSessionAttributes(HttpServletRequest request, Model model, String requiredRole) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("idUsuario") == null) {
            model.addAttribute("class", "btn btn-light text-decoration-none");
            model.addAttribute("dropdown", "btn btn-light dropdown-toggle text-success d-none");
            return false;
        } else {
            model.addAttribute("class", "d-none");
        }

        Integer idUsuario = (Integer) session.getAttribute("idUsuario");
        String nombre = (String) session.getAttribute("nombre");
        String apellido = (String) session.getAttribute("apellido");
        String rol = (String) session.getAttribute("rol");

        if (requiredRole != null && !requiredRole.equals(rol)) {
            session.invalidate();
            return false;
        }

        model.addAttribute("idUsuario", idUsuario);
        model.addAttribute("nombre", nombre);
        model.addAttribute("apellido", apellido);
        model.addAttribute("rol", rol);

        return true;
    }
    
    private void setNoCacheHeaders(HttpServletResponse response) {
        // Configura las cabeceras de respuesta para evitar el almacenamiento en caché
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.
    }
}
