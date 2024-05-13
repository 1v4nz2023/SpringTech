package com.example.springtech.servicio;

import java.util.List;

import com.example.springtech.modelo.Productos;

public interface IProductoServicio {
	
	
	public List<Productos> listarproducto();

    List<Productos> listarproducto(int offset, int limit);
    int contarproducto();
    
    public Productos guardarProducto(Productos producto);
    
    public boolean existeProductoConPn(String partNumber);
    
    public Productos buscarProductoporPartNumber(String partNumber);
    
    public Productos buscarPartNumberAndCategoria(String partNumber, String categoria);

    List<Productos> buscarProductosPorCategoria(String categoria);
}
