package com.example.springtech.controller;

import com.example.springtech.excepciones.RecursoNoEncontradoExcepcion;
import com.example.springtech.modelo.Pedido;
import com.example.springtech.servicio.PedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PedidoController {

    @Autowired
    private PedidoServicio pedidoServicio;

    @GetMapping("/pedidos")
    public List<Pedido> getAllPedidos() {
        return pedidoServicio.getAllPedidos();
    }

    @GetMapping("/pedidos/{idPedido}")
    public ResponseEntity<Pedido> getPedidoPorId(@PathVariable Integer idPedido) {
    	Pedido pedido = pedidoServicio.getPedidoPorId(idPedido);
    	
    	if(pedido == null)
    		throw new RecursoNoEncontradoExcepcion("No se encontro el pedido con el id:"+idPedido);
    	return ResponseEntity.ok(pedido);
    
    }
    
    
    @GetMapping("/pedidos/partNumber/{partNumber}")
    public ResponseEntity<List<Pedido>> buscarPedidosPorPartNumber(@PathVariable String partNumber) {
    	List<Pedido> pedido = pedidoServicio.buscarPedidosPorPartNumber(partNumber);
    	
    	if(pedido == null)
    		throw new RecursoNoEncontradoExcepcion("No se encontro el pedido con el p/n:"+partNumber);
    	return ResponseEntity.ok(pedido);
    
    }
    
    

    @PostMapping("/pedidos")
    public Pedido guardarPedido(@RequestBody Pedido pedido) {
        return pedidoServicio.guardarPedido(pedido);
    }

    @PutMapping("/pedidos/{id}")
    public Pedido actualizarPedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
        Pedido existingPedido = pedidoServicio.getPedidoPorId(id);
        if (existingPedido != null) {
            pedido.setIdPedido(id);
            return pedidoServicio.actualizarPedido(pedido);
        }
        return null;
    }

    @DeleteMapping("/pedidos/{id}")
    public void borrarPedido(@PathVariable Integer id) {
        pedidoServicio.borrarPedido(id);
    }

    @PostMapping("/pedidos/reseñas/{id}")
    public Pedido añadirReseña(@PathVariable Integer id, @RequestBody ReseñaRequest request) {
        return pedidoServicio.añadirReseña(id, request.getReseña(), request.getCalificacion());
    }

    public static class ReseñaRequest {
        private String reseña;
        private Integer calificacion;

        public String getReseña() {
            return reseña;
        }

        public void setReseña(String reseña) {
            this.reseña = reseña;
        }

        public Integer getCalificacion() {
            return calificacion;
        }

        public void setCalificacion(Integer calificacion) {
            this.calificacion = calificacion;
        }
    }
}