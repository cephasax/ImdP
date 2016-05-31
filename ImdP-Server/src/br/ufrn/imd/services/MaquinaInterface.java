package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.Local;

import br.ufrn.imd.dominio.Maquina;

@Local
public interface MaquinaInterface {

	public abstract void save(Maquina maquina);

	public abstract Maquina update(Maquina maquina);
	
	public abstract void delete(Maquina maquina);

	public abstract Maquina find(int entityID);

	public abstract List<Maquina> findAll();
	
}
