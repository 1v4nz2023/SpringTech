package com.example.springtech.servicio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springtech.modelo.Pedido;
import com.example.springtech.modelo.Productos;
import com.example.springtech.modelo.Usuario;
import com.example.springtech.repositorio.PedidoRepositorio;
import com.example.springtech.repositorio.ProductoRepositorio;
import com.example.springtech.repositorio.UsuarioRepositorio;

@Service
public class PedidoServicio implements IPedidoServicio {

	@Autowired
    private PedidoRepositorio pedidoRepository;
	
    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @Autowired
    private ProductoRepositorio productoRepository;

	@Override
	public List<Pedido> getAllPedidos() {
		return pedidoRepository.findAll();
	}

	@Override
	public Pedido getPedidoPorId(Integer idPedido) {
		
		Pedido pedido = pedidoRepository.findByidPedido(idPedido);
		return pedido;
		
	}

	@Override
	public Pedido guardarPedido(Pedido pedido) {
	    Optional<Usuario> usuarioOpt = usuarioRepository.findById(pedido.getUsuario().getIdUsuario());
	    Optional<Productos> productoOpt = productoRepository.findById(pedido.getProducto().getIdProducto());

	    if (usuarioOpt.isPresent() && productoOpt.isPresent()) {
	        Usuario usuario = usuarioOpt.get();
	        Productos producto = productoOpt.get();

	        if (pedido.getCantidad() <= 0) {
	            throw new IllegalArgumentException("La cantidad del pedido debe ser mayor que cero");
	        }

	        if (producto.getStock() < pedido.getCantidad()) {
	            throw new IllegalArgumentException("No hay suficiente stock disponible");
	        }

	        pedido.setUsuario(usuario);
	        pedido.setProducto(producto);
            // Calcular el precio total y redondearlo a dos decimales
            BigDecimal precioTotal = BigDecimal.valueOf(producto.getPrecio() * pedido.getCantidad())
                .setScale(2, RoundingMode.HALF_UP);
            
            pedido.setPrecioTotal(precioTotal.doubleValue());
	        pedido.setFechaPedido(LocalDateTime.now());
	        
	        // Disminuir el stock del producto
	        producto.setStock(producto.getStock()-pedido.getCantidad());
	        
	        return pedidoRepository.save(pedido);
	        
	    } else {
	        throw new IllegalArgumentException("Usuario o Producto no existe");
	    }
	}

    
    @Override
    public Pedido actualizarPedido(Pedido pedido) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(pedido.getUsuario().getIdUsuario());
        Optional<Productos> productoOpt = productoRepository.findById(pedido.getProducto().getIdProducto());

        if (usuarioOpt.isPresent() && productoOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            Productos producto = productoOpt.get();
            
            if (producto.getStock() < pedido.getCantidad()) {
                throw new IllegalArgumentException("No hay suficiente stock disponible");
            }


            pedido.setUsuario(usuario);
            pedido.setProducto(producto);
            pedido.setPrecioTotal(producto.getPrecio() * pedido.getCantidad());
            

            // Ajustar el stock del producto si se cambia la cantidad del pedido
            int cantidadAnterior = pedidoRepository.findById(pedido.getIdPedido())
                .map(Pedido::getCantidad)
                .orElse(0);
            producto.setStock(producto.getStock() + cantidadAnterior - pedido.getCantidad());
            productoRepository.save(producto);


            return pedidoRepository.save(pedido);
        } else {
            throw new IllegalArgumentException("Usuario o Producto no existe");
        }
    }
    
	public void borrarPedido(Integer id) {
		pedidoRepository.deleteById(id);
	}

    @Override
    public Pedido añadirReseña(Integer idPedido, String reseña, Integer calificacion) {
        Pedido pedido = getPedidoPorId(idPedido);
        if (pedido != null) {
            pedido.setReseña(reseña);
            pedido.setCalificacion(calificacion);
            return pedidoRepository.save(pedido);
        } else {
            throw new IllegalArgumentException("Pedido no existe");
        }
    }

	@Override
	public List<Pedido> buscarPedidosPorPartNumber(String partNumber) {
        return pedidoRepository.findByProducto_PartNumber(partNumber);
	}

	
	
}
