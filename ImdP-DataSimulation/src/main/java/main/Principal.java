package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import br.ufrn.imd.dominio.Cargo;
import br.ufrn.imd.dominio.JustificativaFalta;
import br.ufrn.imd.dominio.Maquina;
import br.ufrn.imd.dominio.MesTrabalho;
import br.ufrn.imd.dominio.Permissao;
import br.ufrn.imd.dominio.Ponto;
import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.dominio.TipoJustificativa;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.dominio.Usuario;
import br.ufrn.imd.dominio.Vinculo;
import dao.GenericDao;
import javassist.expr.NewArray;

public class Principal {
	
	public static void main(String[] args) throws ParseException {
		
		Calendar data = Calendar.getInstance();
		GenericDao gdao = new GenericDao();
		
		//CARGO
		Cargo c2 = new Cargo();
		c2.setNome("Coordenador");
		
		Cargo c3 = new Cargo();
		c3.setNome("Diretor");
		
		Cargo c4 = new Cargo();
		c4.setNome("Vice-Diretor");
		
		Cargo c1 = new Cargo();
		c1.setNome("Desenvolvedor");
		
		ArrayList<Cargo> cargos = new ArrayList<Cargo>();
		cargos.add(c1);
		cargos.add(c2);
		cargos.add(c3);

		gdao.inserir(c1);
		gdao.inserir(c2);
		gdao.inserir(c3);
		gdao.inserir(c4);
		
		
		//UNIDADE
		Unidade u1 = new Unidade();
		u1.setNome("IMD");
		
		Unidade u2 = new Unidade();
		u2.setNome("BIO");
		
		Unidade u3 = new Unidade();
		u3.setNome("DIMAP");
		
		Unidade u4 = new Unidade();
		u4.setNome("ECT");
		
		ArrayList<Unidade> unidades = new ArrayList<Unidade>();
		unidades.add(u1);
		unidades.add(u2);
		unidades.add(u3);
		unidades.add(u4);
		
		gdao.inserir(u1);
		gdao.inserir(u2);
		gdao.inserir(u3);
		gdao.inserir(u4);

		
		//TIPO DE JUSTIFICATIVA
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
		
		ArrayList<TipoJustificativa> tipos = new ArrayList<TipoJustificativa>();
		tipos.add(tp1);
		tipos.add(tp2);
		tipos.add(tp3);
		tipos.add(tp4);
		tipos.add(tp5);
		
		gdao.inserir(tp1);
		gdao.inserir(tp2);
		gdao.inserir(tp3);
		gdao.inserir(tp4);
		gdao.inserir(tp5);

		//MAQUINA
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
		
		ArrayList<Maquina> maqs = new ArrayList<Maquina>();
		maqs.add(m1);
		maqs.add(m2);
		maqs.add(m3);
		maqs.add(m4);
		maqs.add(m5);
		maqs.add(m6);
		maqs.add(m7);
		maqs.add(m8);
		
		gdao.inserir(m1);
		gdao.inserir(m2);
		gdao.inserir(m3);
		gdao.inserir(m4);
		gdao.inserir(m5);
		gdao.inserir(m6);
		gdao.inserir(m7);
		gdao.inserir(m8);
		
		
		//PERMISSAO
		Permissao p1 = new Permissao();
		p1.setDescricao("Diretor");
		
		Permissao p2 = new Permissao();
		p2.setDescricao("Vice-Diretor");
		
		Permissao p3 = new Permissao();
		p3.setDescricao("Coordenador");
		
		Permissao p4 = new Permissao();
		p4.setDescricao("Desenvolvedor");
		
		ArrayList<Permissao> perms = new ArrayList<Permissao>();
		
		perms.add(p1);
		perms.add(p2);
		perms.add(p3);
		perms.add(p4);
		
		gdao.inserir(p1);
		gdao.inserir(p2);
		gdao.inserir(p3);
		gdao.inserir(p4);
		
		
		//SETOR
		Setor s1 = new Setor();
		s1.setNome("s1 - IMD");
		s1.setUnidade(u1);
		
		Setor s2 = new Setor();
		s2.setNome("s2 - IMD");
		s2.setUnidade(u1);
		
		Setor s3 = new Setor();
		s3.setNome("s3 - IMD");
		s3.setUnidade(u1);
		
		Setor s4 = new Setor();
		s4.setNome("s1 - BIO");
		s4.setUnidade(u2);
		
		Setor s5 = new Setor();
		s5.setNome("s2 - BIO");
		s5.setUnidade(u2);
		
		Setor s6 = new Setor();
		s6.setNome("s1 - DIMAP");
		s6.setUnidade(u3);
		
		Setor s7 = new Setor();
		s7.setNome("s1 - ECT");
		s7.setUnidade(u4);
		
		ArrayList<Setor> setores = new ArrayList<Setor>();
		setores.add(s1);
		setores.add(s2);
		setores.add(s3);
		setores.add(s4);
		setores.add(s5);
		setores.add(s6);
		setores.add(s7);
		
		gdao.inserir(s1);
		gdao.inserir(s2);
		gdao.inserir(s3);
		gdao.inserir(s4);
		gdao.inserir(s5);
		gdao.inserir(s6);
		gdao.inserir(s7);
		
		//MES TRABALHO
		
		ArrayList<MesTrabalho> meses = new ArrayList<MesTrabalho>();
		
		for(int i = 1; i <= 12;i++ ){
			MesTrabalho mt1 = new MesTrabalho();
			mt1.setAno(2016);
			mt1.setMes(i);
			mt1.setDiasUteis(20);
			gdao.inserir(mt1);
			meses.add(mt1);
		}
		
		//USUARIO
		ArrayList<String> nomes = new ArrayList<String>();
		nomes.add("Jose felicio");
		nomes.add("Marcos Alexandre");
		nomes.add("Carlos Andre Barreto");
		nomes.add("Joao da falha");
		nomes.add("Manel das cunhas");
		nomes.add("Aida Colares");
		nomes.add("Alda Guedes");
		nomes.add("Alice Cayado");
		nomes.add("Angelina Velasco");
		nomes.add("Caím Filgueiras");
		nomes.add("Damião Pirassununga");
		nomes.add("Delfina Pimentel");
		nomes.add("Emílio Sousa do Prado");
		nomes.add("Eva Quirino");
		nomes.add("Filinto Magalhães");
		nomes.add("Graça Granjeia");
		nomes.add("Guadalupe Villas Bôas");
		nomes.add("Isaura Gomide");
		nomes.add("Lavínia Brito");
		nomes.add("Leonir Montero");
		nomes.add("Lília Dantas");
		nomes.add("Miguel Cambaúva");
		nomes.add("Miguel Tibiriçá");
		nomes.add("Minervina Eiró");
		nomes.add("Olga Valério");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ArrayList<String> datas = new ArrayList<String>();
		datas.add("15/08/1987");
		datas.add("12/06/1992");
		datas.add("21/05/1986");
		datas.add("15/08/1987");
		datas.add("12/06/1992");
		datas.add("21/05/1986");
		datas.add("15/08/1987");
		datas.add("12/06/1992");
		datas.add("21/05/1986");
		datas.add("15/08/1987");
		datas.add("12/06/1992");
		datas.add("21/05/1986");
		datas.add("15/08/1987");
		datas.add("12/06/1992");
		datas.add("21/05/1986");
		datas.add("15/08/1987");
		datas.add("12/06/1992");
		datas.add("21/05/1986");
		datas.add("15/08/1987");
		datas.add("12/06/1992");
		datas.add("15/08/1987");
		datas.add("12/06/1992");
		datas.add("21/05/1986");
		datas.add("15/08/1987");
		datas.add("12/06/1992");
		
		ArrayList<String> cpfs = new ArrayList<String>();
		cpfs.add("067534824-58");
		cpfs.add("234568234-65");
		cpfs.add("067534824-59");
		cpfs.add("");
		cpfs.add("067534824-60");
		cpfs.add("234568234-67");
		cpfs.add("067534824-61");
		cpfs.add("234568234-68");
		cpfs.add("067534824-62");
		cpfs.add("234568234-69");
		cpfs.add("067534824-63");
		cpfs.add("234568234-70");
		cpfs.add("067534824-64");
		cpfs.add("234568234-71");
		cpfs.add("067534824-65");
		cpfs.add("234568234-72");
		cpfs.add("067534824-66");
		cpfs.add("234568234-73");
		cpfs.add("067534824-67");
		cpfs.add("234568234-74");
		cpfs.add("067534824-68");
		cpfs.add("234568234-75");
		cpfs.add("067534824-69");
		cpfs.add("234568234-76");
		cpfs.add("067534824-70");
		
		ArrayList<String> emails = new ArrayList<String>();
		emails.add("e1@gmail.com");
		emails.add("e2@gmail.com");
		emails.add("e3@gmail.com");
		emails.add("e4@gmail.com");
		emails.add("e5@gmail.com");
		emails.add("Aida Colares@gmail.com");
		emails.add("Alda Guedes@gmail.com");
		emails.add("Alice Cayado@gmail.com");
		emails.add("Angelina Velasco@gmail.com");
		emails.add("Caím Filgueiras@gmail.com");
		emails.add("Damião Pirassununga@gmail.com");
		emails.add("Delfina Pimentel@gmail.com");
		emails.add("Emílio Sousa do Prado@gmail.com");
		emails.add("Eva Quirino@gmail.com");
		emails.add("Filinto Magalhães@gmail.com");
		emails.add("Graça Granjeia@gmail.com");
		emails.add("Guadalupe Villas Bôas@gmail.com");
		emails.add("Isaura Gomide@gmail.com");
		emails.add("Lavínia Brito@gmail.com");
		emails.add("Leonir Montero@gmail.com");
		emails.add("Lília Dantas@gmail.com");
		emails.add("Miguel Cambaúva@gmail.com");
		emails.add("Miguel Tibiriçá@gmail.com");
		emails.add("Minervina Eiró@gmail.com");
		emails.add("Olga Valério@gmail.com");
		
		ArrayList<String> tels = new ArrayList<String>();
		
		tels.add("98888-0001");
		tels.add("98888-0002");
		tels.add("98888-0003");
		tels.add("98888-0004");
		tels.add("98888-0005");
		tels.add("98888-0006");
		tels.add("98888-0007");
		tels.add("98888-0008");
		tels.add("98888-0009");
		tels.add("98888-0010");
		tels.add("98888-0011");
		tels.add("98888-0012");
		tels.add("98888-0013");
		tels.add("98888-0014");
		tels.add("98888-0015");
		tels.add("98888-0016");
		tels.add("98888-0017");
		tels.add("98888-0018");
		tels.add("98888-0019");
		tels.add("98888-0020");
		tels.add("98888-0021");
		tels.add("98888-0022");
		tels.add("98888-0023");
		tels.add("98888-0024");
		tels.add("98888-0025");
		
		ArrayList<String> logins = new ArrayList<String>();
		logins.add("user1");
		logins.add("user2");
		logins.add("user3");
		logins.add("user4");
		logins.add("user5");
		logins.add("user6");
		logins.add("user7");
		logins.add("user8");
		logins.add("user9");
		logins.add("user10");
		logins.add("user11");
		logins.add("user12");
		logins.add("user13");
		logins.add("user14");
		logins.add("user15");
		logins.add("user16");
		logins.add("user17");
		logins.add("user18");
		logins.add("user19");
		logins.add("user20");
		logins.add("user21");
		logins.add("user22");
		logins.add("user23");
		logins.add("user24");
		logins.add("user25");
		
		ArrayList<String> senhas = new ArrayList<String>();
		senhas.add("user1");
		senhas.add("user2");
		senhas.add("user3");
		senhas.add("user4");
		senhas.add("user5");
		senhas.add("user6");
		senhas.add("user7");
		senhas.add("user8");
		senhas.add("user9");
		senhas.add("user10");
		senhas.add("user11");
		senhas.add("user12");
		senhas.add("user13");
		senhas.add("user14");
		senhas.add("user15");
		senhas.add("user16");
		senhas.add("user17");
		senhas.add("user18");
		senhas.add("user19");
		senhas.add("user20");
		senhas.add("user21");
		senhas.add("user22");
		senhas.add("user23");
		senhas.add("user24");
		senhas.add("user25");
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		for(int i = 0; i < nomes.size(); i++){
			Usuario u = new Usuario();
			u.setNome(nomes.get(i));
			u.setDataNascimento(sdf.parse(datas.get(i)));
			u.setCpf(cpfs.get(i));
			u.setEmail(emails.get(i));
			u.setTelefone1(tels.get(i));
			u.setLogin(logins.get(i));
			u.setSenha(senhas.get(i));
			gdao.inserir(u);
			
			usuarios.add(u);
		}
		
		//VINCULO
		
		ArrayList<Vinculo> vincs = new ArrayList<Vinculo>();
		int to4 = 0;
		int to7 = 0;
		
		for(int i = 0; i < 25; i++){
			Vinculo v = new Vinculo();
			v.setCargaHorariaDiaria(4);
			v.setDescricao("blablabla");
			
			if(to4 > 3){
				to4 = 0;
			}
			
			if(to7 > 6){
				to7 = 0;
			}
			
			v.setPermissao(perms.get(to4));
			v.setSetor(setores.get(to7));
			v.setUsuario(usuarios.get(i));
			v.setSituacao('a');
			to7++;
			to4++;
			gdao.inserir(v);
			vincs.add(v);
		}
		
		//PONTO
		to7 = 0;
		int to25 = 0;
		ArrayList<Ponto> pontos = new ArrayList<Ponto>();
		
		for(int i = 0; i < 128; i++){
			Ponto pt1 = new Ponto();
			pt1.setTimeStamp(sdf.parse("10/07/2014"));
			pt1.setTimeStampAlteracao(null);
			pt1.setTipo('n');
			pt1.setValidado('s');
			if(to7 > 7){
				to7 = 0;
			}
			if(to25 > 24){
				to25 = 0;
			}
			
			pt1.setMaquina(maqs.get(to7));
			pt1.setVinculo(vincs.get(to25));
			
			to7++;
			to25++;
			
			gdao.inserir(pt1);
		}
		
		int to5 = 0;
		//JUSTIFICATIVA DE FALTA
		for(int i = 0; i < 17; i++){
		if(to5 > 4){
			to5 = 0;
		}
			JustificativaFalta just = new JustificativaFalta();
			just.setTipoJustificativa(tipos.get(to5));
			just.setDescricao("blablebli");
			just.setDataInicio(sdf.parse("20/06/2014"));
			just.setDataFim(sdf.parse("21/06/2014"));
			just.setVinculo(vincs.get(to5));
			just.setSituacao('d');
			to5++;
			
			gdao.inserir(just);
		}
	}
}