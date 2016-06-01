package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.Local;

import br.ufrn.imd.dominio.Vinculo;

@Local
public interface VinculoInterface {

	public abstract void save(Vinculo vinculo);

	public abstract Vinculo update(Vinculo vinculo);
	
	public abstract void delete(Vinculo vinculo);

	public abstract Vinculo find(int entityID);

	public abstract List<Vinculo> findAll();
	
}
