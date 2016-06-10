package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.ufrn.imd.dao.TipoJustificativaDao;
import br.ufrn.imd.dominio.TipoJustificativa;
import br.ufrn.imd.excecoes.DadoIncompletoException;
import br.ufrn.imd.excecoes.DadoJaExisteException;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

@Stateless
public class TipoJustificativaService{

	@Inject
	private TipoJustificativaDao tipoJustificativaDao;
	
	public void save(TipoJustificativa tipoJustificativa) throws DadoJaExisteException, DadoIncompletoException {
		verificarTipoJustificativa(tipoJustificativa);
		ArrayList<TipoJustificativa> tjs = tipoJustificativaDao.buscarTipoJustificativaCheck(tipoJustificativa);
		
		if(tjs.size() == 0){
			tipoJustificativaDao.save(tipoJustificativa);
		}
		else{
			throw new DadoJaExisteException("Erro - save: tipo de justificativa ja existe na base de dados");
		}
	}

	public TipoJustificativa update(TipoJustificativa tipoJustificativa) throws DadoIncompletoException {
		verificarTipoJustificativa(tipoJustificativa);
		return (TipoJustificativa) tipoJustificativaDao.update(tipoJustificativa);
	}

	public void delete(TipoJustificativa tipoJustificativa) throws DadoIncompletoException {
		verificarTipoJustificativa(tipoJustificativa);
		tipoJustificativaDao.delete(tipoJustificativa);
	}

	public TipoJustificativa buscar(int entityID) throws DadoNaoEncontradoException {
		TipoJustificativa tj = new TipoJustificativa();
		tj = tipoJustificativaDao.buscarPorId(entityID);
		
		if(tj == null){
			throw new DadoNaoEncontradoException("Erro - buscar: tipo de justificativa nao encontrado");
		}
		else{
			return tj;
		}
	}

	public List<TipoJustificativa> listar() throws DadoNaoEncontradoException {
		ArrayList<TipoJustificativa> tjs = new ArrayList<TipoJustificativa>();
		tjs = tipoJustificativaDao.listar();
		if(tjs.size() > 0){
			return tjs;
		}
		else{
			throw new DadoNaoEncontradoException("Erro - listar: nenhum tipo de justificativa cadastrado");
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

	private void verificarTipoJustificativa(TipoJustificativa tipoJustificativa) throws DadoIncompletoException{
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//NOME
		if (tipoJustificativa.getNome() == null || "".equals(tipoJustificativa.getNome().trim())){
			hasError = true;
		}
		
		if (hasError){
			throw new DadoIncompletoException("O tipo de justificativa nao possui todos os dados.");
		}
	}
}
