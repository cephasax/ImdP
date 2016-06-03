package br.ufrn.imd.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.dao.UnidadeDao;
import br.ufrn.imd.dominio.Unidade;

@Stateless
public class UnidadeService{

	@Inject
	private UnidadeDao unidadeDao;
	
	public void save(Unidade unidade) {
		verificarUnidade(unidade);
		unidadeDao.save(unidade);
	}

	public Unidade update(Unidade unidade) {
		verificarUnidade(unidade);
		return (Unidade) unidadeDao.update(unidade);
	}

	public void delete(Unidade unidade) {
		unidadeDao.delete(unidade);
	}

	public Unidade find(int entityID) {
		return (Unidade) unidadeDao.find(entityID);
	}

	public List<Unidade> findAll() {
		return unidadeDao.listar();
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
