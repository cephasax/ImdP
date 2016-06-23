package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.dao.ImpressaoDigitalDao;
import br.ufrn.imd.dominio.Cargo;
import br.ufrn.imd.dominio.ImpressaoDigital;
import br.ufrn.imd.excecoes.DadoIncompletoException;
import br.ufrn.imd.excecoes.DadoJaExisteException;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

@Stateless
public class ImpressaoDigitalService{

	@Inject
	private ImpressaoDigitalDao digitalDao;
	
	@SuppressWarnings("unchecked")
	public void save(ImpressaoDigital digital) throws DadoJaExisteException, DadoIncompletoException {
		verificarImpressaoDigital(digital);
		ArrayList<ImpressaoDigital> digitais = digitalDao.buscarImpressaoDigitalCheck(digital.getNomeDedo(), 
				digital.getUsuario().getIdUsuario());
		if(digitais.size() == 0){
			digitalDao.save(digital);
		}
		else{
			throw new DadoJaExisteException("Erro - save: ImpressaoDigital ja existe na base de dados");
		}
	}
	
	public ImpressaoDigital buscar(int entityID) throws DadoNaoEncontradoException {
		ImpressaoDigital digital = new ImpressaoDigital();
		digital = digitalDao.buscarPorId(entityID);
		if(digital == null){
			throw new DadoNaoEncontradoException("Erro - buscar: ImpressaoDigital nao encontrado");
		}
		else{
			return digital;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ImpressaoDigital update(ImpressaoDigital digital) throws DadoIncompletoException {
		verificarImpressaoDigital(digital);
		return (ImpressaoDigital) digitalDao.update(digital);
	}

	public void delete(ImpressaoDigital digital) throws DadoIncompletoException{
		verificarImpressaoDigital(digital);
		digitalDao.delete(digital);
	}

	public ArrayList<ImpressaoDigital> buscarPorUsuario(int idUsuario) throws DadoNaoEncontradoException {
		ArrayList<ImpressaoDigital> digitais = new ArrayList<ImpressaoDigital>();
		digitais = digitalDao.buscarImpressaoDigitalUsuario(idUsuario);
		if(digitais.size() == 0){
			throw new DadoNaoEncontradoException("Erro - buscar: ImpressaoDigital nao encontrada");
		}
		else{
			return digitais;
		}
	}
	
	private void verificarImpressaoDigital(ImpressaoDigital digital) throws DadoIncompletoException{
		boolean hasError = false;
		
		//CAMPO OBRIGATORIO
		//NOME DO DEDO
		if (digital.getNomeDedo() == null || "".equals(digital.getNomeDedo().trim())){
			hasError = true;
		}
		
		//ID USUARIO
		if (digital.getUsuario().getIdUsuario() <= 0){
			hasError = true;
		}
		
		//IMPRESSAO DIGITAL - BLOB
		if(digital.getDigital() == null){
			hasError = true;
		}
		
		if (hasError){
			throw new DadoIncompletoException("A digital nao possui todos os dados.");
		}
	}
}
