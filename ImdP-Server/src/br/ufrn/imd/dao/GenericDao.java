package br.ufrn.imd.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ufrn.imd.conexao.Conexao;

public class GenericDao {
	
	public void inserir (Object entidade){
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(entidade);
		em.getTransaction().commit();
	}
	
	public void excluir(Object entidade){
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.remove(entidade);
		em.getTransaction().commit();
	}
	
	public void atualizar(Object entidade){
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.merge(entidade);
		em.getTransaction().commit();
	}
	
	protected EntityManager getEntityManager(){
		return Conexao.getInstance().getEntityManager();
	}
	
	public void inserirLog(Object log){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("IMDBio");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(log);		
			em.getTransaction().commit();
		}
		finally {
			em.close();
		}
	}	

}
