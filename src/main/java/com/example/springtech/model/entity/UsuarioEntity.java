package com.example.springtech.model.entity;

public class UsuarioEntity {
private int  user_id, dni;
private String nombres, apellidos, email, pass;


public UsuarioEntity() {
	super();
}


public UsuarioEntity(int user_id, int dni, String nombres, String apellidos, String email, String pass) {
	super();
	this.user_id = user_id;
	this.dni = dni;
	this.nombres = nombres;
	this.apellidos = apellidos;
	this.email = email;
	this.pass = pass;
}




public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public int getDni() {
	return dni;
}
public void setDni(int dni) {
	this.dni = dni;
}
public String getNombres() {
	return nombres;
}
public void setNombres(String nombres) {
	this.nombres = nombres;
}
public String getApellidos() {
	return apellidos;
}
public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPass() {
	return pass;
}
public void setPass(String pass) {
	this.pass = pass;
}


}
