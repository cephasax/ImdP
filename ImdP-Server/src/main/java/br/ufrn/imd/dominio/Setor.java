package br.ufrn.imd.dominio;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "SEQ_SETOR", initialValue = 1, allocationSize = 1, sequenceName = "seq_setor")
public class Setor implements Serializable {
	private static final long serialVersionUID = -4096652352782721877L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SETOR")
	private int idSetor;

	private String nome;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "setor_unidade", joinColumns = @JoinColumn(name = "idSetor"), inverseJoinColumns = @JoinColumn(name = "idUnidade"))
	private Collection<Unidade> unidades;

	public int getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(int idSetor) {
		this.idSetor = idSetor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Collection<Unidade> getUnidades() {
		return unidades;
	}

	public void setUnidades(Collection<Unidade> unidades) {
		this.unidades = unidades;
	}

}
