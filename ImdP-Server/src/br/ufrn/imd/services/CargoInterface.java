package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.Local;

import br.ufrn.imd.dominio.Cargo;

@Local
public interface CargoInterface {

	public abstract void save(Cargo cargo);

	public abstract Cargo update(Cargo cargo);
	
	public abstract void delete(Cargo cargo);

	public abstract Cargo find(int entityID);

	public abstract List<Cargo> findAll();
	
}
