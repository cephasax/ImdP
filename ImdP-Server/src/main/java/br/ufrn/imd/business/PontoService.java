package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.ufrn.imd.dao.PontoDao;
import br.ufrn.imd.dominio.Ponto;

@Stateless
public class PontoService{

	@Inject
	private PontoDao pontoDao;
	
	public void save(Ponto ponto) {
		verificarPonto(ponto);
		Ponto pon = pontoDao.buscarPorId(ponto.getIdPonto());
		
		if(pon == null){
			pontoDao.save(ponto);
		}
		else{
			throw new IllegalArgumentException("Erro - save: Ponto ja existe na base de dados");
		}
	}

	public Ponto update(Ponto ponto) {
		verificarPonto(ponto);
		return (Ponto) pontoDao.update(ponto);
	}

	public void delete(Ponto ponto) {
		verificarPonto(ponto);
		Ponto pon = pontoDao.buscarPorId(ponto.getIdPonto());
		
		if(pon == null){
			throw new IllegalArgumentException("Erro - delete: Ponto nao existe na base de dados");
		}
		else{
			pontoDao.delete(ponto);
		}
	}

	public Ponto buscar(int entityID) {
		Ponto pon = new Ponto();
		pon = buscaId(entityID);
		
		if(pon == null){
			throw new NoResultException("Erro - buscar: Ponto nao encontrado");
		}
		else{
			return pon;
		}
	}

	public List<Ponto> listar() {
		ArrayList<Ponto> pons = new ArrayList<Ponto>();
		pons = pontoDao.listar();
		if(pons.size() > 0){
			return pons;
		}
		else{
			throw new NoResultException("Erro - listar: nenhum ponto cadastrado");
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
	
	//Metodo usados apenas para verificacoes no escopo do service
	private Ponto buscaId(int id){
		try{
			Ponto pon = pontoDao.buscarPorId(id);
			return pon;
		}
		catch(NoResultException e){
			return null;
		}
	}
	
	private void verificarPonto(Ponto ponto){
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//DATA
		if (ponto.getTimeStamp() == null ){
			hasError = true;
		}
		
		//VINCULO
		if (ponto.getVinculo() == null){
			hasError = true;
		}
		
		//MAQUINA
		if (ponto.getMaquina() == null){
			hasError = true;
		}
		
		if (hasError){
			throw new IllegalArgumentException("O ponto nao possui todos os dados.");
		}
	}
}
