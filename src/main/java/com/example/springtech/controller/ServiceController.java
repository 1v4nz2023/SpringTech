package com.example.springtech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springtech.service.IServicio;

@Controller
public class ServiceController {
	
	@Autowired
	private IServicio servicio;
	
	@GetMapping("/index2")
	public String index2(Model model) {
		model.addAttribute("objeto", servicio.operacion());
		return "index2";
	}

	public void setServicio(IServicio servicio) {
		this.servicio = servicio;
	}

	public ServiceController(IServicio servicio) {
		super();
		this.servicio = servicio;
	}
	
	

}
