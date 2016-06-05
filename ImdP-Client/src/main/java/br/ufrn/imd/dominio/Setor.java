package br.ufrn.imd.dominio;

import java.io.Serializable;

public class Setor implements Serializable {
	private static final long serialVersionUID = -4096652352782721877L;

	private int idSetor;

	private String nome;

//	@JoinColumn(name = "idUnidade")
	private Unidade unidade;

	public int getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(int idSetor) {
		this.idSetor = idSetor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

}
