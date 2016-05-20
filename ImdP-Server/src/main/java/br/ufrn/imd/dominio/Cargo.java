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
@SequenceGenerator(name = "SEQ_CARGO", initialValue = 1, allocationSize = 1, sequenceName = "seq_cargo")
public class Cargo implements Serializable {
	private static final long serialVersionUID = 858455918925207560L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CARGO")
	private int idCargo;
	private String nome;

	@OneToMany
	private Collection<Vinculo> vinculos;

	public int getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Collection<Vinculo> getVinculos() {
		return vinculos;
	}

	public void setVinculos(Collection<Vinculo> vinculos) {
		this.vinculos = vinculos;
	}

}
