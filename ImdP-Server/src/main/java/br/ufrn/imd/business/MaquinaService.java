package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.ufrn.imd.dao.MaquinaDao;
import br.ufrn.imd.dominio.Maquina;

@Stateless
public class MaquinaService{

	@Inject
	private MaquinaDao maquinaDao;
	
	public void save(Maquina maquina) {
		verificarMaquina(maquina);
		Maquina maq = maquinaDao.buscarPorId(maquina.getIdMaquina());
		
		if(maq == null){
			maquinaDao.save(maquina);
		}
		else{
			throw new IllegalArgumentException("Erro - save: Maquina ja existe na base de dados");
		}
	}

	public Maquina update(Maquina maquina) {
		verificarMaquina(maquina);
		return (Maquina) maquinaDao.update(maquina);
	}

	public void delete(Maquina maquina) {
		verificarMaquina(maquina);
		Maquina maq = maquinaDao.buscarPorId(maquina.getIdMaquina());
		
		if(maq == null){
			throw new IllegalArgumentException("Erro - delete: Maquina nao existe na base de dados");
		}
		else{
			maquinaDao.delete(maquina);
		}
	}

	public Maquina buscar(int entityID) {
		Maquina maq = new Maquina();
		maq = buscaId(entityID);
		
		if(maq == null){
			throw new NoResultException("Erro - buscar: Maquina nao encontrada");
		}
		else{
			return maq;
		}
	}

	public List<Maquina> listar() {
		ArrayList<Maquina> maqs = new ArrayList<Maquina>();
		maqs = maquinaDao.listar();
		if(maqs.size() > 0){
			return maqs;
		}
		else{
			throw new NoResultException("Erro - listar: nenhuma maquina cadastrada");
		}
	}	
	
	public ArrayList<Maquina> buscarFiltro(String nomeMaquina, int idUnidade){
		ArrayList<Maquina> maqs = new ArrayList<Maquina>();
		maqs = maquinaDao.buscarMaquinaFiltro(nomeMaquina, idUnidade);
		if(maqs.size() > 0){
			return maqs;
		}
		else{
			return null;
		}
	}

	//Metodo usados apenas para verificacoes no escopo do service
	private Maquina buscaId(int id){
		try{
			Maquina maq = maquinaDao.buscarPorId(id);
			return maq;
		}
		catch(NoResultException e){
			return null;
		}
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
