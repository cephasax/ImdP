package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.ufrn.imd.dao.MesTrabalhoDao;
import br.ufrn.imd.dominio.MesTrabalho;
import br.ufrn.imd.excecoes.DadoIncompletoException;
import br.ufrn.imd.excecoes.DadoJaExisteException;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

@Stateless
public class MesTrabalhoService{

	@Inject
	private MesTrabalhoDao mesTrabalhoDao;
	
	public void save(MesTrabalho mesTrabalho) throws DadoJaExisteException, DadoIncompletoException {
		verificarMesTrabalho(mesTrabalho);
		ArrayList<MesTrabalho> months = mesTrabalhoDao.buscarMesTrabalhoCheck(mesTrabalho);
		
		if(months.size() == 0){
			mesTrabalhoDao.save(mesTrabalho);
		}
		else{
			throw new DadoJaExisteException("Erro - save: MesTrabalho ja existe na base de dados");
		}
	}

	public MesTrabalho update(MesTrabalho mesTrabalho) throws DadoIncompletoException {
		verificarMesTrabalho(mesTrabalho);
		return (MesTrabalho) mesTrabalhoDao.update(mesTrabalho);
	}

	public void delete(MesTrabalho mesTrabalho) throws DadoIncompletoException {
		verificarMesTrabalho(mesTrabalho);
		mesTrabalhoDao.delete(mesTrabalho);
	}

	public MesTrabalho buscar(int entityID) throws DadoNaoEncontradoException {
		MesTrabalho month = new MesTrabalho();
		month = mesTrabalhoDao.buscarPorId(entityID);
		
		if(month == null){
			throw new DadoNaoEncontradoException("Erro - buscar: MesTrabalho nao encontrado");
		}
		else{
			return month;
		}
	}

	public List<MesTrabalho> listar() throws DadoNaoEncontradoException {
		ArrayList<MesTrabalho> months = new ArrayList<MesTrabalho>();
		months = mesTrabalhoDao.listar();
		if(months.size() > 0){
			return months;
		}
		else{
			throw new DadoNaoEncontradoException("Erro - listar: nenhum mesTrabalho cadastrado");
		}
	}
	
	public ArrayList<MesTrabalho> buscarFiltro(int ano, int mes){
		ArrayList<MesTrabalho> months = new ArrayList<MesTrabalho>();
		months = mesTrabalhoDao.buscarMesTrabalhoFiltro(ano, mes);
		if(months.size() > 0){
			return months;
		}
		else{
			return null;
		}
	}
		
	private void verificarMesTrabalho(MesTrabalho mesTrabalho) throws DadoIncompletoException{
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//ANO
		if (mesTrabalho.getAno() <= 0 ){
			hasError = true;
		}
		
		//QUANTIDADE DE DIAS
		if (mesTrabalho.getDiasUteis() <= 0){
			hasError = true;
		}
		
		//MES
		if (mesTrabalho.getMes() <= 0 || mesTrabalho.getMes() > 12){
			hasError = true;
		}
		
		if (hasError){
			throw new DadoIncompletoException("O mesTrabalho nao possui todos os dados.");
		}
	}
}
