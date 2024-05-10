package com.example.springtech.servicio;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springtech.modelo.Usuario;
import com.example.springtech.repositorio.UsuarioRepositorio;


@Service
public class UsuarioServicio implements IUsuarioServicio {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public List<Usuario> listarUsuarios() {
		// TODO Auto-generated method stub
		return usuarioRepositorio.findAll();
	}

	@Override
	public Usuario buscarUsuarioPorId(Integer idUsuario) {
		// TODO Auto-generated method stub
		
		Usuario usuario = usuarioRepositorio.findById(idUsuario).orElse(null);
		return usuario;
		
	}

	@Override
	public Usuario guardarUsuario(Usuario usuario) {
	    Usuario usuarioExistente = usuarioRepositorio.findById(usuario.getIdUsuario()).orElse(null);
	    if (usuarioExistente != null) {
	        // Verificar si la contraseña ha sido modificada
	        if (!usuario.getPassword().equals(usuarioExistente.getPassword())) {
	            // Encriptar la nueva contraseña antes de guardar el usuario
	            String hashedPassword = hashPassword(usuario.getPassword());
	            usuario.setPassword(hashedPassword);
	        }
	    }
	    return usuarioRepositorio.save(usuario);
	}


	@Override
	public void eliminarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioRepositorio.delete(usuario);
	}

	@Override
	public boolean existeUsuarioConDni(String dni) {
	    return usuarioRepositorio.existsByDni(dni);

	}

	@Override
	public boolean existeUsuarioConCorreo(String correo) {
		// TODO Auto-generated method stub
		return usuarioRepositorio.existsBycorreo(correo);
	}

    @Override
    public Usuario autenticarUsuario(String dni, String password) {
        Usuario usuario = usuarioRepositorio.findByDni(dni);
        if (usuario != null) {
            String hashedPassword = hashPassword(password);
            if (hashedPassword.equals(usuario.getPassword())) {
                return usuario;
            }
        }
        return null;
    }


	@Override
	public Usuario buscarUsuarioporDNI(String dni) {
		// TODO Auto-generated method stub
		Usuario usuario = usuarioRepositorio.findByDni(dni);
		return usuario;
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
