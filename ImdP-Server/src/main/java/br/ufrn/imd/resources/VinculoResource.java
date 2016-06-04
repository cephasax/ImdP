package br.ufrn.imd.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.ufrn.imd.business.VinculoService;
import br.ufrn.imd.dominio.Usuario;
import br.ufrn.imd.dominio.Vinculo;

@Stateless
@Path("/consulta")
public class VinculoResource {

	@EJB
	private VinculoService service;

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/vinculos")
	public List<Vinculo> listagem() {
		return service.findAll();
	}
	
	@GET
	@Path("/vinculos/{login}")
	public String login(@PathParam("login") String login) {
		return " Pegando o login: " + login;
	}
	
	@POST
	@Path("/novousuario")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(Vinculo usuario) {
		service.save(usuario);
		return Response.status(200).entity(usuario).build();
	}
	
}
