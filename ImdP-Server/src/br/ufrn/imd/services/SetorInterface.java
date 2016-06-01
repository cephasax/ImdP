package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.Local;

import br.ufrn.imd.dominio.Setor;

@Local
public interface SetorInterface {

	public abstract void save(Setor setor);

	public abstract Setor update(Setor setor);
	
	public abstract void delete(Setor setor);

	public abstract Setor find(int entityID);

	public abstract List<Setor> findAll();
	
}
