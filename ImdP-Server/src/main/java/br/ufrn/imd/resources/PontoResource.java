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

import br.ufrn.imd.business.UsuarioService;
import br.ufrn.imd.dominio.Usuario;

@Stateless
@Path("/consulta")
public class PontoResource {

	@EJB
	private UsuarioService service;

	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/hello")
	public String hello() {
		return "Voce conseguiu jovem";
	}
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/usuarios")
	public List<Usuario> listagem() {
		return service.findAll();
	}
	@GET
	@Path("/usuarios/{login}")
	public String login(@PathParam("login") String login) {
		return " Pegando o login: " + login;
	}
	@POST
	@Path("/novousuario")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(Usuario usuario) {
		service.save(usuario);
		return Response.status(200).entity(usuario).build();
	}
}
