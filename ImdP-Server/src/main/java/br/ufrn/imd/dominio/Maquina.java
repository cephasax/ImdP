package br.ufrn.imd.dominio;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "SEQ_MAQUINA", initialValue = 1, allocationSize = 1, sequenceName = "seq_maquina")
public class Maquina implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2145547853919156580L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MAQUINA")
	private int idMaquina;

	private String denominacao;
	private String ip;

	@OneToMany(mappedBy = "maquina")
	private Collection<Ponto> pontos;

	@ManyToOne
	@JoinColumn(name = "idUnidade")
	private Unidade unidade;

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(int idMaquina) {
		this.idMaquina = idMaquina;
	}

	public Collection<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos(Collection<Ponto> pontos) {
		this.pontos = pontos;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

}
