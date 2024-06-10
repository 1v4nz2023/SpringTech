package com.example.springtech.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer idUsuario;
	String dni;
	String nombre;
	String apellidos;
	String correo;
	String password;
	String rol;
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
    /*public String hashPassword(String password) {
        try {
            // Obtener instancia de MessageDigest con algoritmo SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Convertir la contraseña a bytes
            byte[] passwordBytes = password.getBytes();
            // Generar el hash de la contraseña
            byte[] hashBytes = md.digest(passwordBytes);
            // Convertir el hash a una representación hexadecimal
            BigInteger hashBigInteger = new BigInteger(1, hashBytes);
            String hashedPassword = hashBigInteger.toString(16);
            // Rellenar con ceros a la izquierda si es necesario
            while (hashedPassword.length() < 64) {
                hashedPassword = "0" + hashedPassword;
            }
            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            // Manejo de la excepción
            e.printStackTrace();
            return null;
        }
    }*/

}
