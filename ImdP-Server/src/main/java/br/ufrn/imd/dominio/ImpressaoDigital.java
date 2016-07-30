package br.ufrn.imd.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "SEQ_IMP_DIGITAL", initialValue = 1, allocationSize = 1, sequenceName = "seq_imp_digital")
public class ImpressaoDigital implements Serializable{

	private static final long serialVersionUID = 2366532663499434800L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IMP_DIGITAL")
	private int idImpressaoDigital;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	private byte[] digital;
	private String nomeDedo;
	
	public ImpressaoDigital() {
		super();
	}

	public ImpressaoDigital(int idImpressaoDigital, Usuario usuario, byte[] digital, String nomeDedo) {
		super();
		this.idImpressaoDigital = idImpressaoDigital;
		this.usuario = usuario;
		this.digital = digital;
		this.nomeDedo = nomeDedo;
	}


	public ImpressaoDigital(Usuario usuario, byte[] digital) {
		super();
		this.usuario = usuario;
		this.digital = digital;
	}

	public int getIdImpressaoDigital() {
		return idImpressaoDigital;
	}

	public void setIdImpressaoDigital(int idImpressaoDigital) {
		this.idImpressaoDigital = idImpressaoDigital;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public byte[] getDigital() {
		return digital;
	}

	public void setDigital(byte[] digital) {
		this.digital = digital;
	}

	public String getNomeDedo() {
		return nomeDedo;
	}

	public void setNomeDedo(String nomeDedo) {
		this.nomeDedo = nomeDedo;
	}
	
}
