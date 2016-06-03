package br.ufrn.imd.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.dao.CargoDao;
import br.ufrn.imd.dominio.Cargo;

@Stateless
public class CargoService{

	@Inject
	private CargoDao cargoDao;
	
	public void save(Cargo cargo) {
		verificarCargo(cargo);
		cargoDao.save(cargo);
	}

	public Cargo update(Cargo cargo) {
		verificarCargo(cargo);
		return (Cargo) cargoDao.update(cargo);
	}

	public void delete(Cargo cargo) {
		cargoDao.delete(cargo);
	}

	public Cargo find(int entityID) {
		return (Cargo) cargoDao.find(entityID);
	}

	public List<Cargo> findAll() {
		return cargoDao.listar();
	}
	
	private void verificarCargo(Cargo cargo){
		boolean hasError = false;
		
		//CAMPO OBRIGATORIO
		//NOME
		if (cargo.getNome() == null || "".equals(cargo.getNome().trim())){
			hasError = true;
		}
		
		if (hasError){
			throw new IllegalArgumentException("O cargo nao possui todos os dados.");
		}
	}
	
}
