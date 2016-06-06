package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.ufrn.imd.dao.MesTrabalhoDao;
import br.ufrn.imd.dominio.MesTrabalho;

@Stateless
public class MesTrabalhoService{

	@Inject
	private MesTrabalhoDao mesTrabalhoDao;
	
	public void save(MesTrabalho mesTrabalho) {
		verificarMesTrabalho(mesTrabalho);
		MesTrabalho month = mesTrabalhoDao.buscarPorId(mesTrabalho.getIdMesTrabalho());
		
		if(month == null){
			mesTrabalhoDao.save(mesTrabalho);
		}
		else{
			throw new IllegalArgumentException("Erro - save: MesTrabalho ja existe na base de dados");
		}
	}

	public MesTrabalho update(MesTrabalho mesTrabalho) {
		verificarMesTrabalho(mesTrabalho);
		return (MesTrabalho) mesTrabalhoDao.update(mesTrabalho);
	}

	public void delete(MesTrabalho mesTrabalho) {
		verificarMesTrabalho(mesTrabalho);
		MesTrabalho month = mesTrabalhoDao.buscarPorId(mesTrabalho.getIdMesTrabalho());
		
		if(month == null){
			throw new IllegalArgumentException("Erro - delete: MesTrabalho nao existe na base de dados");
		}
		else{
			mesTrabalhoDao.delete(mesTrabalho);
		}
	}

	public MesTrabalho buscar(int entityID) {
		MesTrabalho month = new MesTrabalho();
		month = buscaId(entityID);
		
		if(month == null){
			throw new NoResultException("Erro - buscar: MesTrabalho nao encontrado");
		}
		else{
			return month;
		}
	}

	public List<MesTrabalho> listar() {
		ArrayList<MesTrabalho> months = new ArrayList<MesTrabalho>();
		months = mesTrabalhoDao.listar();
		if(months.size() > 0){
			return months;
		}
		else{
			throw new NoResultException("Erro - listar: nenhum mesTrabalho cadastrado");
		}
	}
	
	public ArrayList<MesTrabalho> buscarFiltro(int ano, int mes){
		ArrayList<MesTrabalho> months = new ArrayList<MesTrabalho>();
		months = mesTrabalhoDao.buscarMesTrabalhoFiltro(ano, mes);
		if(months.size() > 0){
			return months;
		}
		else{
			return null;
		}
	}

	//Metodo usados apenas para verificacoes no escopo do service
	private MesTrabalho buscaId(int id){
		try{
			MesTrabalho month = mesTrabalhoDao.buscarPorId(id);
			return month;
		}
		catch(NoResultException e){
			return null;
		}
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
