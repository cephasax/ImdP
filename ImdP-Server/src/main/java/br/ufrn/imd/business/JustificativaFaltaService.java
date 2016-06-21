package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.dao.JustificativaFaltaDao;
import br.ufrn.imd.dominio.JustificativaFalta;
import br.ufrn.imd.excecoes.DadoIncompletoException;
import br.ufrn.imd.excecoes.DadoJaExisteException;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

@Stateless
public class JustificativaFaltaService{

	@Inject
	private JustificativaFaltaDao justificativaFaltaDao;
	
	@SuppressWarnings("unchecked")
	public void save(JustificativaFalta justificativaFalta) throws DadoJaExisteException, DadoIncompletoException {
		verificarJustificativaFalta(justificativaFalta);
		ArrayList<JustificativaFalta> justficativas = justificativaFaltaDao.buscarJustificativaCheck(justificativaFalta);
		
		if(justficativas.size() == 0){
			justificativaFaltaDao.save(justificativaFalta);
		}
		else{
			throw new DadoJaExisteException("Erro - save: JustificativaFalta ja existe na base de dados");
		}
	}

	@SuppressWarnings("unchecked")
	public JustificativaFalta update(JustificativaFalta justificativaFalta) throws DadoIncompletoException {
		verificarJustificativaFalta(justificativaFalta);
		return (JustificativaFalta) justificativaFaltaDao.update(justificativaFalta);
	}

	public void delete(JustificativaFalta justificativaFalta) throws DadoIncompletoException{
		verificarJustificativaFalta(justificativaFalta);
		justificativaFaltaDao.delete(justificativaFalta);
	}

	public JustificativaFalta buscar(int entityID) throws DadoNaoEncontradoException {
		JustificativaFalta justficativa = new JustificativaFalta();
		justficativa = justificativaFaltaDao.buscarPorId(entityID);
		
		if(justficativa == null){
			throw new DadoNaoEncontradoException("Erro - buscar: Justificativa de falta nao encontrada");
		}
		else{
			return justficativa;
		}
	}

	public List<JustificativaFalta> listar() throws DadoNaoEncontradoException {
		ArrayList<JustificativaFalta> justficativas = new ArrayList<JustificativaFalta>();
		justficativas = justificativaFaltaDao.listar();
		if(justficativas.size() > 0){
			return justficativas;
		}
		else{
			throw new DadoNaoEncontradoException("Erro - listar: nenhuma justificativa de falta cadastrado");
		}
	}
	
	public ArrayList<JustificativaFalta> buscarFiltro(String nomeUsuario, int idUnidade, int idSetor){
		ArrayList<JustificativaFalta> justficativas = new ArrayList<JustificativaFalta>();
		justficativas = justificativaFaltaDao.buscarJustificativaFiltro(nomeUsuario, idUnidade, idSetor);
		if(justficativas.size() > 0){
			return justficativas;
		}
		else{
			return null;
		}
	}

	private void verificarJustificativaFalta(JustificativaFalta justificativaFalta) throws DadoIncompletoException{
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//Descricao
		if (justificativaFalta.getDescricao() == null || "".equals(justificativaFalta.getDescricao().trim())){
			hasError = true;
		}
		
		//Data de inicio
		if (justificativaFalta.getDataInicio() == null || "".equals(justificativaFalta.getDataInicio())){
			hasError = true;
		}
		
		//Data de fim
		if (justificativaFalta.getDataFim() == null || "".equals(justificativaFalta.getDataFim())){
			hasError = true;
		}
		
		//Id Vinculo
		if(justificativaFalta.getVinculo().getIdVinculo() <= 0){
			hasError = true;
		}

		if (hasError){
			throw new DadoIncompletoException("A justificativa de falta nao possui todos os dados.");
		}
	}
}
