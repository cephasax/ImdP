package br.ufrn.imd.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ponto implements Serializable {

	private static final long serialVersionUID = 3995925481211634067L;

	private int idPonto;

	private Date timeStamp;
	private char tipo;
	private char validado;
	private String observacao;

	private Date timeStampAlteracao;
	private int idUsuarioAlteracao;

	private Vinculo vinculo;

	private Maquina maquina;

	public Ponto() {
		super();
	}

	public Ponto(Date timeStamp, char tipo, char validado, String observacao, Date timeStampAlteracao,
			int idUsuarioAlteracao, Vinculo vinculo, Maquina maquina) {
		super();
		this.timeStamp = timeStamp;
		this.tipo = tipo;
		this.validado = validado;
		this.observacao = observacao;
		this.timeStampAlteracao = timeStampAlteracao;
		this.idUsuarioAlteracao = idUsuarioAlteracao;
		this.vinculo = vinculo;
		this.maquina = maquina;
	}

	public int getIdPonto() {
		return idPonto;
	}

	public void setIdPonto(int idPonto) {
		this.idPonto = idPonto;
	}

	public Vinculo getVinculo() {
		return vinculo;
	}

	public void setVinculo(Vinculo vinculo) {
		this.vinculo = vinculo;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public char getValidado() {
		return validado;
	}

	public void setValidado(char validado) {
		this.validado = validado;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getTimeStampAlteracao() {
		return timeStampAlteracao;
	}

	public void setTimeStampAlteracao(Date timeStampAlteracao) {
		this.timeStampAlteracao = timeStampAlteracao;
	}

	public int getIdUsuarioAlteracao() {
		return idUsuarioAlteracao;
	}

	public void setIdUsuarioAlteracao(int idUsuarioAlteracao) {
		this.idUsuarioAlteracao = idUsuarioAlteracao;
	}

}
