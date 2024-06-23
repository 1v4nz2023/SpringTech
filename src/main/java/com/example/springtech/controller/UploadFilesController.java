package com.example.springtech.controller;

import com.example.springtech.excepciones.RecursoNoEncontradoExcepcion;
import com.example.springtech.modelo.Productos;
import com.example.springtech.servicio.IUploadFilesService;
import com.example.springtech.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadFilesController {

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
        productos.setUrl("http://localhost:8090/picture/" + newFileName);

        Productos savedProducto = productoServicio.guardarProducto(productos);
        return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
    }
}
