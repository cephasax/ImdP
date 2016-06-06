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

import br.ufrn.imd.business.UnidadeService;
import br.ufrn.imd.dominio.Unidade;

@Stateless
@Path("/consulta")
public class UnidadeResource {

	@EJB
	private UnidadeService service;

	//LIST
	@GET
	@Path("/unidades")
	@Produces("application/json; charset=UTF-8")
	public List<Unidade> listagem() {
		return service.listar();
	}
	
	//FIND BY ID
	@GET
	@Path("/unidades/{id}")
	@Produces("application/json; charset=UTF-8")
	public Unidade buscaId(@PathParam("id") int id){
		Unidade und = service.buscar(id);
		return und;
	}
	
	//CREATE
	@POST
	@Path("/unidades")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(Unidade unidade) {
		Unidade und = new Unidade();
		
		try{
			service.save(unidade);
			return Response.status(200).entity(und).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(und).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/unidades/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response update(@PathParam("id") int id) {
		Unidade und = new Unidade();
		try{
			und = service.buscar(id);
			service.update(und);
			return Response.status(200).entity(und).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(und).build();
		}
	}
	
	//DELETE
	@DELETE
	@Path("/unidades/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response delete(@PathParam("id") int id) {
		Unidade und = new Unidade();
		try{
			und = service.buscar(id);
			service.delete(und);
			return Response.status(200).entity(und).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(und).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/unidades/{nomeUnidade}")
	@Produces("application/json; charset=UTF-8")
	public List<Unidade> buscaFiltro(@PathParam("nomeUnidade") String nomeUnidade) {
		
		ArrayList<Unidade> unds = new ArrayList<Unidade>();
		unds = service.buscarFiltro(nomeUnidade);
		return unds;
	}
}
