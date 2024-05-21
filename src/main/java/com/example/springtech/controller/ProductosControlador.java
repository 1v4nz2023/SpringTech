package com.example.springtech.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springtech.servicio.IProductoServicio;
import com.example.springtech.excepciones.RecursoNoEncontradoExcepcion;
import com.example.springtech.modelo.Productos;
import com.example.springtech.modelo.ProductosListResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/producto/other/dream-world/1.svg")

public class ProductosControlador {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductosControlador.class);

	@Autowired
    private IProductoServicio productoServicio;
	

    @GetMapping("/productos")
    public List<Productos> obtenerproducto(){
    	var productos = productoServicio.listarproducto();
    	productos.forEach((producto -> logger.info(producto.toString())));
		return productos;
    }
    
    @GetMapping("/producto")
    public ProductosListResponse getproducto(@RequestParam(defaultValue = "0") int offset,
                                          @RequestParam(defaultValue = "8") int limit) {
        List<Productos> productoes = obtenerproducto();
        int totalproducto = productoServicio.contarproducto();
        List<Productos> productoesToShow;

        // Limitar el número de elementos a mostrar en función del offset y el límite
        if (offset < totalproducto) {
            int endIndex = Math.min(offset + limit, totalproducto);
            productoesToShow = productoes.subList(offset, endIndex);
        } else {
            productoesToShow = Collections.emptyList(); // Si el offset supera el total de productos, no se muestra ninguno
        }

        String baseUrl = "http://localhost:8090/api/producto";
        String previousUrl = offset - limit >= 0 ? baseUrl + "?offset=" + (offset - limit) + "&limit=" + limit : null;
        String nextUrl = offset + limit < totalproducto ? baseUrl + "?offset=" + (offset + limit) + "&limit=" + limit : null;
        
        // Si el previous es null, significa que estamos en la primera página
        // Entonces, si hay productoes para mostrar en la página actual, establecemos el previous en null
        if (previousUrl == null && !productoesToShow.isEmpty()) {
            previousUrl = null;
        }

        ProductosListResponse response = new ProductosListResponse();
        response.setCount(totalproducto);
        response.setNext(nextUrl);
        response.setPrevious(previousUrl);
        response.setResults(productoesToShow);
        return response;
    }



    @GetMapping("/producto/all")
    public int getproductoCount() {
        return productoServicio.contarproducto();
    }
    
    
    @PostMapping("/productos")
    public Productos agregarProductos(@RequestBody Productos productos) {
    	if(productos.getCategoria()== null || productos.getCategoria().isEmpty() || productos.getNombreProducto()== null || productos.getNombreProducto().isEmpty()  ||
    	   productos.getDescripcion()== null || productos.getDescripcion().isEmpty() || productos.getPartNumber()== null || productos.getPartNumber().isEmpty()  ||
    	   productos.getPrecio()== null ||productos.getPrecio().isNaN() || productos.getStock()== null || productos.getUrl().isEmpty() || productos.getUrl()== null) {
    		
	        throw new RecursoNoEncontradoExcepcion("No se admiten espacios vacíos");
    		
    	}
    	
    	if(productoServicio.existeProductoConPn(productos.getPartNumber())) {
	        throw new RecursoNoEncontradoExcepcion("El producto ya se encuentra registrado, ingrese otro producto");

    	}
    	
	    logger.info("Producto a agregar: " + productos);
		return productoServicio.guardarProducto(productos);
    }
    
	@GetMapping("/productos/{partNumber}")
	public ResponseEntity<Productos> obtenerProductoporPartnumber(@PathVariable String partNumber){
		Productos producto = productoServicio.buscarProductoporPartNumber(partNumber);
		
		if(producto == null)
			throw new RecursoNoEncontradoExcepcion("No se encontro el id:" + partNumber);
		return ResponseEntity.ok(producto);
	}
	
	@GetMapping("/productos/pc/{partNumber}")
	public ResponseEntity<Productos> filtrarProductoporCategoria(@PathVariable String partNumber) {
	    Productos producto = productoServicio.buscarPartNumberAndCategoria(partNumber, "pc-oficina");

	    if (producto == null)
	        throw new RecursoNoEncontradoExcepcion("No se encontró el producto pc-oficina con el número de parte:" + partNumber);

	    return ResponseEntity.ok(producto);
	}
	
	//Sirve para nuevos productos de todas las pcs
    @GetMapping("/productos/pc")
    public ResponseEntity<List<Productos>> listarPcPorCategorias() {
        List<Productos> pcCategorias = productoServicio.listarPorCategorias();
        return ResponseEntity.ok(pcCategorias);
    }
    
	//Sirve para nuevos productos de todas las laptops
    @GetMapping("/productos/laptops")
    public ResponseEntity<List<Productos>> listarLaptops() {
        List<Productos> pcCategorias = productoServicio.listarlaptop();
        return ResponseEntity.ok(pcCategorias);
    }
    
	//Sirve para nuevos productos de todas las impresoras
    @GetMapping("/productos/impresoras")
    public ResponseEntity<List<Productos>> listarImpresoras() {
        List<Productos> pcCategorias = productoServicio.listarimpresoras();
        return ResponseEntity.ok(pcCategorias);
    }
	
	
	//Sirve para nuevos productos de pc oficina
	@GetMapping("/productos/pc-oficina")
	public ResponseEntity<List<Productos>> listarProductosPcOficina() {
	    List<Productos> productos = productoServicio.listarpcoficina("pc-oficina");
	   
	    if (productos.isEmpty())
	        throw new RecursoNoEncontradoExcepcion("No se encontraron productos en la categoría pc-oficina");

	    return ResponseEntity.ok(productos);
	}

	
	//Sirve para nuevos productos de pc gamer

	@GetMapping("/productos/pc-gamer")
	public ResponseEntity<List<Productos>>  listarProductosPcGamer() {
	    List<Productos> productos = productoServicio.listarpcoficina("pc-gamer");

	    if (productos.isEmpty())
	        throw new RecursoNoEncontradoExcepcion("No se encontraron productos en la categoría pc-gamer");

	    return ResponseEntity.ok(productos);
	}
	
	
	//Sirve para nuevos productos de pc ingenieriadiseño

	@GetMapping("/productos/pc-ingenieriadiseño")
	public ResponseEntity<List<Productos>> listarProductosPcIngenieriaDiseño() {
	    List<Productos> productos = productoServicio.listarpcoficina("pc-ingenieriadiseño");

	    if (productos.isEmpty())
	        throw new RecursoNoEncontradoExcepcion("No se encontraron productos en la categoría pc-gamer");

	    return ResponseEntity.ok(productos);
	}
	
    
	@GetMapping("/producto/pc-gamer")
	public ProductosListResponse obtenerProductosPcGamer(@RequestParam(defaultValue = "0") int offset,
	                                                      @RequestParam(defaultValue = "8") int limit) {
	    // Obtener productos por la categoría "pc-gamer"
	    List<Productos> productos = productoServicio.buscarProductosPorCategoria("pc-gamer");

	    // Total de productos en la categoría "pc-gamer"
	    int totalProductos = productos.size();

	    // Lista de productos a mostrar según el offset y el límite
	    List<Productos> productosToShow;

	    // Limitar el número de elementos a mostrar en función del offset y el límite
	    if (offset < totalProductos) {
	        int endIndex = Math.min(offset + limit, totalProductos);
	        productosToShow = productos.subList(offset, endIndex);
	    } else {
	        productosToShow = Collections.emptyList(); // Si el offset supera el total de productos, no se muestra ninguno
	    }

	    // URLs para la paginación
	    String baseUrl = "http://localhost:8090/api/producto/pc-gamer";
	    String previousUrl = offset - limit >= 0 ? baseUrl + "?offset=" + (offset - limit) + "&limit=" + limit : null;
	    String nextUrl = offset + limit < totalProductos ? baseUrl + "?offset=" + (offset + limit) + "&limit=" + limit : null;

	    // Si el previous es null, significa que estamos en la primera página
	    // Entonces, si hay productos para mostrar en la página actual, establecemos el previous en null
	    if (previousUrl == null && !productosToShow.isEmpty()) {
	        previousUrl = null;
	    }

	    // Crear la respuesta con los resultados y la información de paginación
	    ProductosListResponse response = new ProductosListResponse();
	    response.setCount(totalProductos);
	    response.setNext(nextUrl);
	    response.setPrevious(previousUrl);
	    response.setResults(productosToShow);

	    return response;
	}

    
	@GetMapping("/producto/pc-ingenieriadiseño")
	public ProductosListResponse obtenerProductosPcDiseñoIngenieria(@RequestParam(defaultValue = "0") int offset,
	                                                      @RequestParam(defaultValue = "8") int limit) {
	    // Obtener productos por la categoría "pc-gamer"
	    List<Productos> productos = productoServicio.buscarProductosPorCategoria("pc-ingenieriadiseño");

	    // Total de productos en la categoría "pc-gamer"
	    int totalProductos = productos.size();

	    // Lista de productos a mostrar según el offset y el límite
	    List<Productos> productosToShow;

	    // Limitar el número de elementos a mostrar en función del offset y el límite
	    if (offset < totalProductos) {
	        int endIndex = Math.min(offset + limit, totalProductos);
	        productosToShow = productos.subList(offset, endIndex);
	    } else {
	        productosToShow = Collections.emptyList(); // Si el offset supera el total de productos, no se muestra ninguno
	    }

	    // URLs para la paginación
	    String baseUrl = "http://localhost:8090/api/producto/pc-ingenieriadiseño";
	    String previousUrl = offset - limit >= 0 ? baseUrl + "?offset=" + (offset - limit) + "&limit=" + limit : null;
	    String nextUrl = offset + limit < totalProductos ? baseUrl + "?offset=" + (offset + limit) + "&limit=" + limit : null;

	    // Si el previous es null, significa que estamos en la primera página
	    // Entonces, si hay productos para mostrar en la página actual, establecemos el previous en null
	    if (previousUrl == null && !productosToShow.isEmpty()) {
	        previousUrl = null;
	    }

	    // Crear la respuesta con los resultados y la información de paginación
	    ProductosListResponse response = new ProductosListResponse();
	    response.setCount(totalProductos);
	    response.setNext(nextUrl);
	    response.setPrevious(previousUrl);
	    response.setResults(productosToShow);

	    return response;
	}
	
	
	@GetMapping("/producto/pc-oficina")
	public ProductosListResponse obtenerProductosPcOficina(@RequestParam(defaultValue = "0") int offset,
	                                                      @RequestParam(defaultValue = "8") int limit) {
	    // Obtener productos por la categoría "pc-gamer"
	    List<Productos> productos = productoServicio.buscarProductosPorCategoria("pc-oficina");

	    // Total de productos en la categoría "pc-gamer"
	    int totalProductos = productos.size();

	    // Lista de productos a mostrar según el offset y el límite
	    List<Productos> productosToShow;

	    // Limitar el número de elementos a mostrar en función del offset y el límite
	    if (offset < totalProductos) {
	        int endIndex = Math.min(offset + limit, totalProductos);
	        productosToShow = productos.subList(offset, endIndex);
	    } else {
	        productosToShow = Collections.emptyList(); // Si el offset supera el total de productos, no se muestra ninguno
	    }

	    // URLs para la paginación
	    String baseUrl = "http://localhost:8090/api/producto/pc-oficina";
	    String previousUrl = offset - limit >= 0 ? baseUrl + "?offset=" + (offset - limit) + "&limit=" + limit : null;
	    String nextUrl = offset + limit < totalProductos ? baseUrl + "?offset=" + (offset + limit) + "&limit=" + limit : null;

	    // Si el previous es null, significa que estamos en la primera página
	    // Entonces, si hay productos para mostrar en la página actual, establecemos el previous en null
	    if (previousUrl == null && !productosToShow.isEmpty()) {
	        previousUrl = null;
	    }

	    // Crear la respuesta con los resultados y la información de paginación
	    ProductosListResponse response = new ProductosListResponse();
	    response.setCount(totalProductos);
	    response.setNext(nextUrl);
	    response.setPrevious(previousUrl);
	    response.setResults(productosToShow);

	    return response;
	}
	
	@GetMapping("/producto/laptops")
	public ProductosListResponse obtenerLaptops(@RequestParam(defaultValue = "0") int offset,
	                                                      @RequestParam(defaultValue = "8") int limit) {
	    // Obtener productos por la categoría "pc-gamer"
	    List<Productos> productos = productoServicio.buscarProductosPorCategoria("laptop");

	    // Total de productos en la categoría "pc-gamer"
	    int totalProductos = productos.size();

	    // Lista de productos a mostrar según el offset y el límite
	    List<Productos> productosToShow;

	    // Limitar el número de elementos a mostrar en función del offset y el límite
	    if (offset < totalProductos) {
	        int endIndex = Math.min(offset + limit, totalProductos);
	        productosToShow = productos.subList(offset, endIndex);
	    } else {
	        productosToShow = Collections.emptyList(); // Si el offset supera el total de productos, no se muestra ninguno
	    }

	    // URLs para la paginación
	    String baseUrl = "http://localhost:8090/api/producto/laptops";
	    String previousUrl = offset - limit >= 0 ? baseUrl + "?offset=" + (offset - limit) + "&limit=" + limit : null;
	    String nextUrl = offset + limit < totalProductos ? baseUrl + "?offset=" + (offset + limit) + "&limit=" + limit : null;

	    // Si el previous es null, significa que estamos en la primera página
	    // Entonces, si hay productos para mostrar en la página actual, establecemos el previous en null
	    if (previousUrl == null && !productosToShow.isEmpty()) {
	        previousUrl = null;
	    }

	    // Crear la respuesta con los resultados y la información de paginación
	    ProductosListResponse response = new ProductosListResponse();
	    response.setCount(totalProductos);
	    response.setNext(nextUrl);
	    response.setPrevious(previousUrl);
	    response.setResults(productosToShow);

	    return response;
	}
	
	@GetMapping("/producto/impresoras")
	public ProductosListResponse obtenerImpresoras(@RequestParam(defaultValue = "0") int offset,
	                                                      @RequestParam(defaultValue = "8") int limit) {
	    // Obtener productos por la categoría "impresora"
	    List<Productos> productos = productoServicio.buscarProductosPorCategoria("impresora");

	    // Total de productos en la categoría "impresora"
	    int totalProductos = productos.size();

	    // Lista de productos a mostrar según el offset y el límite
	    List<Productos> productosToShow;

	    // Limitar el número de elementos a mostrar en función del offset y el límite
	    if (offset < totalProductos) {
	        int endIndex = Math.min(offset + limit, totalProductos);
	        productosToShow = productos.subList(offset, endIndex);
	    } else {
	        productosToShow = Collections.emptyList(); // Si el offset supera el total de productos, no se muestra ninguno
	    }

	    // URLs para la paginación
	    String baseUrl = "http://localhost:8090/api/producto/impresoras";
	    String previousUrl = offset - limit >= 0 ? baseUrl + "?offset=" + (offset - limit) + "&limit=" + limit : null;
	    String nextUrl = offset + limit < totalProductos ? baseUrl + "?offset=" + (offset + limit) + "&limit=" + limit : null;

	    // Si el previous es null, significa que estamos en la primera página
	    // Entonces, si hay productos para mostrar en la página actual, establecemos el previous en null
	    if (previousUrl == null && !productosToShow.isEmpty()) {
	        previousUrl = null;
	    }

	    // Crear la respuesta con los resultados y la información de paginación
	    ProductosListResponse response = new ProductosListResponse();
	    response.setCount(totalProductos);
	    response.setNext(nextUrl);
	    response.setPrevious(previousUrl);
	    response.setResults(productosToShow);

	    return response;
	}
	

    @GetMapping("/buscar")

	public ProductosListResponse buscarProductosPorNombre(@RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "8") int limit,@RequestParam(required = false) String nombreProducto) {
        if (nombreProducto == null) {
            // No se proporcionó ningún nombre de producto en la solicitud
            throw new RecursoNoEncontradoExcepcion("Por favor, ingrese un nombre de producto válido");
        }
        if (nombreProducto.isEmpty()) {
            // El nombre del producto está vacío
            throw new RecursoNoEncontradoExcepcion("Por favor, ingrese un nombre de producto");
        }
        
        List<Productos> productos = productoServicio.buscarProductosPorNombre(nombreProducto);
        
        if (productos.isEmpty()) {
            throw new RecursoNoEncontradoExcepcion("No se encontraron productos con el nombre: " + nombreProducto);
        }


        

int totalProductos = productos.size();

// Lista de productos a mostrar según el offset y el límite
List<Productos> productosToShow;

// Limitar el número de elementos a mostrar en función del offset y el límite
if (offset < totalProductos) {
int endIndex = Math.min(offset + limit, totalProductos);
productosToShow = productos.subList(offset, endIndex);
} else {
productosToShow = Collections.emptyList(); // Si el offset supera el total de productos, no se muestra ninguno
}

// URLs para la paginación
String baseUrl = "http://localhost:8090/api/producto/impresoras";
String previousUrl = offset - limit >= 0 ? baseUrl + "?offset=" + (offset - limit) + "&limit=" + limit : null;
String nextUrl = offset + limit < totalProductos ? baseUrl + "?offset=" + (offset + limit) + "&limit=" + limit : null;

// Si el previous es null, significa que estamos en la primera página
// Entonces, si hay productos para mostrar en la página actual, establecemos el previous en null
if (previousUrl == null && !productosToShow.isEmpty()) {
previousUrl = null;
}

// Crear la respuesta con los resultados y la información de paginación
ProductosListResponse response = new ProductosListResponse();
response.setCount(totalProductos);
response.setNext(nextUrl);
response.setPrevious(previousUrl);
response.setResults(productosToShow);

return response;
}
}
