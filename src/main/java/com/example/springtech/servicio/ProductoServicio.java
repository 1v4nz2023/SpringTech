package com.example.springtech.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springtech.modelo.Productos;
import com.example.springtech.repositorio.ProductoRepositorio;

@Service
public class ProductoServicio implements IProductoServicio{

	@Autowired
	private ProductoRepositorio productoRepository;
	
	@Override
	public List<Productos> listarproducto() {
		// TODO Auto-generated method stub
		return productoRepository.findAll();
	}

	@Override
	public List<Productos> listarproducto(int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int contarproducto() {
		// TODO Auto-generated method stub
        return (int) productoRepository.count();
	}

	@Override
	public Productos guardarProducto(Productos productos) {
		
		return productoRepository.save(productos);
		
		
	}

	@Override
	public boolean existeProductoConPn(String partNumber) {
		// TODO Auto-generated method stub
		return productoRepository.existsBypartNumber(partNumber);
	}

	@Override
	public Productos buscarProductoporPartNumber(String partNumber) {
		// TODO Auto-generated method stub
		Productos producto = productoRepository.findBypartNumber(partNumber);
		return producto;
	}

	@Override
	public Productos buscarPartNumberAndCategoria(String partNumber, String categoria) {
		// TODO Auto-generated method stub
	    Productos producto = productoRepository.findByPartNumberAndCategoria(partNumber, categoria);

		return producto;
	}

	@Override
	public List<Productos> buscarProductosPorCategoria(String categoria) {
		// TODO Auto-generated method stub
		return productoRepository.findByCategoria(categoria);
	}

}
