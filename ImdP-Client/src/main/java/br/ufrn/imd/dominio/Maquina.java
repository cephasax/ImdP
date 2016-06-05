package br.ufrn.imd.dominio;

import java.io.Serializable;
import java.util.Collection;

public class Maquina implements Serializable {

	private static final long serialVersionUID = 2145547853919156580L;

	private int idMaquina;

	private String denominacao;
	private String ip;

	private Collection<Ponto> pontos;

//	@JoinColumn(name = "idUnidade")
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
