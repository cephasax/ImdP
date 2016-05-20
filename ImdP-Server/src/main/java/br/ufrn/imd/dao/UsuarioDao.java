package br.ufrn.imd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.ufrn.imd.dominio.Usuario;

public class UsuarioDao extends GenericDao {

	EntityManager em = getEntityManager();

	public Usuario findByUnidade(int idUnidade) {
		Query a = em.createQuery("from Usuario u where u.unidade.idUnidade = " + idUnidade);
		List<Usuario> resultsA = a.getResultList();
		for (Usuario elemento : resultsA) {
			System.out.println("Usuario: " + elemento.getLogin());
			return elemento;
		}
		return null;
	}

	public Usuario findByUnidadeSetor(int idUnidade, int idSetor) {
		Query a = em.createQuery(
				"from Usuario u where u.unidade.idUnidade = " + idUnidade + "and u.setor.idsetor = " + idSetor);
		List<Usuario> resultsA = a.getResultList();
		for (Usuario elemento : resultsA) {
			System.out.println("Usuario: " + elemento.getLogin());
			return elemento;
		}
		return null;
	}

	public Usuario findBySetor(int idSetor) {
		Query a = em.createQuery("from Usuario u where u.setor.idsetor = " + idSetor);
		List<Usuario> resultsA = a.getResultList();
		for (Usuario elemento : resultsA) {
			System.out.println("Usuario: " + elemento.getLogin());
			return elemento;
		}
		return null;
	}

	public Usuario findByUnidadeSetorNome(int idUnidade, int idSetor, String nome) {
		Query a = em.createQuery("from Usuario u where u.unidade.idUnidade = " + idUnidade + " and u.setor.idsetor = "
				+ idSetor + " and u.pessoa.nome = " + nome);
		List<Usuario> resultsA = a.getResultList();
		for (Usuario elemento : resultsA) {
			System.out.println("Usuario: " + elemento.getLogin());
			return elemento;
		}
		return null;
	}

	public Usuario findByNome(String nome) {
		Query a = em.createQuery("from Usuario u where u.pessoa.nome = " + nome);
		List<Usuario> resultsA = a.getResultList();
		for (Usuario elemento : resultsA) {
			System.out.println("Usuario: " + elemento.getLogin());
			return elemento;
		}
		return null;
	}

	public List<Usuario> listAll() {
		Query a = em.createQuery("from Usuario u");
		List<Usuario> resultsA = a.getResultList();
		for (Usuario elemento : resultsA) {
			System.out.println("Usuario: " + elemento.getLogin());
		}
		return resultsA;
	}
}
