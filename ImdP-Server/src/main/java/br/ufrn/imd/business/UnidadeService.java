package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.ufrn.imd.dao.UnidadeDao;
import br.ufrn.imd.dominio.Unidade;

@Stateless
public class UnidadeService{

	@Inject
	private UnidadeDao unidadeDao;
	
	public void save(Unidade unidade) {
		verificarUnidade(unidade);
		Unidade unit = unidadeDao.buscarPorId(unidade.getIdUnidade());
		
		if(unit == null){
			unidadeDao.save(unidade);
		}
		else{
			throw new IllegalArgumentException("Erro - save: Unidade ja existe na base de dados");
		}
	}

	public Unidade update(Unidade unidade) {
		verificarUnidade(unidade);
		return (Unidade) unidadeDao.update(unidade);
	}

	public void delete(Unidade unidade) {
		verificarUnidade(unidade);
		Unidade unit = unidadeDao.buscarPorId(unidade.getIdUnidade());
		
		if(unit == null){
			throw new IllegalArgumentException("Erro - delete: Unidade nao existe na base de dados");
		}
		else{
			unidadeDao.delete(unidade);
		}
	}

	public Unidade buscar(int entityID) {
		Unidade unit = new Unidade();
		unit = buscaId(entityID);
		
		if(unit == null){
			throw new NoResultException("Erro - buscar: Unidade nao encontrada");
		}
		else{
			return unit;
		}
	}

	public List<Unidade> listar() {
		ArrayList<Unidade> units = new ArrayList<Unidade>();
		units = unidadeDao.listar();
		if(units.size() > 0){
			return units;
		}
		else{
			throw new NoResultException("Erro - listar: nenhuma unidade cadastrada");
		}
	}
	
	public ArrayList<Unidade> buscarFiltro(String nomeUnidade){
		ArrayList<Unidade> units = new ArrayList<Unidade>();
		units = unidadeDao.buscarUnidadeFiltro(nomeUnidade);
		if(units.size() > 0){
			return units;
		}
		else{
			return null;
		}
	}

	//Metodo usados apenas para verificacoes no escopo do service
	private Unidade buscaId(int id){
		try{
			Unidade unit = unidadeDao.buscarPorId(id);
			return unit;
		}
		catch(NoResultException e){
			return null;
		}
	}
	
	private void verificarUnidade(Unidade unidade){
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//NOME
		if (unidade.getNome() == null || "".equals(unidade.getNome().trim())){
			hasError = true;
		}
		
		if (hasError){
			throw new IllegalArgumentException("A unidade nao possui todos os dados.");
		}
	}
}
