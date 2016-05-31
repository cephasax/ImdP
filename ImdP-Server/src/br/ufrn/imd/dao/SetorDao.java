package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.Vinculo;

@Stateless
public class SetorDao extends GenericDao {
	
	public SetorDao(){
		super(Setor.class);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Setor> buscarSetorFiltro(String nomeSetor, int idUnidade) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select s FROM Setor s"
				+ " JOIN s.unidade unidade";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (idUnidade > 0) {
			where.append(" and s.unidade.idUnidade = :idUnidade");
		}
		if (!nomeSetor.equals("")){
			where.append(" and lower(s.nomeSetor) like lower(:nomeSetor) ");
		}
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		if (idUnidade > 0) {
			query.setParameter("idUnidade", idUnidade);
		}
		if (!nomeSetor.equals("")){
			query.setParameter("nomeSetor", "%"+nomeSetor+"%");
		}
		
		//EXECUCAO E RETORNO
		return (ArrayList<Setor>)query.getResultList();
	}

	public ArrayList<Setor> listar() {
		Query query = em.createQuery("Select s from Setor s");
		List<Setor> results = new ArrayList<Setor>();
		results = query.getResultList();
		return (ArrayList<Setor>) results;
	}
	
	public Setor buscarPorId(int idSetor) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select s FROM Setor s";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (idSetor > 0) {
			where.append(" and s.idSetor = :idSetor");
		
			StringBuilder sqlFinal = new StringBuilder();
			sqlFinal.append(sql);
			sqlFinal.append(where.toString());	
			Query query = em.createQuery(sqlFinal.toString());
			
			//DEFINICAO DOS PARAMETROS DA CONSULTA
			query.setParameter("idSetor", idSetor);
			
			//EXECUCAO E RETORNO
			return (Setor)query.getSingleResult();
		}
		else{
			return null;
		}
	}

	public void delete(Setor setor) {
		super.delete(setor.getIdSetor(), Setor.class);
	}
}
