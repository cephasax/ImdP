package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.Local;

import br.ufrn.imd.dominio.Ponto;

@Local
public interface PontoInterface {

	public abstract void save(Ponto ponto);

	public abstract Ponto update(Ponto ponto);
	
	public abstract void delete(Ponto ponto);

	public abstract Ponto find(int entityID);

	public abstract List<Ponto> findAll();
	
}
