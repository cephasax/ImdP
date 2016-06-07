package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.ufrn.imd.dominio.TipoJustificativa;

public class TipoJustificativaDao extends GenericDao {
	
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

}
