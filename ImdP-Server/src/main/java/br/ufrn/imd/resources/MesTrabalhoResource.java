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

import br.ufrn.imd.business.MesTrabalhoService;
import br.ufrn.imd.dominio.MesTrabalho;

@Stateless
@Path("/consulta")
public class MesTrabalhoResource {

	@EJB
	private MesTrabalhoService service;

	//LIST
	@GET
	@Path("/mesesTrabalho")
	@Produces("application/json; charset=UTF-8")
	public List<MesTrabalho> listagem() {
		return service.listar();
	}
	
	//FIND BY ID
	@GET
	@Path("/mesesTrabalho/{id}")
	@Produces("application/json; charset=UTF-8")
	public MesTrabalho buscaId(@PathParam("id") int id){
		MesTrabalho mt = service.buscar(id);
		return mt;
	}
	
	//CREATE
	@POST
	@Path("/mesesTrabalho")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(MesTrabalho mesTrabalho) {
		MesTrabalho mt = new MesTrabalho();
		
		try{
			service.save(mesTrabalho);
			return Response.status(200).entity(mt).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(mt).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/mesesTrabalho/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response update(@PathParam("id") int id) {
		MesTrabalho mt = new MesTrabalho();
		try{
			mt = service.buscar(id);
			service.update(mt);
			return Response.status(200).entity(mt).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(mt).build();
		}
	}
	
	//DELETE
	@DELETE
	@Path("/mesesTrabalho/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response delete(@PathParam("id") int id) {
		MesTrabalho mt = new MesTrabalho();
		try{
			mt = service.buscar(id);
			service.delete(mt);
			return Response.status(200).entity(mt).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(mt).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/mesesTrabalho/{numeroMes}/{ano}")
	@Produces("application/json; charset=UTF-8")
	public List<MesTrabalho> buscaFiltro(@PathParam("numeroMes") int numeroMes, 
			@PathParam("ano")int ano) {
		
		ArrayList<MesTrabalho> mts = new ArrayList<MesTrabalho>();
		mts = service.buscarFiltro(numeroMes, ano);
		return mts;
	}
}
