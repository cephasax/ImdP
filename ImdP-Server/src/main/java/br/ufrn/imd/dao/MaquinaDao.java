package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Maquina;

@Stateless
public class MaquinaDao extends GenericDao {
	
	public MaquinaDao(){
		super(Maquina.class);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Maquina> buscarMaquinaFiltro(String nomeMaquina, int idUnidade) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select m FROM Maquina m"
				+ " JOIN m.unidade unidade";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (idUnidade > 0) {
			where.append(" and unidade.idUnidade = :idUnidade");
		}
		if (!nomeMaquina.equals("")){
			where.append(" and lower(m.denominacao) like lower(:nomeMaquina) ");
		}
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = em.createQuery(sqlFinal.toString());
		
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
		Query query = em.createQuery("Select m FROM Maquina m");
		List<Maquina> results = new ArrayList<Maquina>();
		results = query.getResultList();
		return (ArrayList<Maquina>) results;
	}
	
	public Maquina buscarPorId(int idMaquina) {
		String sql = " Select m FROM Maquina m";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE m.idMaquina = :idMaquina");
		
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		query.setParameter("idMaquina", idMaquina);
		
		//EXECUCAO E RETORNO
		Maquina result = (Maquina)query.getSingleResult();
		return result;

	}
	
	public ArrayList<Maquina> buscarMaquinaCheck(Maquina maq) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select m FROM Maquina m";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		where.append(" and m.ip = :ip");
		where.append(" and lower(m.denominacao) = lower(:denominacao)");

		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		query.setParameter("ip", maq.getIp());
		query.setParameter("denominacao", maq.getDenominacao());
		
		//EXECUCAO E RETORNO
		return (ArrayList<Maquina>)query.getResultList();
	}
	
	public void delete(Maquina maquina) {
		super.delete(maquina.getIdMaquina(), Maquina.class);
	}
	
}
