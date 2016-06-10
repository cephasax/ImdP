package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.ufrn.imd.dao.PontoDao;
import br.ufrn.imd.dominio.Ponto;
import br.ufrn.imd.excecoes.DadoIncompletoException;
import br.ufrn.imd.excecoes.DadoJaExisteException;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

@Stateless
public class PontoService{

	@Inject
	private PontoDao pontoDao;
	
	public void save(Ponto ponto) throws DadoJaExisteException, DadoIncompletoException {
		verificarPonto(ponto);
		ArrayList<Ponto> pontos = pontoDao.buscarPontoCheck(ponto);
		
		if(pontos.size() == 0){
			pontoDao.save(ponto);
		}
		else{
			throw new DadoJaExisteException("Erro - save: Ponto ja existe na base de dados");
		}
	}

	public Ponto update(Ponto ponto) throws DadoIncompletoException {
		verificarPonto(ponto);
		return (Ponto) pontoDao.update(ponto);
	}

	public void delete(Ponto ponto) throws DadoIncompletoException {
		verificarPonto(ponto);
		pontoDao.delete(ponto);
	}

	public Ponto buscar(int entityID) throws DadoNaoEncontradoException {
		Ponto pon = new Ponto();
		pon = pontoDao.buscarPorId(entityID);
		
		if(pon == null){
			throw new DadoNaoEncontradoException("Erro - buscar: Ponto nao encontrado");
		}
		else{
			return pon;
		}
	}

	public List<Ponto> listar() throws DadoNaoEncontradoException {
		ArrayList<Ponto> pons = new ArrayList<Ponto>();
		pons = pontoDao.listar();
		if(pons.size() > 0){
			return pons;
		}
		else{
			throw new DadoNaoEncontradoException("Erro - listar: nenhum ponto cadastrado");
		}
	}
	
	public ArrayList<Ponto> buscarFiltro(String nomePonto, int idUnidade, int idSetor){
		ArrayList<Ponto> pons = new ArrayList<Ponto>();
		pons = pontoDao.buscarPontoFiltro(nomePonto, idUnidade, idSetor);
		if(pons.size() > 0){
			return pons;
		}
		else{
			return null;
		}
	}

	public ArrayList<Ponto> buscarPontosVinculo(int idVinculo){
		ArrayList<Ponto> pons = new ArrayList<Ponto>();
		pons = pontoDao.listarPontosVinculo(idVinculo);
		if(pons.size() > 0){
			return pons;
		}
		else{
			return null;
		}
	}

	public ArrayList<Ponto> buscarPontosUsuario(int idUsuario){
		ArrayList<Ponto> pons = new ArrayList<Ponto>();
		pons = pontoDao.listarPontosUsuario(idUsuario);
		if(pons.size() > 0){
			return pons;
		}
		else{
			return null;
		}
	}
	
	private void verificarPonto(Ponto ponto) throws DadoIncompletoException{
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//DATA
		if (ponto.getTimeStamp() == null ){
			hasError = true;
		}
		
		//Id Vinculo
		if(ponto.getVinculo().getIdVinculo() <= 0){
			hasError = true;
		}
		
		//MAQUINA
		if (ponto.getMaquina() == null){
			hasError = true;
		}
		
		if (hasError){
			throw new DadoIncompletoException("O ponto nao possui todos os dados.");
		}
	}
}
