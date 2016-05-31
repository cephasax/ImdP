package br.ufrn.imd.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.dao.UsuarioDao;
import br.ufrn.imd.dominio.Usuario;

@Stateless
public class UsuarioInterfaceImp implements UsuarioInterface{

	
	@EJB
	private UsuarioDao usuarioDao;
	
	@Override
	public void save(Usuario usuario) {
		verificarUsuario(usuario);
		usuarioDao.save(usuario);
	}

	@Override
	public Usuario update(Usuario usuario) {
		verificarUsuario(usuario);
		return (Usuario) usuarioDao.update(usuario);
	}

	@Override
	public void delete(Usuario usuario) {
		usuarioDao.delete(usuario);
	}

	@Override
	public Usuario find(int entityID) {
		return (Usuario) usuarioDao.find(entityID);
	}

	@Override
	public List<Usuario> findAll() {
		return usuarioDao.listar();
	}
	
	private void verificarUsuario(Usuario usuario){
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//NOME
		if (usuario.getNome() == null || "".equals(usuario.getNome().trim())){
			hasError = true;
		}
		
		//Data DE NASCIMENTO
		if (usuario.getDataNascimento() == null){
			hasError = true;
		}
		
		//CPF
		if (usuario.getCpf() == null || "".equals(usuario.getCpf().trim())){
			hasError = true;
		}
		
		//EMAIL
		if (usuario.getEmail() == null || "".equals(usuario.getEmail().trim())){
			hasError = true;
		}
		
		//TELEFONE 1
		if (usuario.getTelefone1() == null || "".equals(usuario.getTelefone1().trim())){
			hasError = true;
		}
		
		//LOGIN
		if (usuario.getLogin() == null || "".equals(usuario.getLogin().trim())){
			hasError = true;
		}
		
		//SENHA
		if (usuario.getSenha() == null || "".equals(usuario.getSenha().trim())){
			hasError = true;
		}
		
		if (hasError){
			throw new IllegalArgumentException("O usuario nao possui todos os dados.");
		}
	}
	
}
