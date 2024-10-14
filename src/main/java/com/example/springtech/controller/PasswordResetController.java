package com.example.springtech.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springtech.excepciones.RecursoNoEncontradoExcepcion;
import com.example.springtech.modelo.PasswordResetRequest;
import com.example.springtech.modelo.Usuario;
import com.example.springtech.servicio.UsuarioServicio;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8090")

public class PasswordResetController {
	
    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/reset-password")
    public ResponseEntity<Usuario> resetPassword(@RequestBody PasswordResetRequest request) {
    	
    	
    	if(request.getEmail()==null || request.getEmail().isEmpty() || request.getNewPassword()==null || request.getNewPassword().isEmpty()) {
    		throw new RecursoNoEncontradoExcepcion("No se admiten campos vacíos");

    	}
    	
        Usuario usuario = usuarioServicio.buscarUsuarioPorCorreo(request.getEmail());
        if (usuario == null) {
    		throw new RecursoNoEncontradoExcepcion("No se encontro el usuario");
        }

        // Lógica para restablecer la contraseña
        String hashedNewPassword = hashPassword(request.getNewPassword());
        usuario.setPassword(hashedNewPassword);
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

}