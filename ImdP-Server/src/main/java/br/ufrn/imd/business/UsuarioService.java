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
		verificarUsuario(usuario);
		Usuario user = buscaCpf(usuario.getCpf());
		
		if(user == null){
			usuarioDao.save(usuario);
		}
		else{
			throw new IllegalArgumentException("Erro - save: Usuario ja existe na base de dados");
		}
	}

	public Usuario update(Usuario usuario) {
		verificarUsuario(usuario);
		return (Usuario) usuarioDao.update(usuario);
	}

	public void delete(Usuario usuario) {
		verificarUsuario(usuario);
		Usuario user = buscaCpf(usuario.getCpf());
		
		if(user == null){
			throw new IllegalArgumentException("Erro - delete: Usuario nao existe na base de dados");
		}
		else{
			usuarioDao.delete(usuario);
		}
	}

	public Usuario buscar(int entityID) {
		Usuario user = new Usuario();
		user = buscaId(entityID);
		
		if(user == null){
			throw new NoResultException("Erro - buscar: Usuario nao encontrado");
		}
		else{
			return user;
		}
	}

	public List<Usuario> listar() {
		ArrayList<Usuario> users = new ArrayList<Usuario>();
		users = usuarioDao.listar();
		if(users.size() > 0){
			return users;
		}
		else{
			throw new NoResultException("Erro - listar: nenhum usuario cadastrado");
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

	//Metodo usados apenas para verificacoes no escopo do service
	private Usuario buscaId(int id){
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
	
	private void verificarUsuario(Usuario usuario){
		boolean hasError = false;
		
		//CAMPOS OBRIGATORIOS
		//NOME
		if (usuario.getNome() == null || "".equals(usuario.getNome().trim())){
			hasError = true;
		}
		
		//DATA DE NASCIMENTO
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
