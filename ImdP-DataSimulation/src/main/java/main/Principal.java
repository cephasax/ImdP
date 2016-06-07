package main;

import java.util.Calendar;

import br.ufrn.imd.dominio.*;

import dao.GenericDao;

public class Principal {
	
	
	
	public static void main(String[] args) {
		
		Calendar data = Calendar.getInstance();
		GenericDao gdao = new GenericDao();
		
		
		Cargo c1 = new Cargo();
		c1.setNome("Desenvolvedor");
		
		Cargo c2 = new Cargo();
		c2.setNome("Coordenador");
		
		Cargo c3 = new Cargo();
		c3.setNome("Diretor");
		
		Cargo c4 = new Cargo();
		c4.setNome("Vice-Diretor");
		
		gdao.inserir(c1);
		gdao.inserir(c2);
		gdao.inserir(c3);
		gdao.inserir(c4);
		
		
		Unidade u1 = new Unidade();
		u1.setNome("IMD");
		
		Unidade u2 = new Unidade();
		u2.setNome("BIO");
		
		Unidade u3 = new Unidade();
		u3.setNome("DIMAP");
		
		Unidade u4 = new Unidade();
		u4.setNome("ECT");
		
		gdao.inserir(u1);
		gdao.inserir(u2);
		gdao.inserir(u3);
		gdao.inserir(u4);
		
		
		
		TipoJustificativa tp1 = new TipoJustificativa();
		tp1.setNome("Doenca");
		
		TipoJustificativa tp2 = new TipoJustificativa();
		tp2.setNome("Acompanhamento de familiar");
		
		TipoJustificativa tp3 = new TipoJustificativa();
		tp3.setNome("Atividade academica extraordinaria");
		
		TipoJustificativa tp4 = new TipoJustificativa();
		tp4.setNome("Viagem a trabalho");
		
		TipoJustificativa tp5 = new TipoJustificativa();
		tp5.setNome("Outra");
		
		gdao.inserir(tp1);
		gdao.inserir(tp2);
		gdao.inserir(tp3);
		gdao.inserir(tp4);
		gdao.inserir(tp5);
		
		
		
		Maquina m1 = new Maquina();
		m1.setDenominacao("Maquina IMD 01");
		m1.setIp("192.168.0.135");
		m1.setUnidade(u1);
		
		Maquina m2 = new Maquina();
		m2.setDenominacao("Maquina IMD 02");
		m2.setIp("192.168.0.136");
		m2.setUnidade(u1);
		
		Maquina m3 = new Maquina();
		m3.setDenominacao("Maquina ECT 01");
		m3.setIp("192.168.0.137");
		m3.setUnidade(u4);
		
		Maquina m4 = new Maquina();
		m4.setDenominacao("Maquina DIMAP 01");
		m4.setIp("192.168.0.138");
		m4.setUnidade(u3);
		
		Maquina m5 = new Maquina();
		m5.setDenominacao("Maquina DIMAP 02");
		m5.setIp("192.168.0.139");
		m5.setUnidade(u3);
		
		Maquina m6 = new Maquina();
		m6.setDenominacao("Maquina BIO 01");
		m6.setIp("192.168.0.140");
		m6.setUnidade(u2);
		
		Maquina m7 = new Maquina();
		m7.setDenominacao("Maquina BIO 02");
		m7.setIp("192.168.0.141");
		m7.setUnidade(u2);
		
		Maquina m8 = new Maquina();
		m8.setDenominacao("Maquina BIO 03");
		m8.setIp("192.168.0.142");
		m8.setUnidade(u2);
		
		gdao.inserir(m1);
		gdao.inserir(m2);
		gdao.inserir(m3);
		gdao.inserir(m4);
		gdao.inserir(m5);
		gdao.inserir(m6);
		gdao.inserir(m7);
		gdao.inserir(m8);
		
		Permissao p1 = new Permissao();
		p1.setDescricao("Diretor");
		
		Permissao p2 = new Permissao();
		p2.setDescricao("Vice-Diretor");
		
		Permissao p3 = new Permissao();
		p3.setDescricao("Coordenador");
		
		Permissao p4 = new Permissao();
		p4.setDescricao("Desenvolvedor");
		
		gdao.inserir(p1);
		gdao.inserir(p2);
		gdao.inserir(p3);
		gdao.inserir(p4);
		
	}

}
