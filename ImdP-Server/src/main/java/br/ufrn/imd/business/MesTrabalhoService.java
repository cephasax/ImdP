package br.ufrn.imd.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.dao.MesTrabalhoDao;
import br.ufrn.imd.dominio.MesTrabalho;

@Stateless
public class MesTrabalhoService{

	
	@Inject
	private MesTrabalhoDao mesTrabalhoDao;
	
	public void save(MesTrabalho mesTrabalho) {
		verificarMesTrabalho(mesTrabalho);
		mesTrabalhoDao.save(mesTrabalho);
	}

	public MesTrabalho update(MesTrabalho mesTrabalho) {
		verificarMesTrabalho(mesTrabalho);
		return (MesTrabalho) mesTrabalhoDao.update(mesTrabalho);
	}

	public void delete(MesTrabalho mesTrabalho) {
		mesTrabalhoDao.delete(mesTrabalho);
	}

	public MesTrabalho find(int entityID) {
		return (MesTrabalho) mesTrabalhoDao.find(entityID);
	}

	public List<MesTrabalho> findAll() {
		return mesTrabalhoDao.listar();
	}
	
	private void verificarMesTrabalho(MesTrabalho mesTrabalho){
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//ANO
		if (mesTrabalho.getAno() <= 0 ){
			hasError = true;
		}
		
		//QUANTIDADE DE DIAS
		if (mesTrabalho.getDiasUteis() <= 0){
			hasError = true;
		}
		
		//MES
		if (mesTrabalho.getIdMesTrabalho() <= 0 || mesTrabalho.getIdMesTrabalho() > 12){
			hasError = true;
		}
		
		if (hasError){
			throw new IllegalArgumentException("O mesTrabalho nao possui todos os dados.");
		}
	}
	
}
