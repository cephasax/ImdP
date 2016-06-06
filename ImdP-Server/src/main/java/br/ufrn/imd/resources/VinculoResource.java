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

import br.ufrn.imd.business.VinculoService;
import br.ufrn.imd.dominio.Vinculo;

@Stateless
@Path("/consulta")
public class VinculoResource {

	@EJB
	private VinculoService service;

	//LIST
	@GET
	@Path("/vinculos")
	@Produces("application/json; charset=UTF-8")
	public List<Vinculo> listagem() {
		return service.listar();
	}
	
	//FIND BY ID
	@GET
	@Path("/vinculos/{id}")
	@Produces("application/json; charset=UTF-8")
	public Vinculo buscaId(@PathParam("id") int id){
		Vinculo user = service.buscar(id);
		return user;
	}
	
	//CREATE
	@POST
	@Path("/vinculos")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(Vinculo vinculo) {
		Vinculo user = new Vinculo();
		
		try{
			service.save(vinculo);
			return Response.status(200).entity(user).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(user).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/vinculos/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response update(@PathParam("id") int id) {
		Vinculo user = new Vinculo();
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
	@Path("/vinculos/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response delete(@PathParam("id") int id) {
		Vinculo user = new Vinculo();
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
	@Path("/vinculos/{nomeUsuario}/{idUnidade}/{idSetor}")
	@Produces("application/json; charset=UTF-8")
	public List<Vinculo> buscaFiltro(@PathParam("nomeUsuario") String nomeUsuario, 
			@PathParam("idUnidade")int idUnidade, @PathParam("idSetor")int idSetor) {
		
		ArrayList<Vinculo> users = new ArrayList<Vinculo>();
		users = service.buscarFiltro(nomeUsuario, idUnidade, idSetor);
		return users;
	}
}
