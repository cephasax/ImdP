package br.ufrn.imd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Unidade;

public class UnidadeDao extends GenericDao {
	EntityManager em = getEntityManager();

	public Unidade findByName(String nome) {
		Query a = em.createQuery("from Unidade u where u.nome = " + nome);
		List<Unidade> resultsA = a.getResultList();
		for (Unidade elemento : resultsA) {
			System.out.println("Unidade: " + elemento.getNome());
			return elemento;
		}
		return null;
	}

	public List<Unidade> listAll() {
		Query a = em.createQuery("from Unidade u");
		List<Unidade> resultsA = a.getResultList();
		for (Unidade elemento : resultsA) {
			System.out.println("Unidade: " + elemento.getNome());
		}
		return resultsA;
	}
}
