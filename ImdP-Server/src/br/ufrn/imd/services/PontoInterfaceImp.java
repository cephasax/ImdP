package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.dao.PontoDao;
import br.ufrn.imd.dominio.Ponto;

@Stateless
public class PontoInterfaceImp implements PontoInterface{

	
	@EJB
	private PontoDao pontoDao;
	
	@Override
	public void save(Ponto ponto) {
		verificarPonto(ponto);
		pontoDao.save(ponto);
	}

	@Override
	public Ponto update(Ponto ponto) {
		verificarPonto(ponto);
		return (Ponto) pontoDao.update(ponto);
	}

	@Override
	public void delete(Ponto ponto) {
		pontoDao.delete(ponto);
	}

	@Override
	public Ponto find(int entityID) {
		return (Ponto) pontoDao.find(entityID);
	}

	@Override
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
