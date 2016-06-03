package br.ufrn.imd.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.dao.SetorDao;
import br.ufrn.imd.dominio.Setor;

@Stateless
public class SetorService{

	@Inject
	private SetorDao setorDao;
	
	public void save(Setor setor) {
		verificarSetor(setor);
		setorDao.save(setor);
	}

	public Setor update(Setor setor) {
		verificarSetor(setor);
		return (Setor) setorDao.update(setor);
	}

	public void delete(Setor setor) {
		setorDao.delete(setor);
	}

	public Setor find(int entityID) {
		return (Setor) setorDao.find(entityID);
	}

	public List<Setor> findAll() {
		return setorDao.listar();
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
