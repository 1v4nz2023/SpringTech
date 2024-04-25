package com.example.springtech.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springtech.model.dao.UsuarioDAO;
import com.example.springtech.model.entity.UsuarioEntity;

@Controller
public class IndexController {
	
	UsuarioDAO objDAO = new UsuarioDAO();

	@GetMapping({"/index","/","home"})
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/registro")
	public String registro(Model model) {
		model.addAttribute("titulo", "Regístrate aquí");
		return "frmRegistrar";
	}
	
	
	@PostMapping("/registrar")
	public String registrarUsuario(@Validated UsuarioEntity usuario) {
		if(usuario!=null) {
			objDAO.registrarUsuario(usuario);
		}
		return "frmRegistrar";
	}
	
	@GetMapping("/listaUsuarios")
	public String getLista(Model model) {
		List<UsuarioEntity> listUser = new ArrayList<>();
		listUser = objDAO.getUsuarios();
		model.addAttribute("listaU", listUser);
		return "frmListaUsuarios";
	}
	
	
		
}

