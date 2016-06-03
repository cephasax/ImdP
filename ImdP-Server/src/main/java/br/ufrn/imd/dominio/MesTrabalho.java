package br.ufrn.imd.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "SEQ_MESTRABALHO", initialValue = 1, allocationSize = 1, sequenceName = "seq_mestrabalho")
public class MesTrabalho implements Serializable {

	private static final long serialVersionUID = 26929024920294405L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MESTRABALHO")
	private int idMesTrabalho;

	private int mes;
	private int ano;
	private int diasUteis;

	public MesTrabalho() {
		super();
	}

	public MesTrabalho(int mes, int ano, int diasUteis) {
		super();
		this.mes = mes;
		this.ano = ano;
		this.diasUteis = diasUteis;
	}

	public int getIdMesTrabalho() {
		return idMesTrabalho;
	}

	public void setIdMesTrabalho(int idMesTrabalho) {
		this.idMesTrabalho = idMesTrabalho;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getDiasUteis() {
		return diasUteis;
	}

	public void setDiasUteis(int diasUteis) {
		this.diasUteis = diasUteis;
	}

}
