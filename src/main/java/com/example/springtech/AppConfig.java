package com.example.springtech;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.springtech.service.IServicio;
import com.example.springtech.service.MiServicioComplejo;
import com.example.springtech.service.MiServicioSimple;

@Configuration
public class AppConfig {
	@Bean("miServicioSimple")
	
	public IServicio registrarMiServicioSimple() {
		return new MiServicioSimple();
	}
	@Bean("miServicioComplejo")
	@Primary
	public IServicio registrarMiServicioComplejo() {
		return new MiServicioComplejo();
	}
}
