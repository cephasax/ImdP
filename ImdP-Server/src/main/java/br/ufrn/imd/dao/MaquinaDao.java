package br.ufrn.imd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Maquina;

public class MaquinaDao extends GenericDao {
	EntityManager em = getEntityManager();

	public Maquina findByUnidade(int idUnidade) {
		Query a = em.createQuery("from Maquina m where m.unidade.idUnidade = " + idUnidade);
		List<Maquina> resultsA = a.getResultList();
		for (Maquina elemento : resultsA) {
			System.out.println("Maquina: " + elemento.getDenominacao());
			return elemento;
		}
		return null;
	}

	public Maquina findByUnidadeNome(int idUnidade, String denominacao) {
		Query a = em.createQuery(
				"from Maquina m where m.unidade.idUnidade = " + idUnidade + "and m.denominacao = " + denominacao);
		List<Maquina> resultsA = a.getResultList();
		for (Maquina elemento : resultsA) {
			System.out.println("Maquina: " + elemento.getDenominacao());
			return elemento;
		}
		return null;
	}

	public Maquina findByNome(String denominacao) {
		Query a = em.createQuery("from Maquina p where p.denominacao = " + denominacao);
		List<Maquina> resultsA = a.getResultList();
		for (Maquina elemento : resultsA) {
			System.out.println("Maquina: " + elemento.getDenominacao());
			return elemento;
		}
		return null;
	}

	public List<Maquina> listAll() {
		Query a = em.createQuery("from Maquina v");
		List<Maquina> resultsA = a.getResultList();
		for (Maquina elemento : resultsA) {
			System.out.println("Maquina: " + elemento.getDenominacao());
		}
		return resultsA;
	}
}
