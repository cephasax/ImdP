package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.ufrn.imd.dao.UnidadeDao;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.excecoes.DadoIncompletoException;
import br.ufrn.imd.excecoes.DadoJaExisteException;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

@Stateless
public class UnidadeService{

	@Inject
	private UnidadeDao unidadeDao;
	
	public void save(Unidade unidade) throws DadoJaExisteException, DadoIncompletoException {
		verificarUnidade(unidade);
		ArrayList<Unidade> units = unidadeDao.buscarUnidadeCheck(unidade);
		
		if(units.size() == 0){
			unidadeDao.save(unidade);
		}
		else{
			throw new DadoJaExisteException("Erro - save: Unidade ja existe na base de dados");
		}
	}

	public Unidade update(Unidade unidade) throws DadoIncompletoException {
		verificarUnidade(unidade);
		return (Unidade) unidadeDao.update(unidade);
	}

	public void delete(Unidade unidade) throws DadoNaoEncontradoException, DadoIncompletoException {
		verificarUnidade(unidade);		
		unidadeDao.delete(unidade);
	}

	public Unidade buscar(int entityID) throws DadoNaoEncontradoException {
		Unidade unit = new Unidade();
		unit = unidadeDao.buscarPorId(entityID);
		
		if(unit == null){
			throw new DadoNaoEncontradoException("Erro - buscar: Unidade nao encontrada");
		}
		else{
			return unit;
		}
	}

	public List<Unidade> listar() throws DadoNaoEncontradoException {
		ArrayList<Unidade> units = new ArrayList<Unidade>();
		units = unidadeDao.listar();
		
		if(units.size() > 0){
			return units;
		}
		else{
			throw new DadoNaoEncontradoException("Erro - listar: nenhuma unidade cadastrada");
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
	
	private void verificarUnidade(Unidade unidade) throws DadoIncompletoException{
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//NOME
		if (unidade.getNome() == null || "".equals(unidade.getNome().trim())){
			hasError = true;
		}
		
		if (hasError){
			throw new DadoIncompletoException("A unidade nao possui todos os dados.");
		}
	}
}
