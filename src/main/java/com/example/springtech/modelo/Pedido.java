package com.example.springtech.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"usuario", "producto"})

public class Pedido {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer idPedido;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="producto_id")
	Productos producto;
	
	Integer cantidad;
	Double precioTotal;
	LocalDateTime fechaPedido;
	
	String reseña;
	Integer calificacion;
	
	
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Productos getProducto() {
		return producto;
	}
	public void setProducto(Productos producto) {
		this.producto = producto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public LocalDateTime getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(LocalDateTime fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
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
