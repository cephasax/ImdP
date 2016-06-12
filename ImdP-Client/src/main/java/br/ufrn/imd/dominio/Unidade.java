package br.ufrn.imd.dominio;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Unidade implements Serializable {
	private static final long serialVersionUID = -3739400437570744894L;

	private int idUnidade;
	private String nome;

	public Unidade() {
		super();
	}

	public Unidade(String nome) {
		this.nome = nome;
	}

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
}
