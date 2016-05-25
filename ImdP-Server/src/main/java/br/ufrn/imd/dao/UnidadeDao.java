package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.dominio.Unidade;

public class UnidadeDao extends GenericDao {
	
	@SuppressWarnings("unchecked")
	public ArrayList<Unidade> buscarUnidadeFiltro(String nomeUnidade) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select u FROM Unidade u";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (!nomeUnidade.equals("")){
			where.append(" and lower(u.nomeUnidade) like lower(:nomeUnidade) ");
		}
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = getEntityManager().createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		if (!nomeUnidade.equals("")){
			query.setParameter("nomeUnidade", "%"+nomeUnidade+"%");
		}
		
		//EXECUCAO E RETORNO
		return (ArrayList<Unidade>)query.getResultList();
	}

	public ArrayList<Unidade> listar() {
		Query a = getEntityManager().createQuery("Select u from Unidade u");
		List<Unidade> results = new ArrayList<Unidade>();
		results = a.getResultList();
		return (ArrayList<Unidade>) results;
	}
	
	public Unidade buscarPorId(int idUnidade) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select u FROM Unidade u";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (idUnidade > 0) {
			where.append(" and u.idUnidade = :idUnidade");
		}
		
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = getEntityManager().createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		if (idUnidade > 0) {
			query.setParameter("idUnidade", idUnidade);
		}
		
		//EXECUCAO E RETORNO
		return (Unidade)query.getSingleResult();
	}
}
