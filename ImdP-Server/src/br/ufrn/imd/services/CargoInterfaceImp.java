package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.dao.CargoDao;
import br.ufrn.imd.dominio.Cargo;

@Stateless
public class CargoInterfaceImp implements CargoInterface{

	@EJB
	private CargoDao cargoDao;
	
	@Override
	public void save(Cargo cargo) {
		verificarCargo(cargo);
		cargoDao.save(cargo);
	}

	@Override
	public Cargo update(Cargo cargo) {
		verificarCargo(cargo);
		return (Cargo) cargoDao.update(cargo);
	}

	@Override
	public void delete(Cargo cargo) {
		cargoDao.delete(cargo);
	}

	@Override
	public Cargo find(int entityID) {
		return (Cargo) cargoDao.find(entityID);
	}

	@Override
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
