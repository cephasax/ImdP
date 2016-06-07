package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDao {
	
	public EntityManager em;
	
	public void inserir (Object entidade){
		em = getEntityManager();
		em.getTransaction().begin();
		em.persist(entidade);
		em.getTransaction().commit();
	}
	
	public void excluir(Object entidade){
		em = getEntityManager();
		em.getTransaction().begin();
		em.remove(entidade);
		em.getTransaction().commit();
	}
	
	public void atualizar(Object entidade){
		em = getEntityManager();
		em.getTransaction().begin();
		em.merge(entidade);
		em.getTransaction().commit();
	}
	
	public Object findById(Class classe , long id){
		em = getEntityManager();
		return em.find(classe, id);
	}

	private EntityManager getEntityManager(){
		return Banco.getInstance().getEntityManager();
	}

}
