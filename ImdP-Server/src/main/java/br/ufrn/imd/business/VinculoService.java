package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.ufrn.imd.dao.VinculoDao;
import br.ufrn.imd.dominio.Vinculo;

@Stateless
public class VinculoService{

	@Inject
	private VinculoDao vinculoDao;
	
	public void save(Vinculo vinculo) {
		verificarVinculo(vinculo);
		Vinculo vinc = vinculoDao.buscarPorId(vinculo.getIdVinculo());
		
		if(vinc == null){
			vinculoDao.save(vinculo);
		}
		else{
			throw new IllegalArgumentException("Erro - save: Vinculo ja existe na base de dados");
		}
	}

	public Vinculo update(Vinculo vinculo) {
		verificarVinculo(vinculo);
		return (Vinculo) vinculoDao.update(vinculo);
	}

	public void delete(Vinculo vinculo) {
		verificarVinculo(vinculo);
		Vinculo vinc = vinculoDao.buscarPorId(vinculo.getIdVinculo());
		
		if(vinc == null){
			throw new IllegalArgumentException("Erro - delete: Vinculo nao existe na base de dados");
		}
		else{
			vinculoDao.delete(vinculo);
		}
	}

	public Vinculo buscar(int entityID) {
		Vinculo vinc = new Vinculo();
		vinc = buscaId(entityID);
		
		if(vinc == null){
			throw new NoResultException("Erro - buscar: Vinculo nao encontrado");
		}
		else{
			return vinc;
		}
	}

	public List<Vinculo> listar() {
		ArrayList<Vinculo> vincs = new ArrayList<Vinculo>();
		vincs = vinculoDao.listar();
		if(vincs.size() > 0){
			return vincs;
		}
		else{
			throw new NoResultException("Erro - listar: nenhum vinculo cadastrado");
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

	//Metodo usados apenas para verificacoes no escopo do service
	private Vinculo buscaId(int id){
		try{
			Vinculo vinc = vinculoDao.buscarPorId(id);
			return vinc;
		}
		catch(NoResultException e){
			return null;
		}
	}
	
	private void verificarVinculo(Vinculo vinculo){
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//SETOR
		if (vinculo.getSetor() == null){
			hasError = true;
		}
		
		//USUARIO
		if (vinculo.getUsuario() == null){
			hasError = true;
		}
		
		//PERMISSAO
		if (vinculo.getPermissao() == null){
			hasError = true;
		}
		
		//CARGA HORARIA DIARIA
		if (vinculo.getCargaHorariaDiaria() < 0){
			hasError = true;
		}
		
		//SITUACAO
		if (vinculo.getSituacao() == ' '){
			hasError = true;
		}
		
		if (hasError){
			throw new IllegalArgumentException("O vinculo nao possui todos os dados.");
		}
	}
}
