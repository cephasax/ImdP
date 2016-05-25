package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Ponto;
import br.ufrn.imd.dominio.Vinculo;

public class PontoDao extends GenericDao {

	@SuppressWarnings("unchecked")
	public ArrayList<Ponto> buscarPontoFiltro(String nomeUsuario, int idUnidade, int idSetor) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select p FROM Ponto p"
				+ " JOIN p.maquina maquina"
				+ " JOIN p.vinculo vinculo"
				+ " JOIN vinculo.usuario usuario"
				+ " JOIN vinculo.setor setor"
				+ " JOIN setor.unidade unidade";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (idUnidade > 0) {
			where.append(" and unidade.idUnidade = :idUnidade");
		}
		if (idSetor > 0) {
			where.append(" and setor.idSetor = :idSetor");
		}
		if (!nomeUsuario.equals("")){
			where.append(" and lower(p.vinculo.usuario.nome) like lower(:nome) ");
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
		return (ArrayList<Ponto>)query.getResultList();
	}

	public ArrayList<Ponto> listar() {
		Query a = getEntityManager().createQuery("Select p from Ponto p");
		List<Ponto> results = new ArrayList<Ponto>();
		results = a.getResultList();
		return (ArrayList<Ponto>) results;
	}
	
	public Ponto buscarPorId(int idPonto) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = "Select p from Ponto p";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1");
		
		if (idPonto > 0) {
			where.append(" and p.idPonto = :idPonto");
		}
		
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = getEntityManager().createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		if (idPonto > 0) {
			query.setParameter("idPonto", idPonto);
		}
				
		//EXECUCAO E RETORNO
		return (Ponto)query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Ponto> listarPontosUsuario(int idUsuario) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select p FROM Ponto p"
				+ " JOIN p.maquina maquina"
				+ " JOIN p.vinculo vinculo"
				+ " JOIN vinculo.usuario usuario"
				+ " JOIN vinculo.setor setor"
				+ " JOIN setor.unidade unidade";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (idUsuario > 0) {
			where.append(" and p.vinculo.usuario.idUsuario = :idUsuario");
		}
		
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = getEntityManager().createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		if (idUsuario > 0) {
			query.setParameter("idUsuario", idUsuario);
		}
		
		//EXECUCAO E RETORNO
		return (ArrayList<Ponto>)query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Ponto> listarPontosVinculo(int idVinculo) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select p FROM Ponto p"
				+ " JOIN p.maquina maquina"
				+ " JOIN p.vinculo vinculo"
				+ " JOIN vinculo.usuario usuario"
				+ " JOIN vinculo.setor setor"
				+ " JOIN setor.unidade unidade";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (idVinculo > 0) {
			where.append(" and p.vinculo.idVinculo = :idVinculo");
		}
		
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = getEntityManager().createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		if (idVinculo > 0) {
			query.setParameter("idVinculo", idVinculo);
		}
		
		//EXECUCAO E RETORNO
		return (ArrayList<Ponto>)query.getResultList();
	}
}
