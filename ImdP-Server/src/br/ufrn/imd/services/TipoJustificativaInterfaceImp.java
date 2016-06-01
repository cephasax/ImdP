package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.dao.TipoJustificativaDao;
import br.ufrn.imd.dominio.TipoJustificativa;

@Stateless
public class TipoJustificativaInterfaceImp implements TipoJustificativaInterface{

	
	@EJB
	private TipoJustificativaDao tipoJustificativaDao;
	
	@Override
	public void save(TipoJustificativa tipoJustificativa) {
		verificarTipoJustificativa(tipoJustificativa);
		tipoJustificativaDao.save(tipoJustificativa);
	}

	@Override
	public TipoJustificativa update(TipoJustificativa tipoJustificativa) {
		verificarTipoJustificativa(tipoJustificativa);
		return (TipoJustificativa) tipoJustificativaDao.update(tipoJustificativa);
	}

	@Override
	public void delete(TipoJustificativa tipoJustificativa) {
		tipoJustificativaDao.delete(tipoJustificativa);
	}

	@Override
	public TipoJustificativa find(int entityID) {
		return (TipoJustificativa) tipoJustificativaDao.find(entityID);
	}

	@Override
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
