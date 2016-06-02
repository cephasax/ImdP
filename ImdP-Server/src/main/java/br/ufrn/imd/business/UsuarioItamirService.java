package br.ufrn.imd.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.app.dao.UsuarioDao;
import br.ufrn.imd.app.model.Usuario;

@Stateless
public class UsuarioItamirService {
	
	@Inject
	private UsuarioDaoItamir usuarioDaoItamir;
	
	public Usuario salvarOuAtualizar(Usuario usuario) {
		if(usuario.getLogin() != null)
			usuarioDaoItamir.salvarOuAtualizar(usuario);
		
		return usuario;
	}
	
	public List<Usuario> listagem(){
		return usuarioDaoItamir.listagem();
	}
}
