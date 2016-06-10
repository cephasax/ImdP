package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ufrn.imd.dominio.TipoJustificativa;

@Stateless
public class TipoJustificativaDao extends GenericDao {
	
	public TipoJustificativaDao(){
		super(TipoJustificativa.class);
	}
		
	@SuppressWarnings("unchecked")
	public ArrayList<TipoJustificativa> buscarTipoJustificativaFiltro
							(String nomeTipoJustificativa) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select tj FROM TipoJustificativa tj";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (!nomeTipoJustificativa.equals("")){
			where.append(" and lower(tj.nome) like lower(:nomeTipoJustificativa) ");
		}
		
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		if (!nomeTipoJustificativa.equals("")){
			query.setParameter("nomeTipoJustificativa", "%"+nomeTipoJustificativa+"%");
		}
		
		//EXECUCAO E RETORNO
		return (ArrayList<TipoJustificativa>)query.getResultList();
	}

	public ArrayList<TipoJustificativa> listar() {
		Query query = em.createQuery("Select tj from TipoJustificativa tj");
		List<TipoJustificativa> results = new ArrayList<TipoJustificativa>();
		results = query.getResultList();
		return (ArrayList<TipoJustificativa>) results;
	}
	
	public TipoJustificativa buscarPorId(int idTipoJustificativa) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select tj FROM TipoJustificativa tj";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE tj.idTipoJustificativa = :idTipoJustificativa");
			
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		query.setParameter("idTipoJustificativa", idTipoJustificativa);

		//EXECUCAO E RETORNO
		return (TipoJustificativa)query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<TipoJustificativa> buscarTipoJustificativaCheck (TipoJustificativa tipoJust) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select tj FROM TipoJustificativa tj";
		StringBuilder where = new StringBuilder();
		
		where.append(" WHERE 1 = 1 ");
		where.append(" and lower(tj.nome) = lower(:nomeTipoJustificativa) ");
	
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
			query.setParameter("nomeTipoJustificativa", tipoJust.getNome());
		
		//EXECUCAO E RETORNO
		return (ArrayList<TipoJustificativa>)query.getResultList();
	}
	
	public void delete(TipoJustificativa tipoJustificativa) {
		super.delete(tipoJustificativa.getIdTipoJustificativa(), TipoJustificativa.class);
	}
}
