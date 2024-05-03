package com.example.springtech.servicio;

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
		// TODO Auto-generated method stub
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public void eliminarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioRepositorio.delete(usuario);
	}

}
