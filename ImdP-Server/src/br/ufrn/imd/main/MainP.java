package br.ufrn.imd.main;

import javax.persistence.Query;

import br.ufrn.imd.conexao.Conexao;
import br.ufrn.imd.dominio.Vinculo;

public class MainP{
	static Conexao conex = Conexao.getInstance();
	
	public static void main(String[] args) {
		String sql = " Select v FROM Vinculo v";
		StringBuilder where = new StringBuilder();
		where.append(" WHERE 1 = 1 ");
		
		
		StringBuilder sqlFinal = new StringBuilder();
		sqlFinal.append(sql);
		sqlFinal.append(where.toString());	
		Query query = conex.getEntityManager().createQuery(sqlFinal.toString());
		
		Object obj = query.getSingleResult();
		Vinculo vinc = (Vinculo)obj;
		System.out.println(vinc.getSetor().getUnidade().getNome());
	}
}