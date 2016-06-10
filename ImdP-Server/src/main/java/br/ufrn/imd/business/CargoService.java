package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.ufrn.imd.dao.CargoDao;
import br.ufrn.imd.dominio.Cargo;
import br.ufrn.imd.excecoes.DadoIncompletoException;
import br.ufrn.imd.excecoes.DadoJaExisteException;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

@Stateless
public class CargoService{

	@Inject
	private CargoDao cargoDao;
	
	public void save(Cargo cargo) throws DadoJaExisteException, DadoIncompletoException {
		verificarCargo(cargo);
		ArrayList<Cargo> cargs = cargoDao.buscarCargoCheck(cargo.getNome());
		if(cargs.size() == 0){
			cargoDao.save(cargo);
		}
		else{
			throw new DadoJaExisteException("Erro - save: Cargo ja existe na base de dados");
		}
	}

	public Cargo update(Cargo cargo) throws DadoIncompletoException {
		verificarCargo(cargo);
		return (Cargo) cargoDao.update(cargo);
	}

	public void delete(Cargo cargo) throws DadoIncompletoException{
		verificarCargo(cargo);
		cargoDao.delete(cargo);
	}

	public Cargo buscar(int entityID) throws DadoNaoEncontradoException {
		Cargo cargo = new Cargo();
		cargo = cargoDao.buscarPorId(entityID);
		if(cargo == null){
			throw new DadoNaoEncontradoException("Erro - buscar: Cargo nao encontrado");
		}
		else{
			return cargo;
		}
	}

	public List<Cargo> listar() throws DadoNaoEncontradoException {
		ArrayList<Cargo> cargos = new ArrayList<Cargo>();
		cargos = cargoDao.listar();
		if(cargos.size() > 0){
			return cargos;
		}
		else{
			throw new DadoNaoEncontradoException("Erro - listar: nenhum cargo cadastrado");
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
	
	private void verificarCargo(Cargo cargo) throws DadoIncompletoException{
		boolean hasError = false;
		
		//CAMPO OBRIGATORIO
		//NOME
		if (cargo.getNome() == null || "".equals(cargo.getNome().trim())){
			hasError = true;
		}
		
		if (hasError){
			throw new DadoIncompletoException("O cargo nao possui todos os dados.");
		}
	}
}
