package br.ufrn.imd.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "SEQ_UNIDADE", initialValue = 1, allocationSize = 1, sequenceName = "seq_unidade")
public class Unidade implements Serializable {
	private static final long serialVersionUID = -3739400437570744894L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_UNIDADE")
	private int idUnidade;

	private String nome;

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
