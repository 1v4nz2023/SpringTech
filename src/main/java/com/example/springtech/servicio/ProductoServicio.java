package com.example.springtech.servicio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

        // Obtener la lista de productos desde el repositorio
        List<Productos> productos = productoRepository.findAll();

        // Ordenar la lista por ID de mayor a menor
        List<Productos> productosOrdenados = productos.stream()
                .sorted(Comparator.comparingInt(Productos::getIdProducto).reversed())
                .collect(Collectors.toList());

        return productosOrdenados;
    }
	
	@Override
	public List<Productos> listarpcoficina(String categoria) {
        // Obtener la lista de productos desde el repositorio
        List<Productos> productos = productoRepository.findByCategoria(categoria);

        // Ordenar la lista por ID de mayor a menor
        List<Productos> productosOrdenados = productos.stream()
                .sorted(Comparator.comparingInt(Productos::getIdProducto).reversed())
                .collect(Collectors.toList());

        return productosOrdenados;
	}



	@Override
	public List<Productos> listarpcingenieriadiseño(String categoria) {
        // Obtener la lista de productos desde el repositorio
        List<Productos> productos = productoRepository.findByCategoria(categoria);

        // Ordenar la lista por ID de mayor a menor
        List<Productos> productosOrdenados = productos.stream()
                .sorted(Comparator.comparingInt(Productos::getIdProducto).reversed())
                .collect(Collectors.toList());

        return productosOrdenados;
	}



	@Override
	public List<Productos> listarpcgamer(String categoria) {
        // Obtener la lista de productos desde el repositorio
        List<Productos> productos = productoRepository.findByCategoria(categoria);

        // Ordenar la lista por ID de mayor a menor
        List<Productos> productosOrdenados = productos.stream()
                .sorted(Comparator.comparingInt(Productos::getIdProducto).reversed())
                .collect(Collectors.toList());

        return productosOrdenados;	}
		


	@Override
	public List<Productos> listarproducto(int offset, int limit) {
		return null;
	}

	@Override
	public int contarproducto() {
        return (int) productoRepository.count();
	}

	@Override
	public Productos guardarProducto(Productos productos) {
		
		return productoRepository.save(productos);
		
		
	}

	@Override
	public boolean existeProductoConPn(String partNumber) {
		return productoRepository.existsBypartNumber(partNumber);
	}

	@Override
	public Productos buscarProductoporPartNumber(String partNumber) {
		Productos producto = productoRepository.findBypartNumber(partNumber);
		return producto;
	}

	@Override
	public Productos buscarPartNumberAndCategoria(String partNumber, String categoria) {
	    Productos producto = productoRepository.findByPartNumberAndCategoria(partNumber, categoria);

		return producto;
	}

	@Override
	public List<Productos> buscarProductosPorCategoria(String categoria) {
		return productoRepository.findByCategoria(categoria);
	}

	@Override
	public List<Productos> listarPorCategorias() {

        // Obtener productos por categorías específicas
        List<Productos> pcOficina = productoRepository.findByCategoria("pc-oficina");
        List<Productos> pcGamer = productoRepository.findByCategoria("pc-gamer");
        List<Productos> pcIngenieriaDiseno = productoRepository.findByCategoria("pc-ingenieriadiseño");

        // Unir las listas de productos de todas las categorías específicas
        List<Productos> pcCategorias = new ArrayList<>();
        pcCategorias.addAll(pcOficina);
        pcCategorias.addAll(pcGamer);
        pcCategorias.addAll(pcIngenieriaDiseno);

        // Ordenar la lista de productos por ID de mayor a menor
        pcCategorias.sort((p1, p2) -> p2.getIdProducto().compareTo(p1.getIdProducto()));

        return pcCategorias;
	}


	@Override
	public List<Productos> listarlaptop() {
        // Obtener la lista de productos desde el repositorio
        List<Productos> productos = productoRepository.findByCategoria("laptop");

        // Ordenar la lista por ID de mayor a menor
        List<Productos> productosOrdenados = productos.stream()
                .sorted(Comparator.comparingInt(Productos::getIdProducto).reversed())
                .collect(Collectors.toList());

        return productosOrdenados;	}

	@Override
	public List<Productos> listarimpresoras() {
        // Obtener la lista de productos desde el repositorio
        List<Productos> productos = productoRepository.findByCategoria("impresora");

        // Ordenar la lista por ID de mayor a menor
        List<Productos> productosOrdenados = productos.stream()
                .sorted(Comparator.comparingInt(Productos::getIdProducto).reversed())
                .collect(Collectors.toList());

        return productosOrdenados;
	}


}
