package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Unidade;

@Stateless
public class UnidadeDao extends GenericDao {
	
	public UnidadeDao(){
		super(Unidade.class);
	}
	
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
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		if (!nomeUnidade.equals("")){
			query.setParameter("nomeUnidade", "%"+nomeUnidade+"%");
		}
		
		//EXECUCAO E RETORNO
		return (ArrayList<Unidade>)query.getResultList();
	}

	public ArrayList<Unidade> listar() {
		Query query = em.createQuery("Select u from Unidade u");
		List<Unidade> results = new ArrayList<Unidade>();
		results = query.getResultList();
		return (ArrayList<Unidade>) results;
	}
	
	public Unidade buscarPorId(int idUnidade) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select u FROM Unidade u";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (idUnidade > 0) {
			where.append(" and u.idUnidade = :idUnidade");
		
			StringBuilder sqlFinal = new StringBuilder();
			sqlFinal.append(sql);
			sqlFinal.append(where.toString());	
			Query query = em.createQuery(sqlFinal.toString());
			
			//DEFINICAO DOS PARAMETROS DA CONSULTA
				query.setParameter("idUnidade", idUnidade);
			
			//EXECUCAO E RETORNO
			return (Unidade)query.getSingleResult();
		}
		else{
			return null;
		}
	}

	public void delete(Unidade unidade) {
		super.delete(unidade.getIdUnidade(), Unidade.class);
	}
}
