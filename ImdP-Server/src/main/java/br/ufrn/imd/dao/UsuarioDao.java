package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Usuario;

@Stateless
public class UsuarioDao extends GenericDao {

	public UsuarioDao(){
		super(Usuario.class);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Usuario> buscarUsuarioFiltro(String nomeUsuario, String cpf) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = "SELECT u FROM Usuario u";
				
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (!cpf.equals("")){
			where.append(" and u.cpf like :cpf");
		}
		
		if (!nomeUsuario.equals("")){
			where.append(" and lower(u.nome) like lower(:nomeUsuario) ");
		}
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		if (!cpf.equals("")){
			query.setParameter("cpf", "%"+cpf+"%");
		}
		if (!nomeUsuario.equals("")){
			query.setParameter("nomeUsuario", "%"+nomeUsuario+"%");
		}
		
		//EXECUCAO E RETORNO
		return (ArrayList<Usuario>)query.getResultList();
	}

	public ArrayList<Usuario> listar() {
		Query query = em.createQuery("Select u from Usuario u");
		List<Usuario> results = new ArrayList<Usuario>();
		results = query.getResultList();
		return (ArrayList<Usuario>) results;
	}
	
	public Usuario buscarPorId(int idUsuario) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select u FROM Usuario u";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE u.idUsuario = :idUsuario");
		
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		query.setParameter("idUsuario", idUsuario);
		
		//EXECUCAO E RETORNO
		return (Usuario)query.getSingleResult();
	}

	public Usuario buscarPorCpf(String cpf) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select u FROM Usuario u";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE u.cpf = :cpf");
		
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		query.setParameter("cpf", cpf);
		
		//EXECUCAO E RETORNO
		return (Usuario)query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Usuario> buscarUsuarioCheck(Usuario usuario) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select u FROM Usuario u";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE u.cpf = :cpf");
		
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		query.setParameter("cpf", usuario.getCpf());
		
		//EXECUCAO E RETORNO
		return (ArrayList<Usuario>) query.getResultList();
	}
	
	public void delete(Usuario usuario) {
		super.delete(usuario.getIdUsuario(), Usuario.class);
	}
}
