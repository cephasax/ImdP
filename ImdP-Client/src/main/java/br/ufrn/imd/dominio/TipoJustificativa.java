package br.ufrn.imd.dominio;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TipoJustificativa implements Serializable {
	private static final long serialVersionUID = 8868548151221158266L;

	private int idTipoJustificativa;

	private String nome;

	public TipoJustificativa() {
		super();
	}

	public TipoJustificativa(String nome) {
		this.nome = nome;
	}

	public int getIdTipoJustificativa() {
		return idTipoJustificativa;
	}

	public void setIdTipoJustificativa(int idTipoJustificativa) {
		this.idTipoJustificativa = idTipoJustificativa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
