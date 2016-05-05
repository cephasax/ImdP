package br.ufrn.imd.conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {
	
	private static Conexao singleton = new Conexao();
	private static EntityManager em;
	
	private Conexao() {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("ConexaoDB");
		em = entityManagerFactory.createEntityManager();
	}

	public static Conexao getInstance() {
		return singleton;
	}

	public EntityManager getEntityManager() {
		return em;
	}
}

