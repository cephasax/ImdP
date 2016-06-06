package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.ufrn.imd.dao.CargoDao;
import br.ufrn.imd.dominio.Cargo;

@Stateless
public class CargoService{

	@Inject
	private CargoDao cargoDao;
	
	public void save(Cargo cargo) {
		verificarCargo(cargo);
		Cargo carg = cargoDao.buscarPorId(cargo.getIdCargo());
		if(carg == null){
			cargoDao.save(cargo);
		}
		else{
			throw new IllegalArgumentException("Erro - save: Cargo ja existe na base de dados");
		}
	}

	public Cargo update(Cargo cargo) {
		verificarCargo(cargo);
		return (Cargo) cargoDao.update(cargo);
	}

	public void delete(Cargo cargo) {
		verificarCargo(cargo);
		Cargo carg = cargoDao.buscarPorId(cargo.getIdCargo());
		
		if(carg == null){
			throw new IllegalArgumentException("Erro - delete: Cargo nao existe na base de dados");
		}
		else{
			cargoDao.delete(cargo);
		}
	}

	public Cargo buscar(int entityID) {
		Cargo cargo = new Cargo();
		cargo = buscaId(entityID);
		
		if(cargo == null){
			throw new NoResultException("Erro - buscar: Cargo nao encontrado");
		}
		else{
			return cargo;
		}
	}

	public List<Cargo> listar() {
		ArrayList<Cargo> cargos = new ArrayList<Cargo>();
		cargos = cargoDao.listar();
		if(cargos.size() > 0){
			return cargos;
		}
		else{
			throw new NoResultException("Erro - listar: nenhum cargo cadastrado");
		}
	}
	
	public ArrayList<Cargo> buscarFiltro(String nomeCargo){
		ArrayList<Cargo> cargos = new ArrayList<Cargo>();
		cargos = cargoDao.buscarCargoFiltro(nomeCargo);
		if(cargos.size() > 0){
			return cargos;
		}
		else{
			return null;
		}
	}

	//Metodo usados apenas para verificacoes no escopo do service
	private Cargo buscaId(int id){
		try{
			Cargo cargo = cargoDao.buscarPorId(id);
			return cargo;
		}
		catch(NoResultException e){
			return null;
		}
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
