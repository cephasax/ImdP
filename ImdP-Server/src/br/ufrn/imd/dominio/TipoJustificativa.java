package br.ufrn.imd.dominio;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "SEQ_TIPOJUSTIFICATIVA", initialValue = 1, allocationSize = 1, sequenceName = "seq_tipojustificativa")
public class TipoJustificativa implements Serializable {
	private static final long serialVersionUID = 8868548151221158266L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIPOJUSTIFICATIVA")
	private int idTipoJustificativa;
	
	private String nome;
	
	@OneToMany(mappedBy="tipoJustificativa")
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
