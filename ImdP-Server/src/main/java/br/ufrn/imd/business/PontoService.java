package br.ufrn.imd.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.dao.PontoDao;
import br.ufrn.imd.dominio.Ponto;

@Stateless
public class PontoService{

	@Inject
	private PontoDao pontoDao;
	
	public void save(Ponto ponto) {
		verificarPonto(ponto);
		pontoDao.save(ponto);
	}

	public Ponto update(Ponto ponto) {
		verificarPonto(ponto);
		return (Ponto) pontoDao.update(ponto);
	}

	public void delete(Ponto ponto) {
		pontoDao.delete(ponto);
	}

	public Ponto find(int entityID) {
		return (Ponto) pontoDao.find(entityID);
	}

	public List<Ponto> findAll() {
		return pontoDao.listar();
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
