package br.ufrn.imd.customDao;

public class VinculoCustomDao {

	private String nomeUsuario;
	private String cpfUsuario;
	private String nomeUnidade;
	private String nomeSetor;
	private int chSemanalUsuario;
	private char situacaoVinculo;
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getCpfUsuario() {
		return cpfUsuario;
	}
	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}
	public String getNomeUnidade() {
		return nomeUnidade;
	}
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}
	public String getNomeSetor() {
		return nomeSetor;
	}
	public void setNomeSetor(String nomeSetor) {
		this.nomeSetor = nomeSetor;
	}
	public int getChSemanalUsuario() {
		return chSemanalUsuario;
	}
	public void setChSemanalUsuario(int chSemanalUsuario) {
		this.chSemanalUsuario = chSemanalUsuario;
	}
	public char getSituacaoVinculo() {
		return situacaoVinculo;
	}
	public void setSituacaoVinculo(char situacaoVinculo) {
		this.situacaoVinculo = situacaoVinculo;
	}
	
	
}
