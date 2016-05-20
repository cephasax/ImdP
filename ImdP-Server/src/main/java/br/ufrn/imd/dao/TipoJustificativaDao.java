package br.ufrn.imd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ufrn.imd.dominio.TipoJustificativa;

public class TipoJustificativaDao extends GenericDao {
	
	EntityManager em = getEntityManager();

	public TipoJustificativa findByName(String nome) {
		Query a = em.createQuery("from TipoJustificativa t where t.nome = " + nome);
		List<TipoJustificativa> resultsA = a.getResultList();
		for (TipoJustificativa elemento : resultsA) {
			System.out.println("TipoJustificativa: " + elemento.getNome());
			return elemento;
		}
		return null;
	}
	
	public List<TipoJustificativa> listAll() {
		Query a = em.createQuery("from TipoJustificativa t");
		List<TipoJustificativa> resultsA = a.getResultList();
		for (TipoJustificativa elemento : resultsA) {
			System.out.println("TipoJustificativa: " + elemento.getNome());
		}
		return resultsA;
	}
}
