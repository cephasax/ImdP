package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.Local;

import br.ufrn.imd.dominio.TipoJustificativa;

@Local
public interface TipoJustificativaInterface {

	public abstract void save(TipoJustificativa tipoJustificativa);

	public abstract TipoJustificativa update(TipoJustificativa tipoJustificativa);
	
	public abstract void delete(TipoJustificativa tipoJustificativa);

	public abstract TipoJustificativa find(int entityID);

	public abstract List<TipoJustificativa> findAll();
	
}
