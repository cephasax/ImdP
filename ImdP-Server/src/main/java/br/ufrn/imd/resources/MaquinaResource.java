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

import br.ufrn.imd.business.MaquinaService;
import br.ufrn.imd.dominio.Maquina;

@Stateless
@Path("/consulta")
public class MaquinaResource {

	@EJB
	private MaquinaService service;

	//LIST
	@GET
	@Path("/maquinas")
	@Produces("application/json; charset=UTF-8")
	public List<Maquina> listagem() {
		return service.listar();
	}
	
	//FIND BY ID
	@GET
	@Path("/maquinas/{id}")
	@Produces("application/json; charset=UTF-8")
	public Maquina buscaId(@PathParam("id") int id){
		Maquina maq = service.buscar(id);
		return maq;
	}
	
	//CREATE
	@POST
	@Path("/maquinas")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(Maquina maquina) {
		Maquina maq = new Maquina();
		
		try{
			service.save(maquina);
			return Response.status(200).entity(maq).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(maq).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/maquinas/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response update(@PathParam("id") int id) {
		Maquina maq = new Maquina();
		try{
			maq = service.buscar(id);
			service.update(maq);
			return Response.status(200).entity(maq).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(maq).build();
		}
	}
	
	//DELETE
	@DELETE
	@Path("/maquinas/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response delete(@PathParam("id") int id) {
		Maquina maq = new Maquina();
		try{
			maq = service.buscar(id);
			service.delete(maq);
			return Response.status(200).entity(maq).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(maq).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/maquinas/{nomeMaquina}/{idUnidade}")
	@Produces("application/json; charset=UTF-8")
	public List<Maquina> buscaFiltro(@PathParam("nomeMaquina") String nomeMaquina, 
			@PathParam("idUnidade")int idUnidade) {
		
		ArrayList<Maquina> maqs = new ArrayList<Maquina>();
		maqs = service.buscarFiltro(nomeMaquina, idUnidade);
		return maqs;
	}
}
