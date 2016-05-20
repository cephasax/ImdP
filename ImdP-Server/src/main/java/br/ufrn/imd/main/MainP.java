package br.ufrn.imd.main;

import br.ufrn.imd.dao.MesTrabalhoDao;
import br.ufrn.imd.dominio.MesTrabalho;

public class MainP {

	public static void main(String[] args) {
		MesTrabalho mt = new MesTrabalho(1, 2016, 25);		
		
		MesTrabalhoDao mtDAO = new MesTrabalhoDao();
		mtDAO.inserir(mt);
		System.out.println("ss");
	}

}
