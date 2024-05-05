package com.example.springtech.servicio;

import java.util.List;

import com.example.springtech.modelo.Usuario;

public interface IUsuarioServicio {
	
	public List<Usuario> listarUsuarios();
	
	public Usuario buscarUsuarioPorId(Integer idUsuario);
	
	
	public Usuario guardarUsuario(Usuario usuario);
	
	
	public void eliminarUsuario(Usuario usuario);

	public boolean existeUsuarioConDni(Integer dni);

	public boolean existeUsuarioConCorreo(String correo);

	

}
