package br.ufrn.imd.dao;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ufrn.imd.dominio.ImpressaoDigital;

@Stateless
public class ImpressaoDigitalDao extends GenericDao {

	public ImpressaoDigitalDao(){
		super(ImpressaoDigital.class);
	}
	
	public ImpressaoDigital buscarPorId(int idImpressaoDigital) {
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select i FROM ImpressaoDigital i";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE i.idImpressaoDigital = :idImpressaoDigital");
			
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = em.createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		query.setParameter("idImpressaoDigital", idImpressaoDigital);
		
		//EXECUCAO E RETORNO
		return (ImpressaoDigital)query.getSingleResult();
	}
	
	public void delete(ImpressaoDigital cargo) {
		super.delete(cargo.getIdImpressaoDigital(), ImpressaoDigital.class);
	}

	public ArrayList<ImpressaoDigital> buscarImpressaoDigitalCheck(String nomeDedo, int idUsuario){
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select i FROM ImpressaoDigital i"
				+ " JOIN i.usuario usuario";
		
		StringBuilder where = new StringBuilder();
		where.append(" WHERE i.nomeDedo = :nomeDedo ");
		where.append(" and i.usuario.idUsuario = :idUsuario ");
		
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		
		Query query = em.createQuery(sqlFinal.toString());
		query.setParameter("nomeDedo", nomeDedo);		
		query.setParameter("idUsuario", idUsuario);

		//EXECUCAO E RETORNO
		return (ArrayList<ImpressaoDigital>)query.getResultList();
		 
	}
	
	public ArrayList<ImpressaoDigital> buscarImpressaoDigitalUsuario(int idUsuario){
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = " Select i FROM ImpressaoDigital i"
				+ " JOIN i.usuario usuario";
		
		StringBuilder where = new StringBuilder();
		where.append(" WHERE i.usuario.idUsuario = :idUsuario ");
		
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		
		Query query = em.createQuery(sqlFinal.toString());		
		query.setParameter("idUsuario", idUsuario);

		//EXECUCAO E RETORNO
		return (ArrayList<ImpressaoDigital>)query.getResultList();
		 
	}
	
}

