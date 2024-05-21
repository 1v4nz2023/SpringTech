package com.example.springtech.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springtech.modelo.Productos;

public interface ProductoRepositorio extends JpaRepository<Productos, Integer>{

	boolean existsBypartNumber(String partNumber);
	Productos findBypartNumber(String partNumber);
    Productos findBycategoria (String categoria);
    List<Productos> findByCategoria(String categoria);
    Productos findByPartNumberAndCategoria(String partNumber, String categoria);
    List<Productos> findByNombreProductoContainingIgnoreCase(String nombreProducto);


}
