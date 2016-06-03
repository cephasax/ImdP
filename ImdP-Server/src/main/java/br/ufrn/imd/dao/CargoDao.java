package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Cargo;

@Stateless
public class CargoDao extends GenericDao {

	public CargoDao(){
		super(Cargo.class);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Cargo> buscarCargoFiltro(String nomeCargo) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select c FROM Cargo c";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (!nomeCargo.equals("")){
			where.append(" and lower(c.nome) like lower(:nome) ");
		}
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		if (!nomeCargo.equals("")){
			query.setParameter("nome", "%"+nomeCargo+"%");
		}
		
		//EXECUCAO E RETORNO
		return (ArrayList<Cargo>)query.getResultList();
	}

	public ArrayList<Cargo> listar() {
		Query query = em.createQuery("Select c from Cargo c");
		List<Cargo> results = new ArrayList<Cargo>();
		results = query.getResultList();
		return (ArrayList<Cargo>) results;
	}
	
	public Cargo buscarPorId(int idCargo) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select c FROM Cargo c";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (idCargo > 0) {
			where.append(" and c.idCargo = :idCargo");
			
			StringBuilder sqlFinal = new StringBuilder();
			sqlFinal.append(sql);
			sqlFinal.append(where.toString());	
			Query query = em.createQuery(sqlFinal.toString());
			
			//DEFINICAO DOS PARAMETROS DA CONSULTA
			query.setParameter("idCargo", idCargo);
			
			//EXECUCAO E RETORNO
			return (Cargo)query.getSingleResult();
		}
		else{
			return null;
		}	
	}
	
	public void delete(Cargo cargo) {
		super.delete(cargo.getIdCargo(), Cargo.class);
	}

}
