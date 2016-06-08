package br.ufrn.imd.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "SEQ_PONTO", initialValue = 1, allocationSize = 1, sequenceName = "seq_ponto")
public class Ponto implements Serializable {

	private static final long serialVersionUID = 3995925481211634067L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PONTO")
	private int idPonto;

	@Temporal(TemporalType.DATE)
	private Date timeStamp;
	private char tipo;
	private char validado;
	private String observacao;
	@Temporal(TemporalType.DATE)
	private Date timeStampAlteracao;
	private int idUsuarioAlteracao;

	@ManyToOne
	@JoinColumn(name = "idVinculo")
	private Vinculo vinculo;

	@ManyToOne
	@JoinColumn(name = "idMaquina")
	private Maquina maquina;

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
