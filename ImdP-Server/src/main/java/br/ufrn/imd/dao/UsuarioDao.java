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
	public ArrayList<Usuario> buscarUsuarioFiltro(String nomeUsuario,
						int idUnidade, int idSetor) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select u FROM Usuario u"
				+ " JOIN u.vinculo vinculo"
				+ " JOIN vinculo.setor setor"
				+ "	JOIN setor.unidade unidade";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (idUnidade > 0) {
			where.append(" and unidade.idUnidade = :idUnidade");
		}
		if (idSetor > 0) {
			where.append(" and setor.idSetor = :idSetor");
		}
		if (!nomeUsuario.equals("")){
			where.append(" and lower(u.nome) like lower(:nomeUsuario) ");
		}
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		if (idUnidade > 0) {
			query.setParameter("idUnidade", idUnidade);
		}
		if (idSetor > 0) {
			query.setParameter("idSetor", idSetor);
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
		where.append(" WHERE 1 = 1 ");
		
		if (idUsuario > 0) {
			where.append(" and u.idUsuario = :idUsuario");
		
			StringBuilder sqlFinal = new StringBuilder();
			sqlFinal.append(sql);
			sqlFinal.append(where.toString());	
			Query query = em.createQuery(sqlFinal.toString());
			
			//DEFINICAO DOS PARAMETROS DA CONSULTA
			query.setParameter("idUsuario", idUsuario);
			
			//EXECUCAO E RETORNO
			return (Usuario)query.getSingleResult();
		}
		else{
			return null;
		}
	}

	public void delete(Usuario usuario) {
		super.delete(usuario.getIdUsuario(), Usuario.class);
	}
}
