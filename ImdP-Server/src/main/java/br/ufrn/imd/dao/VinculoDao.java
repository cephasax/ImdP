package br.ufrn.imd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Vinculo;

public class VinculoDao extends GenericDao {

	EntityManager em = getEntityManager();

	public Vinculo findByUnidade(int idUnidade) {
		Query a = em.createQuery("from Vinculo v where v.unidade.idUnidade = " + idUnidade);
		List<Vinculo> resultsA = a.getResultList();
		for (Vinculo elemento : resultsA) {
			System.out.println("Vinculo: " + elemento.getDescricao());
			return elemento;
		}
		return null;
	}

	public Vinculo findByUnidadeSetor(int idUnidade, int idSetor) {
		Query a = em.createQuery(
				"from Vinculo v where v.unidade.idUnidade = " + idUnidade + "and v.setor.idsetor = " + idSetor);
		List<Vinculo> resultsA = a.getResultList();
		for (Vinculo elemento : resultsA) {
			System.out.println("Vinculo: " + elemento.getDescricao());
			return elemento;
		}
		return null;
	}

	public Vinculo findBySetor(int idSetor) {
		Query a = em.createQuery("from Vinculo v where v.setor.idsetor = " + idSetor);
		List<Vinculo> resultsA = a.getResultList();
		for (Vinculo elemento : resultsA) {
			System.out.println("Vinculo: " + elemento.getDescricao());
			return elemento;
		}
		return null;
	}

	public Vinculo findByUnidadeSetorNome(int idUnidade, int idSetor, String nome) {
		Query a = em.createQuery("from Vinculo v where v.unidade.idUnidade = " + idUnidade + " and v.setor.idsetor = "
				+ idSetor + " and v.nome = " + nome);
		List<Vinculo> resultsA = a.getResultList();
		for (Vinculo elemento : resultsA) {
			System.out.println("Vinculo: " + elemento.getDescricao());
			return elemento;
		}
		return null;
	}

	public Vinculo findByNome(String nome) {
		Query a = em.createQuery("from Vinculo v where v.nome = " + nome);
		List<Vinculo> resultsA = a.getResultList();
		for (Vinculo elemento : resultsA) {
			System.out.println("Vinculo: " + elemento.getDescricao());
			return elemento;
		}
		return null;
	}

	public List<Vinculo> listAll() {
		Query a = em.createQuery("from Vinculo v");
		List<Vinculo> resultsA = a.getResultList();
		for (Vinculo elemento : resultsA) {
			System.out.println("Vinculo: " + elemento.getDescricao());
		}
		return resultsA;
	}
}
