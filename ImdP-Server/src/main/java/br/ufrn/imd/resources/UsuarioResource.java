package br.ufrn.imd.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.ufrn.imd.business.UsuarioService;
import br.ufrn.imd.dominio.Usuario;

@Stateless
@Path("/consulta")
public class UsuarioResource {

	@EJB
	private UsuarioService service;

	//LIST
	@GET
	@Path("/usuarios")
	@Produces("application/json; charset=UTF-8")
	public List<Usuario> listagem() {
		return service.listar();
	}
	
	//FIND BY ID
	@GET
	@Path("/usuarios/{id}")
	@Produces("application/json; charset=UTF-8")
	public Usuario buscaId(@PathParam("id") int id){
		Usuario user = service.buscar(id);
		return user;
	}
	
	//CREATE
	@POST
	@Path("/usuarios")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(Usuario usuario) {
		Usuario user = new Usuario();
		
		try{
			service.save(usuario);
			return Response.status(200).entity(user).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(user).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/usuarios/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response update(@PathParam("id") int id) {
		Usuario user = new Usuario();
		try{
			user = service.buscar(id);
			service.update(user);
			return Response.status(200).entity(user).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(user).build();
		}
	}
	
	//DELETE
	@DELETE
	@Path("/usuarios/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response delete(@PathParam("id") int id) {
		Usuario user = new Usuario();
		try{
			user = service.buscar(id);
			service.delete(user);
			return Response.status(200).entity(user).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(user).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/usuarios/{nomeUsuario}/unidades/{idUnidade}/setores/{idSetor}")
	@Produces("application/json; charset=UTF-8")
	public List<Usuario> buscaFiltro(@PathParam("nomeUsuario") String nomeUsuario, 
			@PathParam("idUnidade")int idUnidade, @PathParam("idSetor")int idSetor) {
		
		ArrayList<Usuario> users = new ArrayList<Usuario>();
		users = service.buscarFiltro(nomeUsuario, idUnidade, idSetor);
		return users;
	}
}
