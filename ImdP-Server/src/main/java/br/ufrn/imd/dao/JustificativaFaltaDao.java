package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ufrn.imd.dominio.JustificativaFalta;

@Stateless
public class JustificativaFaltaDao extends GenericDao {
	
	public JustificativaFaltaDao(){
		super(JustificativaFalta.class);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<JustificativaFalta> buscarJustificativaFiltro(
			String nomeUsuario, int idUnidade, int idSetor) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select j FROM JustificativaFalta j"
				+ " JOIN j.vinculo vinculo"
				+ " JOIN vinculo.usuario usuario"
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
			where.append(" and lower(vinculo.usuario.nome) like lower(:nome) ");
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
			query.setParameter("nome", "%"+nomeUsuario+"%");
		}
		
		//EXECUCAO E RETORNO
		return (ArrayList<JustificativaFalta>)query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<JustificativaFalta> listar() {
		Query query = em.createQuery("Select j from JustificativaFalta j");
		List<JustificativaFalta> results = new ArrayList<JustificativaFalta>();
		results = query.getResultList();
		return (ArrayList<JustificativaFalta>) results;
	}
	
	public JustificativaFalta buscarPorId(int idJustificativa) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select j FROM JustificativaFalta j";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (idJustificativa > 0) {
			where.append(" and j.idJustificativa = :idJustificativa");
			
			StringBuilder sqlFinal = new StringBuilder();
			sqlFinal.append(sql);
			sqlFinal.append(where.toString());	
			Query query = em.createQuery(sqlFinal.toString());
			
			//DEFINICAO DOS PARAMETROS DA CONSULTA
			query.setParameter("idJustificativa", idJustificativa);
			
			//EXECUCAO E RETORNO
			return (JustificativaFalta)query.getSingleResult();
		}
		else{
			return null;
		}
	}
	
	public void delete(JustificativaFalta justificativaFalta) {
		super.delete(justificativaFalta.getIdJustificativaFalta(), JustificativaFalta.class);
	}
}
