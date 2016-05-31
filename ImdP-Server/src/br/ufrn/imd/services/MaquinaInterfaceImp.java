package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.dao.MaquinaDao;
import br.ufrn.imd.dominio.Maquina;

@Stateless
public class MaquinaInterfaceImp implements MaquinaInterface{

	
	@EJB
	private MaquinaDao maquinaDao;
	
	@Override
	public void save(Maquina maquina) {
		verificarMaquina(maquina);
		maquinaDao.save(maquina);
	}

	@Override
	public Maquina update(Maquina maquina) {
		verificarMaquina(maquina);
		return (Maquina) maquinaDao.update(maquina);
	}

	@Override
	public void delete(Maquina maquina) {
		maquinaDao.delete(maquina);
	}

	@Override
	public Maquina find(int entityID) {
		return (Maquina) maquinaDao.find(entityID);
	}

	@Override
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
