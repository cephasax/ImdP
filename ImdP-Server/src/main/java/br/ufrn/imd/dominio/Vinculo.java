package br.ufrn.imd.dominio;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "SEQ_VINCULO", initialValue = 1, allocationSize = 1, sequenceName = "seq_vinculo")
public class Vinculo implements Serializable {
	private static final long serialVersionUID = 118938217482360981L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VINCULO")
	private int idVinculo;
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "idCargo")
	private Cargo cargo;

	@ManyToOne
	@JoinColumn(name = "idSetor")
	private Setor setor;

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	@OneToMany
	private Collection<Ponto> pontos;
	
	@ManyToOne
	private Permissao permissao;
	
	@OneToMany
	private Collection<JustificativaFalta> justificativas;

	private int cargaHorariaDiaria;
	private int cargaHorariaSemanal;
	private int cargaHorariaMensal;
	private char situacao;

	public int getIdVinculo() {
		return idVinculo;
	}

	public void setIdVinculo(int idVinculo) {
		this.idVinculo = idVinculo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public int getCargaHorariaDiaria() {
		return cargaHorariaDiaria;
	}

	public void setCargaHorariaDiaria(int cargaHorariaDiaria) {
		this.cargaHorariaDiaria = cargaHorariaDiaria;
	}

	public int getCargaHorariaSemanal() {
		return cargaHorariaSemanal;
	}

	public void setCargaHorariaSemanal(int cargaHorariaSemanal) {
		this.cargaHorariaSemanal = cargaHorariaSemanal;
	}

	public int getCargaHorariaMensal() {
		return cargaHorariaMensal;
	}

	public void setCargaHorariaMensal(int cargaHorariaMensal) {
		this.cargaHorariaMensal = cargaHorariaMensal;
	}

	public char getSituacao() {
		return situacao;
	}

	public void setSituacao(char situacao) {
		this.situacao = situacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
