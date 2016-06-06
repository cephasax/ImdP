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

import br.ufrn.imd.business.CargoService;
import br.ufrn.imd.dominio.Cargo;

@Stateless
@Path("/consulta")
public class CargoResource {

	@EJB
	private CargoService service;

	//LIST
	@GET
	@Path("/cargos")
	@Produces("application/json; charset=UTF-8")
	public List<Cargo> listagem() {
		return service.listar();
	}
	
	//FIND BY ID
	@GET
	@Path("/cargos/{id}")
	@Produces("application/json; charset=UTF-8")
	public Cargo buscaId(@PathParam("id") int id){
		Cargo cargo = service.buscar(id);
		return cargo;
	}
	
	//CREATE
	@POST
	@Path("/cargos")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(Cargo cargo) {
		Cargo car = new Cargo();
		
		try{
			service.save(car);
			return Response.status(200).entity(car).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(car).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/cargos/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response update(@PathParam("id") int id) {
		Cargo cargo = new Cargo();
		try{
			cargo = service.buscar(id);
			service.update(cargo);
			return Response.status(200).entity(cargo).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(cargo).build();
		}
	}
	
	//DELETE
	@DELETE
	@Path("/cargos/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response delete(@PathParam("id") int id) {
		Cargo cargo = new Cargo();
		try{
			cargo = service.buscar(id);
			service.delete(cargo);
			return Response.status(200).entity(cargo).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(cargo).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/cargos/{nomeCargo}")
	@Produces("application/json; charset=UTF-8")
	public List<Cargo> buscaFiltro(@PathParam("nomeCargo") String nomeCargo){
		
		ArrayList<Cargo> cargos = new ArrayList<Cargo>();
		cargos = service.buscarFiltro(nomeCargo);
		return cargos;
	}
}
