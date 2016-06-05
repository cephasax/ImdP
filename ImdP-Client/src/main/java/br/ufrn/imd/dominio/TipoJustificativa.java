package br.ufrn.imd.dominio;

import java.io.Serializable;
import java.util.Collection;

public class TipoJustificativa implements Serializable {
	private static final long serialVersionUID = 8868548151221158266L;

	private int idTipoJustificativa;
	
	private String nome;
	
	private Collection<JustificativaFalta> justificativas;

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

	public Collection<JustificativaFalta> getJustificativas() {
		return justificativas;
	}

	public void setJustificativas(Collection<JustificativaFalta> justificativas) {
		this.justificativas = justificativas;
	}
	
	
}
