package br.ufrn.imd.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@SequenceGenerator(name = "SEQ_USUARIO", initialValue = 1, allocationSize = 1, sequenceName = "seq_usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 6952842832271889997L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
	private int idUsuario;
	
	private String login;
	private String senha;
	private String foto;
	private Date dataCriacao;
		
	@ManyToOne
	@JoinColumn(name = "idPessoa")
	private Pessoa pessoa;
	
	@OneToMany(mappedBy = "usuario")
	@Cascade({CascadeType.ALL})
	private ArrayList<Vinculo> vinculos;
	
	@OneToMany(mappedBy = "usuario")
	@Cascade({CascadeType.ALL})
	private ArrayList<Ponto> pontos;
	
	@OneToMany(mappedBy = "usuario")
	@Cascade({CascadeType.ALL})
	private ArrayList<JustificativaFalta> justificativasFaltas;
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public ArrayList<Vinculo> getVinculos() {
		return vinculos;
	}

	public void setVinculos(ArrayList<Vinculo> vinculos) {
		this.vinculos = vinculos;
	}

	public ArrayList<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos(ArrayList<Ponto> pontos) {
		this.pontos = pontos;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public ArrayList<JustificativaFalta> getJustificativassFaltas() {
		return justificativasFaltas;
	}

	public void setJustificasFaltas(ArrayList<JustificativaFalta> justificativasFaltas) {
		this.justificativasFaltas = justificativasFaltas;
	}
	
}
