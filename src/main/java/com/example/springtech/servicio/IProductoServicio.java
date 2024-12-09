package com.example.springtech.servicio;

import java.util.List;

import com.example.springtech.modelo.Productos;

public interface IProductoServicio {
	
	
	public List<Productos> listarproducto();
	
	public List<Productos> listarpcoficina(String categoria);
	public List<Productos> listarpcingenieriadiseño(String categoria);
	public List<Productos> listarpcgamer(String categoria);

	
	public List<Productos> listarPorCategorias();

    List<Productos> listarproducto(int offset, int limit);
    int contarproducto();
    
    public Productos guardarProducto(Productos producto);

    public Productos actualizarProducto(Productos producto);
    
    public boolean existeProductoConPn(String partNumber);
    
    public Productos buscarProductoporPartNumber(String partNumber);

    public Productos buscarProductoporId(Integer idProducto);

    
    public Productos buscarPartNumberAndCategoria(String partNumber, String categoria);

    List<Productos> buscarProductosPorCategoria(String categoria);

	List<Productos> listarlaptop();
	
	List<Productos> listarimpresoras();
	
    List<Productos> buscarProductosPorNombre(String nombreProducto);

	

}
