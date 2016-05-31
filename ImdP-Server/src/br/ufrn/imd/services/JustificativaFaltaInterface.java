package br.ufrn.imd.services;

import java.util.List;

import br.ufrn.imd.dominio.JustificativaFalta;

public interface JustificativaFaltaInterface {
	
	public abstract void save(JustificativaFalta justificativaFalta);

	public abstract JustificativaFalta update(JustificativaFalta justificativaFalta);
	
	public abstract void delete(JustificativaFalta justificativaFalta);

	public abstract JustificativaFalta find(int entityID);

	public abstract List<JustificativaFalta> findAll();
}
