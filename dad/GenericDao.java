package br.ufrn.imd.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDao {

	public void inserir(Object entidade) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(entidade);
		em.getTransaction().commit();
	}

	public void excluir(Object entidade) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.remove(entidade);
		em.getTransaction().commit();
	}

	public void atualizar(Object entidade) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.merge(entidade);
		em.getTransaction().commit();
	}

	public Object buscarPorId(Class classe, long id) {
		EntityManager em = getEntityManager();
		return em.find(classe, id);
	}

	public EntityManager getEntityManager() {
		return Banco.getInstance().getEntityManager();
	}

	public void inserirLog(Object log) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("IMDBio");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(log);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

}
