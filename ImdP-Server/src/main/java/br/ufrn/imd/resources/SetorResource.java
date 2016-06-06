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

import br.ufrn.imd.business.SetorService;
import br.ufrn.imd.dominio.Setor;

@Stateless
@Path("/consulta")
public class SetorResource {

	@EJB
	private SetorService service;

	//LIST
	@GET
	@Path("/setores")
	@Produces("application/json; charset=UTF-8")
	public List<Setor> listagem() {
		return service.listar();
	}
	
	//FIND BY ID
	@GET
	@Path("/setores/{id}")
	@Produces("application/json; charset=UTF-8")
	public Setor buscaId(@PathParam("id") int id){
		Setor place = service.buscar(id);
		return place;
	}
	
	//CREATE
	@POST
	@Path("/setores")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(Setor setor) {
		Setor place = new Setor();
		
		try{
			service.save(setor);
			return Response.status(200).entity(place).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(place).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/setores/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response update(@PathParam("id") int id) {
		Setor place = new Setor();
		try{
			place = service.buscar(id);
			service.update(place);
			return Response.status(200).entity(place).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(place).build();
		}
	}
	
	//DELETE
	@DELETE
	@Path("/setores/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response delete(@PathParam("id") int id) {
		Setor place = new Setor();
		try{
			place = service.buscar(id);
			service.delete(place);
			return Response.status(200).entity(place).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(place).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/setores/{nomeSetor}/unidades/{idUnidade}")
	@Produces("application/json; charset=UTF-8")
	public List<Setor> buscaFiltro(@PathParam("nomeSetor") String nomeSetor, 
			@PathParam("idUnidade")int idUnidade) {
		
		ArrayList<Setor> places = new ArrayList<Setor>();
		places = service.buscarFiltro(nomeSetor, idUnidade);
		return places;
	}
}
