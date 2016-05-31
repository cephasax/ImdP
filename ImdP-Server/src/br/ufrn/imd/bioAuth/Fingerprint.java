package br.ufrn.imd.bioAuth;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "SEQ_FINGERPRINT", initialValue = 1, allocationSize = 1, sequenceName = "seq_fingerprint")
public class Fingerprint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FINGERPRINT")
	private int idFingerprint;

	private String nomeUsuario;

	private byte[] fingerprintImage;

	public int getIdFingerprint() {
		return idFingerprint;
	}

	public void setIdFingerprint(int idFingerprint) {
		this.idFingerprint = idFingerprint;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public byte[] getFingerprintImage() {
		return fingerprintImage;
	}

	public void setFingerprintImage(byte[] fingerprintImage) {
		this.fingerprintImage = fingerprintImage;
	}

}
