package br.ufrn.imd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ufrn.imd.dominio.JustificativaFalta;

public class JustificativaFaltaDao extends GenericDao {
	EntityManager em = getEntityManager();

	public JustificativaFalta findByUnidade(int idUnidade) {
		Query a = em.createQuery("from JustificativaFalta j where j.unidade.idUnidade = " + idUnidade);
		List<JustificativaFalta> resultsA = a.getResultList();
		for (JustificativaFalta elemento : resultsA) {
			System.out.println("JustificativaFalta: " + elemento.getIdJustificativaFalta());
			return elemento;
		}
		return null;
	}

	public JustificativaFalta findByUnidadeSetor(int idUnidade, int idSetor) {
		Query a = em.createQuery(
				"from JustificativaFalta j where j.unidade.idUnidade = " + idUnidade + "and j.setor.idsetor = " + idSetor);
		List<JustificativaFalta> resultsA = a.getResultList();
		for (JustificativaFalta elemento : resultsA) {
			System.out.println("JustificativaFalta: " + elemento.getIdJustificativaFalta());
			return elemento;
		}
		return null;
	}

	public JustificativaFalta findBySetor(int idSetor) {
		Query a = em.createQuery("from JustificativaFalta j where j.setor.idsetor = " + idSetor);
		List<JustificativaFalta> resultsA = a.getResultList();
		for (JustificativaFalta elemento : resultsA) {
			System.out.println("JustificativaFalta: " + elemento.getIdJustificativaFalta());
			return elemento;
		}
		return null;
	}

	public JustificativaFalta findByUnidadeSetorNome(int idUnidade, int idSetor, String nome) {
		Query a = em.createQuery("from JustificativaFalta j where j.unidade.idUnidade = " + idUnidade + " and j.setor.idsetor = "
				+ idSetor + " and j.nome = " + nome);
		List<JustificativaFalta> resultsA = a.getResultList();
		for (JustificativaFalta elemento : resultsA) {
			System.out.println("JustificativaFalta: " + elemento.getIdJustificativaFalta());
			return elemento;
		}
		return null;
	}

	public JustificativaFalta findByNome(String nome) {
		Query a = em.createQuery("from JustificativaFalta j where j.nome = " + nome);
		List<JustificativaFalta> resultsA = a.getResultList();
		for (JustificativaFalta elemento : resultsA) {
			System.out.println("JustificativaFalta: " + elemento.getIdJustificativaFalta());
			return elemento;
		}
		return null;
	}

	public List<JustificativaFalta> listAll() {
		Query a = em.createQuery("from JustificativaFalta j");
		List<JustificativaFalta> resultsA = a.getResultList();
		for (JustificativaFalta elemento : resultsA) {
			System.out.println("JustificativaFalta: " + elemento.getIdJustificativaFalta());
		}
		return resultsA;
	}
}
