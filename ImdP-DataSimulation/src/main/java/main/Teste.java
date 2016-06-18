package main;

import java.util.ArrayList;

import javax.persistence.Query;

import br.ufrn.imd.dominio.Cargo;
import br.ufrn.imd.dominio.Usuario;
import dao.Banco;

public class Teste {

	public static void main(String[] args) {
		String cpf = "67";
		String nomeUsuario = "mo";
		
		//CONSTRUCAO DA CONSULTA SQL
		String sql = "SELECT u FROM Usuario u";
				
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		if (!cpf.equals("")){
			where.append(" and u.cpf like :cpf");
		}
		
		if (!nomeUsuario.equals("")){
			where.append(" and lower(u.nome) like lower(:nomeUsuario) ");
		}
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = Banco.getInstance().getEntityManager().createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		if (!cpf.equals("")){
			query.setParameter("cpf", "%"+cpf+"%");
		}
		if (!nomeUsuario.equals("")){
			query.setParameter("nomeUsuario", "%"+nomeUsuario+"%");
		}
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = (ArrayList<Usuario>)query.getResultList();
		System.out.println(usuarios.size());

		for(Usuario u: usuarios){
			System.out.println(u.getNome() + " - " + u.getCpf());
		}
	}

}
