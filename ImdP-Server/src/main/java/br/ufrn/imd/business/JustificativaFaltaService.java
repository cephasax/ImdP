package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.ufrn.imd.dao.JustificativaFaltaDao;
import br.ufrn.imd.dominio.JustificativaFalta;

@Stateless
public class JustificativaFaltaService{

	@Inject
	private JustificativaFaltaDao justificativaFaltaDao;
	
	public void save(JustificativaFalta justificativaFalta) {
		verificarJustificativaFalta(justificativaFalta);
		JustificativaFalta justficativa = justificativaFaltaDao.buscarPorId(justificativaFalta.getIdJustificativaFalta());
		
		if(justficativa == null){
			justificativaFaltaDao.save(justificativaFalta);
		}
		else{
			throw new IllegalArgumentException("Erro - save: JustificativaFalta ja existe na base de dados");
		}
	}

	public JustificativaFalta update(JustificativaFalta justificativaFalta) {
		verificarJustificativaFalta(justificativaFalta);
		return (JustificativaFalta) justificativaFaltaDao.update(justificativaFalta);
	}

	public void delete(JustificativaFalta justificativaFalta) {
		verificarJustificativaFalta(justificativaFalta);
		JustificativaFalta justficativa = justificativaFaltaDao.buscarPorId(justificativaFalta.getIdJustificativaFalta());
		
		if(justficativa == null){
			throw new IllegalArgumentException("Erro - delete: Justificativa de falta nao existe na base de dados");
		}
		else{
			justificativaFaltaDao.delete(justificativaFalta);
		}
	}

	public JustificativaFalta buscar(int entityID) {
		JustificativaFalta justficativa = new JustificativaFalta();
		justficativa = buscaId(entityID);
		
		if(justficativa == null){
			throw new NoResultException("Erro - buscar: Justificativa de falta nao encontrada");
		}
		else{
			return justficativa;
		}
	}

	public List<JustificativaFalta> listar() {
		ArrayList<JustificativaFalta> justficativas = new ArrayList<JustificativaFalta>();
		justficativas = justificativaFaltaDao.listar();
		if(justficativas.size() > 0){
			return justficativas;
		}
		else{
			throw new NoResultException("Erro - listar: nenhuma justificativa de falta cadastrado");
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

	//Metodo usados apenas para verificacoes no escopo do service
	private JustificativaFalta buscaId(int id){
		try{
			JustificativaFalta justficativa = justificativaFaltaDao.buscarPorId(id);
			return justficativa;
		}
		catch(NoResultException e){
			return null;
		}
	}
	
	private void verificarJustificativaFalta(JustificativaFalta justificativaFalta){
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
		
		//Vinculo
		if (justificativaFalta.getVinculo() == null){
			hasError = true;
		}
		if (hasError){
			throw new IllegalArgumentException("A justificativa de falta nao possui todos os dados.");
		}
	}
}
