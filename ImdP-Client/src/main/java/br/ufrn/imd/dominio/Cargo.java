package br.ufrn.imd.dominio;

import java.io.Serializable;

public class Cargo implements Serializable {
	private static final long serialVersionUID = 858455918925207560L;

	private int idCargo;
	private String nome;

	public int getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
