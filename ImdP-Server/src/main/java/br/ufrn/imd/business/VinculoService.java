package br.ufrn.imd.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.dao.VinculoDao;
import br.ufrn.imd.dominio.Vinculo;

@Stateless
public class VinculoService {

	@Inject
	private VinculoDao vinculoDao;
	
	public void save(Vinculo vinculo) {
		verificarVinculo(vinculo);
		vinculoDao.save(vinculo);
	}

	public Vinculo update(Vinculo vinculo) {
		verificarVinculo(vinculo);
		return (Vinculo) vinculoDao.update(vinculo);
	}

	public void delete(Vinculo vinculo) {
		vinculoDao.delete(vinculo);
	}

	public Vinculo find(int entityID) {
		return (Vinculo) vinculoDao.find(entityID);
	}

	public List<Vinculo> findAll() {
		return vinculoDao.listar();
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
