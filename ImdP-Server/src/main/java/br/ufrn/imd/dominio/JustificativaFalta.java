package br.ufrn.imd.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "SEQ_JUSTIFICATIVAFALTA", initialValue = 1, allocationSize = 1, sequenceName = "seq_justificativafalta")
public class JustificativaFalta implements Serializable {
	private static final long serialVersionUID = -4432018474136066836L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_JUSTIFICATIVAFALTA")
	private int idJustificativaFalta;

	private Date dataInicio;
	private Date dataFim;

	@ManyToOne
	@JoinColumn(name = "idVinculo")
	private Vinculo vinculo;

	@ManyToOne
	@JoinColumn(name = "idTipoJustificativa")
	private TipoJustificativa tipoJustificativa;

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario gestor;

	private String descricao;
	private Date dataEnvio;
	private char situacao;

	private String observacaoAnalise;
	private Date dataAnalise;
	private int cargaHorariaAbonada;
	private int cargaHorariaNaoAbonada;
	private String comprovante;

	public int getIdJustificativaFalta() {
		return idJustificativaFalta;
	}

	public void setIdJustificativaFalta(int idJustificativaFalta) {
		this.idJustificativaFalta = idJustificativaFalta;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Vinculo getVinculo() {
		return vinculo;
	}

	public void setVinculo(Vinculo vinculo) {
		this.vinculo = vinculo;
	}

	public TipoJustificativa getTipoJustificativa() {
		return tipoJustificativa;
	}

	public void setTipoJustificativa(TipoJustificativa tipoJustificativa) {
		this.tipoJustificativa = tipoJustificativa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public char getSituacao() {
		return situacao;
	}

	public void setSituacao(char situacao) {
		this.situacao = situacao;
	}

	public Usuario getGestor() {
		return gestor;
	}

	public void setGestor(Usuario gestor) {
		this.gestor = gestor;
	}

	public String getObservacaoAnalise() {
		return observacaoAnalise;
	}

	public void setObservacaoAnalise(String observacaoAnalise) {
		this.observacaoAnalise = observacaoAnalise;
	}

	public Date getDataAnalise() {
		return dataAnalise;
	}

	public void setDataAnalise(Date dataAnalise) {
		this.dataAnalise = dataAnalise;
	}

	public int getCargaHorariaAbonada() {
		return cargaHorariaAbonada;
	}

	public void setCargaHorariaAbonada(int cargaHorariaAbonada) {
		this.cargaHorariaAbonada = cargaHorariaAbonada;
	}

	public int getCargaHorariaNaoAbonada() {
		return cargaHorariaNaoAbonada;
	}

	public void setCargaHorariaNaoAbonada(int cargaHorariaNaoAbonada) {
		this.cargaHorariaNaoAbonada = cargaHorariaNaoAbonada;
	}

	public String getComprovante() {
		return comprovante;
	}

	public void setComprovante(String comprovante) {
		this.comprovante = comprovante;
	}

}
