package br.ufrn.imd.dominio;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ImpressaoDigital implements Serializable{
	private static final long serialVersionUID = 2366532663499434800L;
	
	private int idImpressaoDigital;
	private Usuario usuario;
	
	private byte[] digital;
	private String nomeDedo;
	
	public ImpressaoDigital() {
		super();
	}
	
	public ImpressaoDigital(Usuario usuario, byte[] digital) {
		super();
		this.usuario = usuario;
		this.digital = digital;
	}
	
	public ImpressaoDigital(byte[] digital) {
		super();
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
