package br.ufrn.imd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Ponto;

public class PontoDao extends GenericDao {

	EntityManager em = getEntityManager();

	public Ponto findByUnidade(int idUnidade) {
		Query a = em.createQuery("from Ponto p where p.unidade.idUnidade = " + idUnidade);
		List<Ponto> resultsA = a.getResultList();
		for (Ponto elemento : resultsA) {
			System.out.println("Ponto: " + elemento.getIdPonto());
			return elemento;
		}
		return null;
	}

	public Ponto findByUnidadeSetor(int idUnidade, int idSetor) {
		Query a = em.createQuery(
				"from Ponto p where p.unidade.idUnidade = " + idUnidade + "and p.setor.idsetor = " + idSetor);
		List<Ponto> resultsA = a.getResultList();
		for (Ponto elemento : resultsA) {
			System.out.println("Ponto: " + elemento.getIdPonto());
			return elemento;
		}
		return null;
	}

	public Ponto findBySetor(int idSetor) {
		Query a = em.createQuery("from Ponto p where p.setor.idsetor = " + idSetor);
		List<Ponto> resultsA = a.getResultList();
		for (Ponto elemento : resultsA) {
			System.out.println("Ponto: " + elemento.getIdPonto());
			return elemento;
		}
		return null;
	}

	public Ponto findByUnidadeSetorNome(int idUnidade, int idSetor, String nome) {
		Query a = em.createQuery("from Ponto p where p.unidade.idUnidade = " + idUnidade + " and p.setor.idsetor = "
				+ idSetor + " and p.nome = " + nome);
		List<Ponto> resultsA = a.getResultList();
		for (Ponto elemento : resultsA) {
			System.out.println("Ponto: " + elemento.getIdPonto());
			return elemento;
		}
		return null;
	}

	public Ponto findByNome(String nome) {
		Query a = em.createQuery("from Ponto p where p.nome = " + nome);
		List<Ponto> resultsA = a.getResultList();
		for (Ponto elemento : resultsA) {
			System.out.println("Ponto: " + elemento.getIdPonto());
			return elemento;
		}
		return null;
	}

	public List<Ponto> listAll() {
		Query a = em.createQuery("from Ponto v");
		List<Ponto> resultsA = a.getResultList();
		for (Ponto elemento : resultsA) {
			System.out.println("Ponto: " + elemento.getIdPonto());
		}
		return resultsA;
	}
}
