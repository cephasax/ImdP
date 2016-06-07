package br.ufrn.imd.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "SEQ_TIPOJUSTIFICATIVA", initialValue = 1, allocationSize = 1, sequenceName = "seq_tipojustificativa")
public class TipoJustificativa implements Serializable {
	private static final long serialVersionUID = 8868548151221158266L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIPOJUSTIFICATIVA")
	private int idTipoJustificativa;
	
	private String nome;
	
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
