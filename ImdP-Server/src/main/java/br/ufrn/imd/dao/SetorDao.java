package br.ufrn.imd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Setor;

public class SetorDao extends GenericDao {
	EntityManager em = getEntityManager();

	public Setor findByName(String nome) {
		Query a = em.createQuery("from Setor s where s.nome = " + nome);
		List<Setor> resultsA = a.getResultList();
		for (Setor elemento : resultsA) {
			System.out.println("Setor: " + elemento.getNome());
			return elemento;
		}
		return null;
	}
	
	public List<Setor> listAll() {
		Query a = em.createQuery("from Setor s");
		List<Setor> resultsA = a.getResultList();
		for (Setor elemento : resultsA) {
			System.out.println("Setor: " + elemento.getNome());
		}
		return resultsA;
	}
}
