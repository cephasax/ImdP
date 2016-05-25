package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.ufrn.imd.dominio.Maquina;

public class MaquinaDao extends GenericDao {
	
	@SuppressWarnings("unchecked")
	public ArrayList<Maquina> buscarMaquinaFiltro(String nomeMaquina, int idUnidade) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select m FROM Maquina m"
				+ " JOIN m.unidade unidade";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (idUnidade > 0) {
			where.append(" unidade.idUnidade = :idUnidade");
		}
		if (!nomeMaquina.equals("")){
			where.append(" and lower(m.nome) like lower(:nomeMaquina) ");
		}
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = getEntityManager().createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		if (idUnidade > 0) {
			query.setParameter("idUnidade", idUnidade);
		}
		if (!nomeMaquina.equals("")){
			query.setParameter("nomeMaquina", "%"+nomeMaquina+"%");
		}
		
		//EXECUCAO E RETORNO
		return (ArrayList<Maquina>)query.getResultList();
	}

	public ArrayList<Maquina> listar() {
		Query a = getEntityManager().createQuery("Select m FROM Maquina m");
		List<Maquina> results = new ArrayList<Maquina>();
		results = a.getResultList();
		return (ArrayList<Maquina>) results;
	}
	
	public Maquina buscarPorId(int idMaquina) {
		String sql = " Select m FROM Maquina m";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (idMaquina > 0) {
			where.append(" and m.idMaquina = :idMaquina");
		}
		
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());
		Query query = getEntityManager().createQuery(sqlFinal.toString());
		
		if (idMaquina > 0) {
			query.setParameter("idMaquina", idMaquina);
		}
		
		Maquina result = new Maquina();
		result = (Maquina)query.getSingleResult();
		return result;
	}
	
}
