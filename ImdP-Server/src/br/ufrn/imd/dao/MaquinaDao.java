package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Maquina;
import br.ufrn.imd.dominio.Vinculo;

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
			where.append(" unidade.idUnidade = :idUnidade");
		}
		if (!nomeMaquina.equals("")){
			where.append(" and lower(m.nome) like lower(:nomeMaquina) ");
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
		where.append(" WHERE 1 = 1 ");
		
		if (idMaquina > 0) {
			where.append(" and m.idMaquina = :idMaquina");
		
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
		else{
			return null;
		}
	}
	
	public void delete(Maquina maquina) {
		super.delete(maquina.getIdMaquina(), Maquina.class);
	}
	
}
