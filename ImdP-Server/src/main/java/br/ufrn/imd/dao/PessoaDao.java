//package br.ufrn.imd.dao;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//
//import br.ufrn.imd.dominio.Pessoa;
//import br.ufrn.imd.dominio.Pessoa;
//
//public class PessoaDao extends GenericDao {
//	
//	EntityManager em = getEntityManager();
//
//	public Pessoa findByUnidade(int idUnidade) {
//		Query a = em.createQuery("from Pessoa p where p.unidade.idUnidade = " + idUnidade);
//		List<Pessoa> resultsA = a.getResultList();
//		for (Pessoa elemento : resultsA) {
//			System.out.println("Pessoa: " + elemento.getNome());
//			return elemento;
//		}
//		return null;
//	}
//
//	public Pessoa findByUnidadeSetor(int idUnidade, int idSetor) {
//		Query a = em.createQuery(
//				"from Pessoa p where p.unidade.idUnidade = " + idUnidade + "and p.setor.idsetor = " + idSetor);
//		List<Pessoa> resultsA = a.getResultList();
//		for (Pessoa elemento : resultsA) {
//			System.out.println("Pessoa: " + elemento.getNome());
//			return elemento;
//		}
//		return null;
//	}
//
//	public Pessoa findBySetor(int idSetor) {
//		Query a = em.createQuery("from Pessoa p where p.setor.idsetor = " + idSetor);
//		List<Pessoa> resultsA = a.getResultList();
//		for (Pessoa elemento : resultsA) {
//			System.out.println("Pessoa: " + elemento.getNome());
//			return elemento;
//		}
//		return null;
//	}
//
//	public Pessoa findByUnidadeSetorNome(int idUnidade, int idSetor, String nome) {
//		Query a = em.createQuery("from Pessoa p where p.unidade.idUnidade = " + idUnidade + " and p.setor.idsetor = "
//				+ idSetor + " and p.nome = " + nome);
//		List<Pessoa> resultsA = a.getResultList();
//		for (Pessoa elemento : resultsA) {
//			System.out.println("Pessoa: " + elemento.getNome());
//			return elemento;
//		}
//		return null;
//	}
//
//	public Pessoa findByNome(String nome) {
//		Query a = em.createQuery("from Pessoa p where p.nome = " + nome);
//		List<Pessoa> resultsA = a.getResultList();
//		for (Pessoa elemento : resultsA) {
//			System.out.println("Pessoa: " + elemento.getNome());
//			return elemento;
//		}
//		return null;
//	}
//	public List<Pessoa> listAll() {
//		Query a = em.createQuery("from Pessoa p");
//		List<Pessoa> resultsA = a.getResultList();
//		for (Pessoa elemento : resultsA) {
//			System.out.println("Pessoa: " + elemento.getNome());
//		}
//		return resultsA;
//	}
//}
