package com.example.springtech.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springtech.modelo.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido,Integer> {
	
	Pedido findByidPedido(Integer idPedido);
	
    List<Pedido> findByProducto_PartNumber(String partNumber);

}
