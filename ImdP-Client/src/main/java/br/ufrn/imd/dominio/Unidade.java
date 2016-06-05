package br.ufrn.imd.dominio;

import java.io.Serializable;
import java.util.Collection;

public class Unidade implements Serializable {
	private static final long serialVersionUID = -3739400437570744894L;

	private int idUnidade;

	private String nome;

	private Collection<Setor> setores;

	public int getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(int idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Collection<Setor> getSetores() {
		return setores;
	}

	public void setSetores(Collection<Setor> setores) {
		this.setores = setores;
	}

}
