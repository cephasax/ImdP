package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.Local;

import br.ufrn.imd.dominio.Unidade;

@Local
public interface UnidadeInterface {

	public abstract void save(Unidade unidade);

	public abstract Unidade update(Unidade unidade);
	
	public abstract void delete(Unidade unidade);

	public abstract Unidade find(int entityID);

	public abstract List<Unidade> findAll();
	
}
