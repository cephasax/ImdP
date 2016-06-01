package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ufrn.imd.dominio.MesTrabalho;
import br.ufrn.imd.dominio.Vinculo;

@Stateless
public class MesTrabalhoDao extends GenericDao {
	
	public MesTrabalhoDao(){
		super(MesTrabalho.class);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<MesTrabalho> buscarMesTrabalhoFiltro(int mes, int ano) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select mt FROM MesTrabalho mt";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (mes > 0) {
			where.append(" and mt.mes = :mes");
		}
		if (ano > 0) {
			where.append(" and mt.ano = :ano");
		}
		
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		if (mes > 0) {
			query.setParameter("mes", mes);
		}
		if (ano > 0) {
			query.setParameter("ano", ano);
		}
		
		//EXECUCAO E RETORNO
		return (ArrayList<MesTrabalho>)query.getResultList();
	}

	public ArrayList<MesTrabalho> listar() {
		Query a = em.createQuery(" Select mt FROM MesTrabalho mt");
		List<MesTrabalho> results = new ArrayList<MesTrabalho>();
		results = a.getResultList();
		return (ArrayList<MesTrabalho>) results;
	}
	
	public MesTrabalho buscarPorId(int idMesTrabalho) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select mt FROM MesTrabalho mt";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (idMesTrabalho > 0) {
			where.append(" and v.idMesTrabalho = :idMesTrabalho");
		
			StringBuilder sqlFinal = new StringBuilder();
			sqlFinal.append(sql);
			sqlFinal.append(where.toString());	
			Query query = em.createQuery(sqlFinal.toString());
			
			//DEFINICAO DOS PARAMETROS DA CONSULTA
			query.setParameter("idMesTrabalho", idMesTrabalho);		
			
			//EXECUCAO E RETORNO
			return (MesTrabalho)query.getSingleResult();
		}
		else{
			return null;
		}
	}
	
	public void delete(MesTrabalho mesTrabalho) {
		super.delete(mesTrabalho.getIdMesTrabalho(), MesTrabalho.class);
	}
}
