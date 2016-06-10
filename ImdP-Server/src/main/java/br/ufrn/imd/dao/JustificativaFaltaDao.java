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
	
	public JustificativaFalta buscarPorId(int idJustificativaFalta) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select j FROM JustificativaFalta j";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE j.idJustificativaFalta = :idJustificativaFalta");
			
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		query.setParameter("idJustificativaFalta", idJustificativaFalta);
		
		//EXECUCAO E RETORNO
		return (JustificativaFalta)query.getSingleResult();
	}
	
	//Metodo que busca no banco uma justificativa para saber se pode inserir ou ja existe
	@SuppressWarnings("unchecked")
	public ArrayList<JustificativaFalta> buscarJustificativaCheck(JustificativaFalta just) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select j FROM JustificativaFalta j"
				+ " JOIN j.vinculo vinculo"
				+ " JOIN vinculo.usuario usuario"
				+ " JOIN vinculo.setor setor"
				+ "	JOIN setor.unidade unidade";
		
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		where.append(" and unidade.idUnidade = :idUnidade");
		where.append(" and setor.idSetor = :idSetor");
		where.append(" and lower(vinculo.usuario.nome) = lower(:nome)");
		where.append(" and j.dataInicio = :dataInicio");
		where.append(" and j.dataFim = :dataFim");
		
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		query.setParameter("idUnidade", just.getVinculo().getSetor().getUnidade().getIdUnidade());
		query.setParameter("idSetor", just.getVinculo().getSetor().getIdSetor());
		query.setParameter("nome", just.getVinculo().getUsuario().getNome());
		query.setParameter("dataInicio", just.getDataInicio());
		query.setParameter("dataFim", just.getDataFim());

		//EXECUCAO E RETORNO
		return (ArrayList<JustificativaFalta>)query.getResultList();
	}
	
	public void delete(JustificativaFalta justificativaFalta) {
		super.delete(justificativaFalta.getIdJustificativaFalta(), JustificativaFalta.class);
	}
}
