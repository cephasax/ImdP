package br.ufrn.imd.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericDao<T> {
	private final static String UNIT_NAME = "ImdP";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	private Class<T> entityClass;

	public GenericDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void save(T entity) {
		em.persist(entity);
	}

	protected void delete(Object id, Class<T> classe) {
		T entityToBeRemoved = em.getReference(classe, id);
		em.remove(entityToBeRemoved);
	}

	public T update(T entity) {
		return em.merge(entity);
	}

	public T find(int entityID) {
		return em.find(entityClass, entityID);
	}
}

