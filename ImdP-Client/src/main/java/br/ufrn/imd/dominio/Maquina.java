package br.ufrn.imd.dominio;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Maquina implements Serializable {

	private static final long serialVersionUID = 2145547853919156580L;

	private int idMaquina;

	private String denominacao;
	private String ip;

	private Unidade unidade;

	public Maquina() {

	}

	public Maquina(String denominacao, String ip, Unidade unidade) {
		super();
		this.denominacao = denominacao;
		this.ip = ip;
		this.unidade = unidade;
	}

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

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

}
