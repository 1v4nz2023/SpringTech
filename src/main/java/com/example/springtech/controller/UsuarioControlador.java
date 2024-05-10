package com.example.springtech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springtech.excepciones.RecursoNoEncontradoExcepcion;
import com.example.springtech.modelo.Usuario;
import com.example.springtech.servicio.IUsuarioServicio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
	
	@PostMapping("/usuarios")
	public Usuario agregarUsuario(@RequestBody Usuario usuario) {
		
		if(usuario.getNombre() == null || usuario.getNombre().isEmpty() ||
				   usuario.getApellidos() == null || usuario.getApellidos().isEmpty() ||
				   usuario.getDni() == null || usuario.getDni().isEmpty() ||
				   usuario.getCorreo() == null || usuario.getCorreo().isEmpty() ||
				   usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
				    throw new RecursoNoEncontradoExcepcion("No se admiten espacios vacíos");
				}

		
		
	    // Verificar si el DNI está duplicado
	    if (usuarioServicio.existeUsuarioConDni(usuario.getDni())) {
	        throw new RecursoNoEncontradoExcepcion("El DNI ya está registrado");
	    }
	    
	    if (usuarioServicio.existeUsuarioConCorreo(usuario.getCorreo())) {
	        throw new RecursoNoEncontradoExcepcion("El correo ya está registrado");
	    }	    
	    

	    logger.info("Usuario a agregar: " + usuario);
	    return usuarioServicio.guardarUsuario(usuario);
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Integer id){
		Usuario usuario = usuarioServicio.buscarUsuarioPorId(id);
		if(usuario == null)
			throw new RecursoNoEncontradoExcepcion("No se encontro el id:" + id);
		return ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioRecibido){
		
		Usuario usuario = usuarioServicio.buscarUsuarioPorId(id);
		if (usuario == null)
			throw new RecursoNoEncontradoExcepcion("El id recibido no existe: "+id);
		
	
		usuario.setNombre(usuarioRecibido.getNombre());
		usuario.setApellidos(usuarioRecibido.getApellidos());
		usuario.setCorreo(usuarioRecibido.getCorreo());
		usuario.setPassword(usuarioRecibido.getPassword());
		usuario.setRol(usuarioRecibido.getRol());
		usuarioServicio.guardarUsuario(usuario);
		return ResponseEntity.ok(usuario);
	}
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Map<String,Boolean>>
		eliminarUsuario(@PathVariable Integer id){
		
		if(id.equals(1)) {
			throw new RecursoNoEncontradoExcepcion("No puedes borrar este usuario");

		}
		
		
		Usuario usuario = usuarioServicio.buscarUsuarioPorId(id);
		
		if(usuario == null)
			throw new RecursoNoEncontradoExcepcion("El id recibido no existe :"+id);
		
		
		usuarioServicio.eliminarUsuario(usuario);
		
		// Json {"eliminado":"true}
		
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminado", Boolean.TRUE);
		
		return ResponseEntity.ok(respuesta);
		
	}
	
	@GetMapping("/user/{dni}")
	public ResponseEntity<Usuario> obtenerUsuarioPorDni(@PathVariable String dni){
		Usuario usuario = usuarioServicio.buscarUsuarioporDNI(dni);
		if(usuario == null)
			throw new RecursoNoEncontradoExcepcion("No se encontro el id:" + dni);
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> iniciarSesion(@RequestBody Usuario usuario, HttpServletRequest request) {
	    String dni = usuario.getDni();
	    String password = usuario.getPassword();
	    Usuario usuarioAutenticado = usuarioServicio.autenticarUsuario(dni, password);
	    if (usuarioAutenticado == null) {
	        throw new RecursoNoEncontradoExcepcion("Inicio de Sesión fallido");
	    }
	    
	    // Guardar el rol del usuario en la sesión
	    HttpSession session = request.getSession();
	    session.setAttribute("idUsuario", usuarioAutenticado.getIdUsuario());
	    session.setAttribute("nombre", usuarioAutenticado.getNombre());
	    session.setAttribute("apellido", usuarioAutenticado.getApellidos());
   	    session.setAttribute("rol", usuarioAutenticado.getRol());

	    Map<String, Object> response = new HashMap<>();
	    response.put("idUsuario", usuarioAutenticado.getIdUsuario());
	    response.put("nombre", usuarioAutenticado.getNombre());
	    response.put("apellido", usuarioAutenticado.getApellidos());
	    response.put("rol", usuarioAutenticado.getRol());
	    
	    response.put("ruta", "admin".equals(usuarioAutenticado.getRol()) ? "admin" : "usuario");
	    
	    return ResponseEntity.ok(response);

	}

}

