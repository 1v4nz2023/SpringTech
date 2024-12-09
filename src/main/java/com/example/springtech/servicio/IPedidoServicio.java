package com.example.springtech.servicio;
import java.util.List;

import com.example.springtech.modelo.Pedido;

public interface IPedidoServicio {


	public List<Pedido> getAllPedidos();
	
	public Pedido getPedidoPorId(Integer id);
	
	public Pedido guardarPedido(Pedido pedido);
	
	public Pedido actualizarPedido(Pedido pedido);
	
	public void borrarPedido(Integer id);
	
	public Pedido añadirReseña(Integer idPedido, String reseña, Integer calificacion );
	
	List<Pedido> buscarPedidosPorPartNumber(String partNumber);

}
