package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.dao.UnidadeDao;
import br.ufrn.imd.dominio.Unidade;

@Stateless
public class UnidadeInterfaceImp implements UnidadeInterface{

	
	@EJB
	private UnidadeDao unidadeDao;
	
	@Override
	public void save(Unidade unidade) {
		verificarUnidade(unidade);
		unidadeDao.save(unidade);
	}

	@Override
	public Unidade update(Unidade unidade) {
		verificarUnidade(unidade);
		return (Unidade) unidadeDao.update(unidade);
	}

	@Override
	public void delete(Unidade unidade) {
		unidadeDao.delete(unidade);
	}

	@Override
	public Unidade find(int entityID) {
		return (Unidade) unidadeDao.find(entityID);
	}

	@Override
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
