package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.ufrn.imd.dao.TipoJustificativaDao;
import br.ufrn.imd.dominio.TipoJustificativa;

@Stateless
public class TipoJustificativaService{

	@Inject
	private TipoJustificativaDao tipoJustificativaDao;
	
	public void save(TipoJustificativa tipoJustificativa) {
		verificarTipoJustificativa(tipoJustificativa);
		TipoJustificativa tj = tipoJustificativaDao.buscarPorId(tipoJustificativa.getIdTipoJustificativa());
		
		if(tj == null){
			tipoJustificativaDao.save(tipoJustificativa);
		}
		else{
			throw new IllegalArgumentException("Erro - save: tipo de justificativa ja existe na base de dados");
		}
	}

	public TipoJustificativa update(TipoJustificativa tipoJustificativa) {
		verificarTipoJustificativa(tipoJustificativa);
		return (TipoJustificativa) tipoJustificativaDao.update(tipoJustificativa);
	}

	public void delete(TipoJustificativa tipoJustificativa) {
		verificarTipoJustificativa(tipoJustificativa);
		TipoJustificativa tj = tipoJustificativaDao.buscarPorId(tipoJustificativa.getIdTipoJustificativa());
		
		if(tj == null){
			throw new IllegalArgumentException("Erro - delete: tipo de justificativa nao existe na base de dados");
		}
		else{
			tipoJustificativaDao.delete(tipoJustificativa);
		}
	}

	public TipoJustificativa buscar(int entityID) {
		TipoJustificativa tj = new TipoJustificativa();
		tj = buscaId(entityID);
		
		if(tj == null){
			throw new NoResultException("Erro - buscar: tipo de justificativa nao encontrado");
		}
		else{
			return tj;
		}
	}

	public List<TipoJustificativa> listar() {
		ArrayList<TipoJustificativa> tjs = new ArrayList<TipoJustificativa>();
		tjs = tipoJustificativaDao.listar();
		if(tjs.size() > 0){
			return tjs;
		}
		else{
			throw new NoResultException("Erro - listar: nenhum tipo de justificativa cadastrado");
		}
	}
	
	public ArrayList<TipoJustificativa> buscarFiltro(String nomeTipoJustificativa){
		ArrayList<TipoJustificativa> tjs = new ArrayList<TipoJustificativa>();
		tjs = tipoJustificativaDao.buscarTipoJustificativaFiltro(nomeTipoJustificativa);
		if(tjs.size() > 0){
			return tjs;
		}
		else{
			return null;
		}
	}

	//Metodo usados apenas para verificacoes no escopo do service
	private TipoJustificativa buscaId(int id){
		try{
			TipoJustificativa tj = tipoJustificativaDao.buscarPorId(id);
			return tj;
		}
		catch(NoResultException e){
			return null;
		}
	}

	private void verificarTipoJustificativa(TipoJustificativa tipoJustificativa){
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//NOME
		if (tipoJustificativa.getNome() == null || "".equals(tipoJustificativa.getNome().trim())){
			hasError = true;
		}
		
		if (hasError){
			throw new IllegalArgumentException("O tipo de justificativa nao possui todos os dados.");
		}
	}
}
