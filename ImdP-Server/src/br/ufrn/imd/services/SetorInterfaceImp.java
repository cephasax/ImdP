package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.dao.SetorDao;
import br.ufrn.imd.dominio.Setor;

@Stateless
public class SetorInterfaceImp implements SetorInterface{

	
	@EJB
	private SetorDao setorDao;
	
	@Override
	public void save(Setor setor) {
		verificarSetor(setor);
		setorDao.save(setor);
	}

	@Override
	public Setor update(Setor setor) {
		verificarSetor(setor);
		return (Setor) setorDao.update(setor);
	}

	@Override
	public void delete(Setor setor) {
		setorDao.delete(setor);
	}

	@Override
	public Setor find(int entityID) {
		return (Setor) setorDao.find(entityID);
	}

	@Override
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
