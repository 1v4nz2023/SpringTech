package com.example.springtech.controller;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:8090")

public class UsuarioControlador {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);
	
	
	@Autowired
	private IUsuarioServicio usuarioServicio;
	
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
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioRecibido) {
	    Usuario usuario = usuarioServicio.buscarUsuarioPorId(id);
	    if (usuario == null) {
	        throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
	    }

	    // Verificar si se proporciona la contraseña actual y la nueva contraseña
	    if (!StringUtils.isEmpty(usuarioRecibido.getPassword()) && !StringUtils.isEmpty(usuarioRecibido.getNewpassword())) {
	        // Verificar si la contraseña actual es correcta
	        String hashedPassword = hashPassword(usuarioRecibido.getPassword());
	        if (!hashedPassword.equals(usuario.getPassword())) {
	            throw new RecursoNoEncontradoExcepcion("La contraseña ingresada no coincide con la almacenada");
	        }

	        // Encriptar la nueva contraseña antes de guardarla
	        String hashedNewPassword = hashPassword(usuarioRecibido.getNewpassword());
	        usuario.setPassword(hashedNewPassword);
	    } else if (!StringUtils.isEmpty(usuarioRecibido.getPassword()) || !StringUtils.isEmpty(usuarioRecibido.getNewpassword())) {
	        // Si se proporciona solo una de las contraseñas, lanzar una excepción
			throw new RecursoNoEncontradoExcepcion("Debe proporcionar tanto la contraseña actual como la nueva contraseña");

	    }

	    // Actualizar los demás datos del usuario si no hay campos vacíos
	    if (!StringUtils.isEmpty(usuarioRecibido.getNombre())) {
	        usuario.setNombre(usuarioRecibido.getNombre());
	    }
	    if (!StringUtils.isEmpty(usuarioRecibido.getApellidos())) {
	        usuario.setApellidos(usuarioRecibido.getApellidos());
	    }
	    if (!StringUtils.isEmpty(usuarioRecibido.getCorreo())) {
	        usuario.setCorreo(usuarioRecibido.getCorreo());
	    }
	    if (!StringUtils.isEmpty(usuarioRecibido.getRol())) {
	        usuario.setRol(usuarioRecibido.getRol());
	    }

	    // Guardar el usuario solo si no hay campos vacíos
	    if (!StringUtils.isEmpty(usuarioRecibido.getPassword()) || !StringUtils.isEmpty(usuarioRecibido.getNewpassword()) ||
	        !StringUtils.isEmpty(usuarioRecibido.getNombre()) || !StringUtils.isEmpty(usuarioRecibido.getApellidos()) ||
	        !StringUtils.isEmpty(usuarioRecibido.getCorreo()) || !StringUtils.isEmpty(usuarioRecibido.getRol())) {
	        usuarioServicio.actualizarUsuario(usuario);
	    } else {
			throw new RecursoNoEncontradoExcepcion("Debe proporcionar al menos un campo para actualizar");

	    }

	    return ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<Usuario> actualizarUsuarios(@PathVariable Integer id, @RequestBody Usuario usuarioRecibido) {
	    Usuario usuario = usuarioServicio.buscarUsuarioPorId(id);
	    if (usuario == null)
	        throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);

	    usuario.setNombre(usuarioRecibido.getNombre());
	    usuario.setApellidos(usuarioRecibido.getApellidos());
	    usuario.setCorreo(usuarioRecibido.getCorreo());
	    usuario.setRol(usuarioRecibido.getRol());

	    // Verificar si la contraseña recibida es diferente a la contraseña almacenada en la BD
	    if (!usuarioRecibido.getPassword().equals(usuario.getPassword())) {
	        // Encriptar la nueva contraseña antes de guardarla
	        String hashedPassword = hashPassword(usuarioRecibido.getPassword());
	        usuario.setPassword(hashedPassword);
	    }
			//last commit
	    usuarioServicio.actualizarUsuario(usuario);
	    return ResponseEntity.ok(usuario);
	}

	

	   public String hashPassword(String password) {
	        try {
	            MessageDigest digest = MessageDigest.getInstance("SHA-256");
	            byte[] hashBytes = digest.digest(password.getBytes());
	            BigInteger hashBigInteger = new BigInteger(1, hashBytes);
	            String hashedPassword = hashBigInteger.toString(16);
	            while (hashedPassword.length() < 64) {
	                hashedPassword = "0" + hashedPassword;
	            }
	            return hashedPassword;
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	            return null;
	        }
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

