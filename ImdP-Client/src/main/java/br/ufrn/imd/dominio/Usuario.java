package br.ufrn.imd.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario implements Serializable {

	private static final long serialVersionUID = 6952842832271889997L;

	private int idUsuario;

	private String nome;
	private String sexo;

	private Date dataNascimento;
	private String cpf;
	private String rg;
	private String orgaoExpedicaoRg;

	private Date dataExpedicaoRg;
	private String EstadoRg;
	private String cnh;
	private String nomePai;
	private String nomeMae;
	private String email;
	private String telefone1;
	private String telefone2;

	private String login;
	private String senha;
	private String foto;

	private Date dataCriacao;

	public Usuario() {
		super();
	}

	public Usuario(int idUsuario, String nome, String sexo, Date dataNascimento, String cpf, String rg,
			String orgaoExpedicaoRg, Date dataExpedicaoRg, String estadoRg, String cnh, String nomePai, String nomeMae,
			String email, String telefone1, String telefone2, String login, String senha, String foto,
			Date dataCriacao) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.rg = rg;
		this.orgaoExpedicaoRg = orgaoExpedicaoRg;
		this.dataExpedicaoRg = dataExpedicaoRg;
		EstadoRg = estadoRg;
		this.cnh = cnh;
		this.nomePai = nomePai;
		this.nomeMae = nomeMae;
		this.email = email;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.login = login;
		this.senha = senha;
		this.foto = foto;
		this.dataCriacao = dataCriacao;
	}

	public Usuario(String nome, String sexo, Date dataNascimento, String cpf, String rg, String orgaoExpedicaoRg,
			Date dataExpedicaoRg, String estadoRg, String cnh, String nomePai, String nomeMae, String email,
			String telefone1, String telefone2, String login, String senha, String foto,
			Date dataCriacao) {
		super();
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.rg = rg;
		this.orgaoExpedicaoRg = orgaoExpedicaoRg;
		this.dataExpedicaoRg = dataExpedicaoRg;
		EstadoRg = estadoRg;
		this.cnh = cnh;
		this.nomePai = nomePai;
		this.nomeMae = nomeMae;
		this.email = email;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.login = login;
		this.senha = senha;
		this.foto = foto;
		this.dataCriacao = dataCriacao;
	}

	

	public Usuario(String nome, String sexo, Date dataNascimento, String cpf, String rg, String orgaoExpedicaoRg,
			Date dataExpedicaoRg, String estadoRg, String cnh, String nomePai, String nomeMae, String email,
			String telefone1, String telefone2, Date dataCriacao) {
		super();
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.rg = rg;
		this.orgaoExpedicaoRg = orgaoExpedicaoRg;
		this.dataExpedicaoRg = dataExpedicaoRg;
		EstadoRg = estadoRg;
		this.cnh = cnh;
		this.nomePai = nomePai;
		this.nomeMae = nomeMae;
		this.email = email;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.dataCriacao = dataCriacao;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoExpedicaoRg() {
		return orgaoExpedicaoRg;
	}

	public void setOrgaoExpedicaoRg(String orgaoExpedicaoRg) {
		this.orgaoExpedicaoRg = orgaoExpedicaoRg;
	}

	public Date getDataExpedicaoRg() {
		return dataExpedicaoRg;
	}

	public void setDataExpedicaoRg(Date dataExpedicaoRg) {
		this.dataExpedicaoRg = dataExpedicaoRg;
	}

	public String getEstadoRg() {
		return EstadoRg;
	}

	public void setEstadoRg(String estadoRg) {
		EstadoRg = estadoRg;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

}
