package br.ufrn.imd.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.dao.JustificativaFaltaDao;
import br.ufrn.imd.dominio.JustificativaFalta;

@Stateless
public class JustificativaFaltaService{

	
	@Inject
	private JustificativaFaltaDao justificativaFaltaDao;
	
	public void save(JustificativaFalta justificativaFalta) {
		verificarJustificativaFalta(justificativaFalta);
		justificativaFaltaDao.save(justificativaFalta);
	}

	public JustificativaFalta update(JustificativaFalta justificativaFalta) {
		verificarJustificativaFalta(justificativaFalta);
		return (JustificativaFalta) justificativaFaltaDao.update(justificativaFalta);
	}

	public void delete(JustificativaFalta justificativaFalta) {
		justificativaFaltaDao.delete(justificativaFalta);
	}

	public JustificativaFalta find(int entityID) {
		return (JustificativaFalta) justificativaFaltaDao.find(entityID);
	}

	public List<JustificativaFalta> findAll() {
		return justificativaFaltaDao.listar();
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
			throw new IllegalArgumentException("A justificativa de Falta nao possui todos os dados.");
		}
	}
	
}
