package br.ufrn.imd.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.dao.MaquinaDao;
import br.ufrn.imd.dominio.Maquina;

@Stateless
public class MaquinaService{

	
	@Inject
	private MaquinaDao maquinaDao;
		
	public void save(Maquina maquina) {
		verificarMaquina(maquina);
		maquinaDao.save(maquina);
	}
	
	public Maquina update(Maquina maquina) {
		verificarMaquina(maquina);
		return (Maquina) maquinaDao.update(maquina);
	}
	
	public void delete(Maquina maquina) {
		maquinaDao.delete(maquina);
	}

	public Maquina find(int entityID) {
		return (Maquina) maquinaDao.find(entityID);
	}
	
	public List<Maquina> findAll() {
		return maquinaDao.listar();
	}
	
	private void verificarMaquina(Maquina maquina){
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//IP
		if (maquina.getIp() == null || "".equals(maquina.getIp().trim())){
			hasError = true;
		}
		
		//UNIDADE
		if (maquina.getUnidade() == null){
			hasError = true;
		}
		
		if (hasError){
			throw new IllegalArgumentException("A maquina nao possui todos os dados.");
		}
	}
	
}
