package br.ufrn.imd.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.dao.TipoJustificativaDao;
import br.ufrn.imd.dominio.TipoJustificativa;

@Stateless
public class TipoJustificativaService{

	@Inject
	private TipoJustificativaDao tipoJustificativaDao;
	
	public void save(TipoJustificativa tipoJustificativa) {
		verificarTipoJustificativa(tipoJustificativa);
		tipoJustificativaDao.save(tipoJustificativa);
	}

	public TipoJustificativa update(TipoJustificativa tipoJustificativa) {
		verificarTipoJustificativa(tipoJustificativa);
		return (TipoJustificativa) tipoJustificativaDao.update(tipoJustificativa);
	}

	public void delete(TipoJustificativa tipoJustificativa) {
		tipoJustificativaDao.delete(tipoJustificativa);
	}

	public TipoJustificativa find(int entityID) {
		return (TipoJustificativa) tipoJustificativaDao.find(entityID);
	}

	public List<TipoJustificativa> findAll() {
		return tipoJustificativaDao.listar();
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
