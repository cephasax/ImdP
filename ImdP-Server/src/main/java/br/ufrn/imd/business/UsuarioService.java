package br.ufrn.imd.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.ufrn.imd.dao.UsuarioDao;
import br.ufrn.imd.dominio.Usuario;

@Stateless
public class UsuarioService{

	@Inject
	private UsuarioDao usuarioDao;
	
	public void save(Usuario usuario) {
		if(!verificarUsuario(usuario)){
			Usuario user = buscaCpf(usuario.getCpf());
			if(user == null){
				usuarioDao.save(usuario);
			}
			else{
				System.out.println("Usuario já existe");
			}
	}

	public Usuario update(Usuario usuario) {
		verificarUsuario(usuario);
		return (Usuario) usuarioDao.update(usuario);
	}

	public void delete(Usuario usuario) {
		usuarioDao.delete(usuario);
	}

	public Usuario buscar(int entityID) {
		return (Usuario) usuarioDao.buscarPorId(entityID);
	}

	public List<Usuario> listar() {
		return usuarioDao.listar();
	}
	
	private boolean verificarUsuario(Usuario usuario){
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
			System.out.println("O usuario nao possui todos os dados.");
			return hasError;
		}
		else{
			return hasError;
		}
	}
	
	public ArrayList<Usuario> buscarFiltro(String nomeUsuario, int idUnidade, int idSetor){
		ArrayList<Usuario> users = new ArrayList<Usuario>();
		users = usuarioDao.buscarUsuarioFiltro(nomeUsuario, idUnidade, idSetor);
		if(users.size() > 0){
			return users;
		}
		else{
			return null;
		}
	}

	public Usuario buscaId(int id){
		try{
			Usuario user = usuarioDao.buscarPorId(id);
			return user;
		}
		catch(NoResultException e){
			return null;
		}
	}
	
	public Usuario buscaCpf(String cpf){
		try{
			Usuario user = usuarioDao.buscarPorCpf(cpf);
			return user;
		}
		catch(NoResultException e){
			return null;
		}
	}
}
