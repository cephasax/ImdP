package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.ufrn.imd.dao.SetorDao;
import br.ufrn.imd.dominio.Setor;

@Stateless
public class SetorService{

	@Inject
	private SetorDao setorDao;
	
	public void save(Setor setor) {
		verificarSetor(setor);
		Setor place = setorDao.buscarPorId(setor.getIdSetor());
		
		if(place == null){
			setorDao.save(setor);
		}
		else{
			throw new IllegalArgumentException("Erro - save: Setor ja existe na base de dados");
		}
	}

	public Setor update(Setor setor) {
		verificarSetor(setor);
		return (Setor) setorDao.update(setor);
	}

	public void delete(Setor setor) {
		verificarSetor(setor);
		Setor place = setorDao.buscarPorId(setor.getIdSetor());
		
		if(place == null){
			throw new IllegalArgumentException("Erro - delete: Setor nao existe na base de dados");
		}
		else{
			setorDao.delete(setor);
		}
	}

	public Setor buscar(int entityID) {
		Setor place = new Setor();
		place = buscaId(entityID);
		
		if(place == null){
			throw new NoResultException("Erro - buscar: Setor nao encontrado");
		}
		else{
			return place;
		}
	}

	public List<Setor> listar() {
		ArrayList<Setor> places = new ArrayList<Setor>();
		places = setorDao.listar();
		if(places.size() > 0){
			return places;
		}
		else{
			throw new NoResultException("Erro - listar: nenhum setor cadastrado");
		}
	}
	
	public ArrayList<Setor> buscarFiltro(String nomeSetor, int idUnidade){
		ArrayList<Setor> places = new ArrayList<Setor>();
		places = setorDao.buscarSetorFiltro(nomeSetor, idUnidade);
		if(places.size() > 0){
			return places;
		}
		else{
			return null;
		}
	}

	//Metodo usados apenas para verificacoes no escopo do service
	private Setor buscaId(int id){
		try{
			Setor place = setorDao.buscarPorId(id);
			return place;
		}
		catch(NoResultException e){
			return null;
		}
	}
	
	private void verificarSetor(Setor setor){
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//NOME
		if (setor.getNome() == null || "".equals(setor.getNome().trim())){
			hasError = true;
		}
		
		//UNIDADE
		if (setor.getUnidade() == null){
			hasError = true;
		}
		
		if (hasError){
			throw new IllegalArgumentException("O setor nao possui todos os dados.");
		}
	}
}
