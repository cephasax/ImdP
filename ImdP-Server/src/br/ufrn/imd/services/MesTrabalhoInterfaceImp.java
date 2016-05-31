package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.dao.MesTrabalhoDao;
import br.ufrn.imd.dominio.MesTrabalho;

@Stateless
public class MesTrabalhoInterfaceImp implements MesTrabalhoInterface{

	
	@EJB
	private MesTrabalhoDao mesTrabalhoDao;
	
	@Override
	public void save(MesTrabalho mesTrabalho) {
		verificarMesTrabalho(mesTrabalho);
		mesTrabalhoDao.save(mesTrabalho);
	}

	@Override
	public MesTrabalho update(MesTrabalho mesTrabalho) {
		verificarMesTrabalho(mesTrabalho);
		return (MesTrabalho) mesTrabalhoDao.update(mesTrabalho);
	}

	@Override
	public void delete(MesTrabalho mesTrabalho) {
		mesTrabalhoDao.delete(mesTrabalho);
	}

	@Override
	public MesTrabalho find(int entityID) {
		return (MesTrabalho) mesTrabalhoDao.find(entityID);
	}

	@Override
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
