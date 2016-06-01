package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.Local;

import br.ufrn.imd.dominio.Usuario;

@Local
public interface UsuarioInterface {

	public abstract void save(Usuario usuario);

	public abstract Usuario update(Usuario usuario);
	
	public abstract void delete(Usuario usuario);

	public abstract Usuario find(int entityID);

	public abstract List<Usuario> findAll();
	
}
