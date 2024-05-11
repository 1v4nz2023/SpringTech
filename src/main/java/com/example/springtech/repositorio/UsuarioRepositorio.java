package com.example.springtech.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springtech.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
    boolean existsByDni(String dni);
    boolean existsBycorreo(String correo);
    Usuario findByDni(String dni);
    
    
}
