package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.dao.SetorDao;
import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.excecoes.DadoIncompletoException;
import br.ufrn.imd.excecoes.DadoJaExisteException;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

@Stateless
public class SetorService{

	@Inject
	private SetorDao setorDao;
	
	public void save(Setor setor) throws DadoJaExisteException, DadoIncompletoException {
		verificarSetor(setor);
		ArrayList<Setor> places = setorDao.buscarSetorCheck(setor);
		
		if(places.size() == 0){
			setorDao.save(setor);
		}
		else{
			throw new DadoJaExisteException("Erro - save: Setor ja existe na base de dados");
		}
	}

	public Setor update(Setor setor) throws DadoIncompletoException {
		verificarSetor(setor);
		return (Setor) setorDao.update(setor);
	}

	public void delete(Setor setor) throws DadoIncompletoException {
		verificarSetor(setor);
		setorDao.delete(setor);
	}

	public Setor buscar(int entityID) throws DadoNaoEncontradoException {
		Setor place = new Setor();
		place = setorDao.buscarPorId(entityID);
		
		if(place == null){
			throw new DadoNaoEncontradoException("Erro - buscar: Setor nao encontrado");
		}
		else{
			return place;
		}
	}

	public List<Setor> listar() throws DadoNaoEncontradoException {
		ArrayList<Setor> places = new ArrayList<Setor>();
		places = setorDao.listar();
		if(places.size() > 0){
			return places;
		}
		else{
			throw new DadoNaoEncontradoException("Erro - listar: nenhum setor cadastrado");
		}
	}
	
	public ArrayList<Setor> buscarFiltro(String nomeSetor, int idUnidade){
		ArrayList<Setor> places = new ArrayList<Setor>();
		places = setorDao.buscarSetorFiltro(nomeSetor, idUnidade);
		if(places.size() > 0){
			return places;
		}
		else{
			return null;
		}
	}

	private void verificarSetor(Setor setor) throws DadoIncompletoException{
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//NOME
		if (setor.getNome() == null || "".equals(setor.getNome().trim())){
			hasError = true;
		}
		
		//Id UNIDADE
		if(setor.getUnidade().getIdUnidade() <= 0){
			hasError = true;
		}
		
		if (hasError){
			throw new DadoIncompletoException("O setor nao possui todos os dados.");
		}
	}
}
