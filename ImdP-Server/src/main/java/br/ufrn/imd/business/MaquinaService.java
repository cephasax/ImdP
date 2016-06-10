package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.ufrn.imd.dao.MaquinaDao;
import br.ufrn.imd.dominio.Maquina;
import br.ufrn.imd.excecoes.DadoIncompletoException;
import br.ufrn.imd.excecoes.DadoJaExisteException;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

@Stateless
public class MaquinaService{

	@Inject
	private MaquinaDao maquinaDao;
	
	public void save(Maquina maquina) throws DadoIncompletoException, DadoJaExisteException {
		verificarMaquina(maquina);
		ArrayList<Maquina> maqs = maquinaDao.buscarMaquinaCheck(maquina);

		if(maqs.size() == 0){
			maquinaDao.save(maquina);
		}
		else{
			throw new DadoJaExisteException("Erro - save: Maquina ja existe na base de dados");
		}
	}

	public Maquina update(Maquina maquina) throws DadoIncompletoException {
		verificarMaquina(maquina);
		return (Maquina) maquinaDao.update(maquina);
	}

	public void delete(Maquina maquina) throws DadoIncompletoException {
		verificarMaquina(maquina);
		maquinaDao.delete(maquina);
	}

	public Maquina buscar(int entityID) throws DadoNaoEncontradoException {
		Maquina maq = new Maquina();
		maq = buscaId(entityID);
		
		if(maq == null){
			throw new DadoNaoEncontradoException("Erro - buscar: Maquina nao encontrada");
		}
		else{
			return maq;
		}
	}

	public List<Maquina> listar() throws DadoNaoEncontradoException {
		ArrayList<Maquina> maqs = new ArrayList<Maquina>();
		maqs = maquinaDao.listar();
		if(maqs.size() > 0){
			return maqs;
		}
		else{
			throw new DadoNaoEncontradoException("Erro - listar: nenhuma maquina cadastrada");
		}
	}	
	
	public ArrayList<Maquina> buscarFiltro(String nomeMaquina, int idUnidade){
		ArrayList<Maquina> maqs = new ArrayList<Maquina>();
		maqs = maquinaDao.buscarMaquinaFiltro(nomeMaquina, idUnidade);
		if(maqs.size() > 0){
			return maqs;
		}
		else{
			return null;
		}
	}

	//Metodo usados apenas para verificacoes no escopo do service
	private Maquina buscaId(int id){
		try{
			Maquina maq = maquinaDao.buscarPorId(id);
			return maq;
		}
		catch(NoResultException e){
			return null;
		}
	}
	
	private void verificarMaquina(Maquina maquina) throws DadoIncompletoException{
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//IP
		if (maquina.getIp() == null || "".equals(maquina.getIp().trim())){
			hasError = true;
		}
		
		//id UNIDADE
		if (maquina.getUnidade().getIdUnidade() <= 0){
			hasError = true;
		}
		
		if (hasError){
			throw new DadoIncompletoException("A maquina nao possui todos os dados.");
		}
	}
}
