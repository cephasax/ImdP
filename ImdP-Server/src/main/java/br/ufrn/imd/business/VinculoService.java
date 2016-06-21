package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.dao.VinculoDao;
import br.ufrn.imd.dominio.Vinculo;
import br.ufrn.imd.excecoes.DadoIncompletoException;
import br.ufrn.imd.excecoes.DadoJaExisteException;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

@Stateless
public class VinculoService{

	@Inject
	private VinculoDao vinculoDao;
	
	@SuppressWarnings("unchecked")
	public void save(Vinculo vinculo) throws DadoJaExisteException, DadoIncompletoException {
		verificarVinculo(vinculo);
		ArrayList<Vinculo> vincs = vinculoDao.buscarVinculoCheck(vinculo);
		if(vincs.size() == 0){
			vinculoDao.save(vinculo);
		}
		else{
			throw new DadoJaExisteException("Erro - save: Vinculo ja existe na base de dados");
		}
	}

	@SuppressWarnings("unchecked")
	public Vinculo update(Vinculo vinculo) throws DadoIncompletoException {
		verificarVinculo(vinculo);
		return (Vinculo) vinculoDao.update(vinculo);
	}

	public void delete(Vinculo vinculo) throws DadoNaoEncontradoException, DadoIncompletoException {
		verificarVinculo(vinculo);
		vinculoDao.delete(vinculo);
	}

	public Vinculo buscar(int entityID) throws DadoNaoEncontradoException {
		Vinculo vinc = new Vinculo();
		vinc = vinculoDao.buscarPorId(entityID);
		if(vinc == null){
			throw new DadoNaoEncontradoException("Erro - buscar: Vinculo nao encontrado");
		}
		else{
			return vinc;
		}
	}

	public List<Vinculo> listar() throws DadoNaoEncontradoException {
		ArrayList<Vinculo> vincs = new ArrayList<Vinculo>();
		vincs = vinculoDao.listar();
		if(vincs.size() > 0){
			return vincs;
		}
		else{
			throw new DadoNaoEncontradoException("Erro - listar: nenhum vinculo cadastrado");
		}
	}
	
	public ArrayList<Vinculo> buscarFiltro(String nomeUsuario, int idUnidade, int idSetor){
		ArrayList<Vinculo> vincs = new ArrayList<Vinculo>();
		vincs = vinculoDao.buscarVinculoFiltro(nomeUsuario, idUnidade, idSetor);
		if(vincs.size() > 0){
			return vincs;
		}
		else{
			return null;
		}
	}
	
	public ArrayList<Vinculo> buscarVinculosUsuario(int idUsuario){
		ArrayList<Vinculo> vincs = new ArrayList<Vinculo>();
		vincs = vinculoDao.listarVinculosUsuario(idUsuario);
		if(vincs.size() > 0){
			return vincs;
		}
		else{
			return null;
		}
	}
	

	private void verificarVinculo(Vinculo vinculo) throws DadoIncompletoException{
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//IdSETOR
		if (vinculo.getSetor().getIdSetor() <= 0){
			hasError = true;
		}
		
		//IdUSUARIO
		if (vinculo.getUsuario().getIdUsuario() <= 0){
			hasError = true;
		}
		
		//IdPERMISSAO
		if (vinculo.getPermissao().getIdPermissao() <= 0){
			hasError = true;
		}
		
		//CARGA HORARIA DIARIA
		if (vinculo.getCargaHorariaDiaria() <= 0){
			hasError = true;
		}
		
		//SITUACAO
		if (vinculo.getSituacao() == ' '){
			hasError = true;
		}
		
		if (hasError){
			throw new DadoIncompletoException("O vinculo nao possui todos os dados.");
		}
	}
}
