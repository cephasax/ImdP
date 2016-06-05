package br.ufrn.imd.dominio;

import java.io.Serializable;
import java.util.Collection;

public class Cargo implements Serializable {
	private static final long serialVersionUID = 858455918925207560L;

	private int idCargo;
	private String nome;

	private Collection<Vinculo> vinculos;

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

	public Collection<Vinculo> getVinculos() {
		return vinculos;
	}

	public void setVinculos(Collection<Vinculo> vinculos) {
		this.vinculos = vinculos;
	}

}
