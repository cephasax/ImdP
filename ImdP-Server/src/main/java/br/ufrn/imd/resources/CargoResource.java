package br.ufrn.imd.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.ufrn.imd.business.CargoService;
import br.ufrn.imd.dominio.Cargo;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

@Stateless
@Path("/consulta")
public class CargoResource {

	@EJB
	private CargoService service;

	//LIST
	@GET
	@Path("/cargos")
	@Produces("application/json; charset=UTF-8")
	public List<Cargo> listagem(){
		try {
			return service.listar();
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//FIND BY ID
	@GET
	@Path("/cargos/{id}")
	@Produces("application/json; charset=UTF-8")
	public Cargo buscaId(@PathParam("id") int id){
		Cargo cargo;
		try {
			cargo = service.buscar(id);
			return cargo;
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//CREATE
	@POST
	@Path("/cargos")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(Cargo cargo) {
		try{
			service.save(cargo);
			return Response.status(200).entity(cargo).build();
		}
		catch (Exception e){
			return Response.status(409).entity(cargo).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/cargos")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response update(Cargo cargo) {
		try{
			Cargo c = service.buscar(cargo.getIdCargo());
			service.update(cargo);
			return Response.status(200).entity(cargo).build();
		}
		catch (Exception e){
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
		catch (Exception e){
			return Response.status(204).entity(cargo).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/cargosFilter")
	@Produces("application/json; charset=UTF-8")
	public List<Cargo> buscaFiltro(@DefaultValue("")@QueryParam("nomeCargo") String nomeCargo){
		
		ArrayList<Cargo> cargos = new ArrayList<Cargo>();
		cargos = service.buscarFiltro(nomeCargo);
		return cargos;
	}
}
