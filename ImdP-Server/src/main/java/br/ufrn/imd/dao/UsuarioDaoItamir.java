package br.ufrn.imd.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrn.imd.app.model.Usuario;

@Stateless
public class UsuarioDaoItamir  {

	@PersistenceContext
    private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Usuario salvarOuAtualizar(Usuario usuario) {
		if(usuario.getId() == 0)
			em.persist(usuario);
		else
			em.merge(usuario);
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> listagem() {
		return (List<Usuario>) 
				em.createQuery("select u from Usuario u").getResultList();
	}	
	
}
