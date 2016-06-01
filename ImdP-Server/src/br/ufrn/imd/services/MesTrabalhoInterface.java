package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.Local;

import br.ufrn.imd.dominio.MesTrabalho;

@Local
public interface MesTrabalhoInterface {

	public abstract void save(MesTrabalho mesTrabalho);

	public abstract MesTrabalho update(MesTrabalho mesTrabalho);
	
	public abstract void delete(MesTrabalho mesTrabalho);

	public abstract MesTrabalho find(int entityID);

	public abstract List<MesTrabalho> findAll();
	
}
