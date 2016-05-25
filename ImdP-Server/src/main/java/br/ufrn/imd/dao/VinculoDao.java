package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.ufrn.imd.dominio.Vinculo;

public class VinculoDao extends GenericDao {

	@SuppressWarnings("unchecked")
	public ArrayList<Vinculo> buscarVinculoFiltro(String nomeUsuario, int idUnidade, int idSetor) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select v FROM Vinculo v"
				+ " JOIN v.usuario usuario"
				+ " JOIN v.setor setor"
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
			where.append(" and lower(v.usuario.nome) like lower(:nome) ");
		}
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = getEntityManager().createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		if (idUnidade > 0) {
			query.setParameter("idUnidade", idUnidade);
		}
		if (idSetor > 0) {
			query.setParameter("idSetor", idSetor);
		}
		if (!nomeUsuario.equals("")){
			query.setParameter("nome", "%"+nomeUsuario+"%");
		}
		
		//EXECUCAO E RETORNO
		return (ArrayList<Vinculo>)query.getResultList();
	}

	public ArrayList<Vinculo> listar() {
		Query a = getEntityManager().createQuery("Select v from Vinculo v");
		List<Vinculo> results = new ArrayList<Vinculo>();
		results = a.getResultList();
		return (ArrayList<Vinculo>) results;
	}
	
	public Vinculo buscarPorId(int idVinculo) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select v FROM Vinculo v";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (idVinculo > 0) {
			where.append(" and v.idVinculo = :idVinculo");
			
			StringBuilder sqlFinal = new StringBuilder();
			sqlFinal.append(sql);
			sqlFinal.append(where.toString());	
			Query query = getEntityManager().createQuery(sqlFinal.toString());
			
			//DEFINICAO DOS PARAMETROS DA CONSULTA
			query.setParameter("idVinculo", idVinculo);

			//EXECUCAO E RETORNO
			return (Vinculo)query.getSingleResult();
		}
		else{
			return null;
		}
	}
}
