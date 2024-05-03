package com.example.springtech.apicontrolador;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springtech.modelo.Usuario;
import com.example.springtech.servicio.IUsuarioServicio;

@RestController
//http://localhost:8090/api
@RequestMapping("api")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UsuarioControlador {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);
	
	
	@Autowired
	private IUsuarioServicio usuarioServicio;
	
	// http://localhost:8090/api/empleados
	@GetMapping("/usuarios")
	public List<Usuario> obtenerUsuarios(){
		var usuarios = usuarioServicio.listarUsuarios();
		usuarios.forEach((usuario -> logger.info(usuario.toString())));
		return usuarios;
		
	}
	
	
}

