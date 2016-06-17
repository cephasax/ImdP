package br.ufrn.imd.dominio;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vinculo implements Serializable {
	private static final long serialVersionUID = 118938217482360981L;

	private int idVinculo;
	private String descricao;

	private Cargo cargo;

	private Setor setor;

	private Usuario usuario;

	private Permissao permissao;

	private int cargaHorariaDiaria;
	private int cargaHorariaSemanal;
	private int cargaHorariaMensal;
	private char situacao;

	public Vinculo() {
		super();
	}

	public Vinculo(String descricao, Cargo cargo, Setor setor, Usuario usuario, Permissao permissao,
			int cargaHorariaDiaria, int cargaHorariaSemanal, int cargaHorariaMensal, char situacao) {
		super();
		this.descricao = descricao;
		this.cargo = cargo;
		this.setor = setor;
		this.usuario = usuario;
		this.permissao = permissao;
		this.cargaHorariaDiaria = cargaHorariaDiaria;
		this.cargaHorariaSemanal = cargaHorariaSemanal;
		this.cargaHorariaMensal = cargaHorariaMensal;
		this.situacao = situacao;
	}
	
	

	public Vinculo(String descricao, Cargo cargo, Setor setor, int cargaHorariaDiaria, int cargaHorariaSemanal,
			int cargaHorariaMensal, char situacao) {
		super();
		this.descricao = descricao;
		this.cargo = cargo;
		this.setor = setor;
		this.cargaHorariaDiaria = cargaHorariaDiaria;
		this.cargaHorariaSemanal = cargaHorariaSemanal;
		this.cargaHorariaMensal = cargaHorariaMensal;
		this.situacao = situacao;
	}

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

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

}
