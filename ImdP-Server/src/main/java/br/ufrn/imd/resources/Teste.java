package br.ufrn.imd.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.ufrn.imd.business.CargoService;
import br.ufrn.imd.dominio.Cargo;

@Path("/teste")
@Stateless
public class Teste {

	@EJB
	private CargoService service;
	
	//TESTE
	@GET
	@Path("/oi")
	@Produces("application/json; charset=UTF-8")
	public String oi() {
		return "Servidor funcionando corretamente";
	}
	
}
