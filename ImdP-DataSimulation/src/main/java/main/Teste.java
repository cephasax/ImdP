package main;

import java.util.ArrayList;

import javax.persistence.Query;

import br.ufrn.imd.dominio.Cargo;
import dao.Banco;

public class Teste {

	public static void main(String[] args) {
		String nomeCargo = "de";
		String sql = " Select c FROM Cargo c";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1");
		where.append(" and lower(c.nome) like lower(:nome) ");
		
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = Banco.getInstance().getEntityManager().createQuery(sqlFinal.toString());
		
		//DEFINICAO DOS PARAMETROS DA CONSULTA
		query.setParameter("nome", "%"+nomeCargo+"%");
		
		//EXECUCAO E RETORNO
		ArrayList<Cargo> cargos = new ArrayList<Cargo>();
		cargos = (ArrayList<Cargo>)query.getResultList();

		for(Cargo c: cargos){
			System.out.println(c.getNome());
		}
	}

}
