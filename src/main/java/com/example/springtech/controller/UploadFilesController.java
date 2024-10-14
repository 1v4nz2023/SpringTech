package com.example.springtech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.springtech.excepciones.RecursoNoEncontradoExcepcion;
import com.example.springtech.modelo.Productos;
import com.example.springtech.servicio.IUploadFilesService;
import com.example.springtech.servicio.ProductoServicio;


@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = {"http://52.55.45.15", "http://localhost:8090"})


public class UploadFilesController {

    String urlServer = "http://52.55.45.15";

    @Autowired
    IUploadFilesService uploadFilesService;

    @Autowired
    ProductoServicio productoServicio;

    @PostMapping("/productos")
    public ResponseEntity<Productos> agregarProductos(@RequestPart("producto") Productos productos,
                                                      @RequestPart("imagen") MultipartFile file) throws Exception {
        if (productos.getCategoria() == null || productos.getCategoria().isEmpty() ||
                productos.getNombreProducto() == null || productos.getNombreProducto().isEmpty() ||
                productos.getDescripcion() == null || productos.getDescripcion().isEmpty() ||
                productos.getPartNumber() == null || productos.getPartNumber().isEmpty() ||
                productos.getPrecio() == null || productos.getPrecio().isNaN() ||
                productos.getStock() == null || productos.getUrl() == null || productos.getUrl().isEmpty()) {
            throw new RecursoNoEncontradoExcepcion("No se admiten espacios vacíos");
        }

        if (productoServicio.existeProductoConPn(productos.getPartNumber())) {
            throw new RecursoNoEncontradoExcepcion("El producto ya se encuentra registrado, ingrese otro producto");
        }

        // Handle file upload and set URL
        String newFileName = uploadFilesService.handleFileUpload(file);
        productos.setUrl(urlServer+"/picture/" + newFileName);

        Productos savedProducto = productoServicio.guardarProducto(productos);
        return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
    }

    @PutMapping("/productos/{idProducto}")
    public ResponseEntity<Productos> actualizarProductos(@PathVariable Integer idProducto, 
                                                         @RequestPart("producto") Productos productoRecibido, 
                                                         @RequestPart(value = "imagen", required = false) MultipartFile file) throws Exception {

        Productos producto = productoServicio.buscarProductoporId(idProducto);

        if (producto == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id:" + idProducto);
        }

        if (productoRecibido.getCategoria() == null || productoRecibido.getCategoria().isEmpty() ||
                productoRecibido.getNombreProducto() == null || productoRecibido.getNombreProducto().isEmpty() ||
                productoRecibido.getDescripcion() == null || productoRecibido.getDescripcion().isEmpty() ||
                productoRecibido.getPartNumber() == null || productoRecibido.getPartNumber().isEmpty() ||
                productoRecibido.getPrecio() == null || productoRecibido.getPrecio().isNaN() ||
                productoRecibido.getStock() == null) {
            throw new RecursoNoEncontradoExcepcion("No se admiten espacios vacíos");
        }

        producto.setNombreProducto(productoRecibido.getNombreProducto());
        producto.setPartNumber(productoRecibido.getPartNumber());
        producto.setCategoria(productoRecibido.getCategoria());
        producto.setPrecio(productoRecibido.getPrecio());
        producto.setStock(productoRecibido.getStock());
        producto.setDescripcion(productoRecibido.getDescripcion());
        producto.setMarca(productoRecibido.getMarca());
        producto.setGarantia(productoRecibido.getGarantia());

        // Verificar si el archivo no está vacío
        if (file != null && !file.isEmpty()) {
            String url = urlServer+ "/picture/" + uploadFilesService.handleFileUpload(file);
            producto.setUrl(url);
        }

        productoServicio.actualizarProducto(producto);

        return ResponseEntity.ok(producto);
    }
}

