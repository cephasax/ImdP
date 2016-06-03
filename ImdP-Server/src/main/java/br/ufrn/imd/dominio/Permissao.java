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
@SequenceGenerator(name = "SEQ_PERMISSAO", initialValue = 1, allocationSize = 1, sequenceName = "seq_permissao")
public class Permissao implements Serializable {
	private static final long serialVersionUID = -6715065336143175013L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERMISSAO")
	private int idPermissao;

	private String descricao;

	/*@OneToMany(mappedBy = "permissao")
	private Collection<Vinculo> vinculos;*/

	public int getIdPermissao() {
		return idPermissao;
	}

	public void setIdPermissao(int idPermissao) {
		this.idPermissao = idPermissao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/*public Collection<Vinculo> getVinculos() {
		return vinculos;
	}

	public void setVinculos(Collection<Vinculo> vinculos) {
		this.vinculos = vinculos;
	}*/

}
