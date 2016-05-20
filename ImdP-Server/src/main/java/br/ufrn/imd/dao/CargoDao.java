package br.ufrn.imd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Cargo;

public class CargoDao extends GenericDao {

	EntityManager em = getEntityManager();

	public Cargo findByName(String nome) {
		Query a = em.createQuery("from Cargo c where c.nome = " + nome);
		List<Cargo> resultsA = a.getResultList();
		for (Cargo elemento : resultsA) {
			System.out.println("Cargo: " + elemento.getNome());
			return elemento;
		}
		return null;
	}

	public List<Cargo> listAll() {
		Query a = em.createQuery("from Cargo c");
		List<Cargo> resultsA = a.getResultList();
		for (Cargo elemento : resultsA) {
			System.out.println("Cargo: " + elemento.getNome());
		}
		return resultsA;
	}

}
