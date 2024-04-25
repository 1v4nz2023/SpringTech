package com.example.springtech.model.dao;

import java.util.ArrayList;

import com.example.springtech.model.entity.UsuarioEntity;

public class UsuarioDAO {
	
	ArrayList<UsuarioEntity> listaUser = new ArrayList <>();
	private int ultimoUserId=0;
	
	public void registrarUsuario(UsuarioEntity objUE) {
		ultimoUserId++;
		objUE.setUser_id(ultimoUserId);
		listaUser.add(objUE);
	}
	
	public ArrayList<UsuarioEntity> getUsuarios(){
		return listaUser;
	}
}
