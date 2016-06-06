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

import br.ufrn.imd.business.PontoService;
import br.ufrn.imd.dominio.Ponto;

@Stateless
@Path("/consulta")
public class PontoResource {

	@EJB
	private PontoService service;

	//LIST
	@GET
	@Path("/pontos")
	@Produces("application/json; charset=UTF-8")
	public List<Ponto> listagem() {
		return service.listar();
	}
	
	//FIND BY ID
	@GET
	@Path("/pontos/{id}")
	@Produces("application/json; charset=UTF-8")
	public Ponto buscaId(@PathParam("id") int id){
		Ponto dot = service.buscar(id);
		return dot;
	}
	
	//CREATE
	@POST
	@Path("/pontos")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(Ponto ponto) {
		Ponto dot = new Ponto();
		
		try{
			service.save(ponto);
			return Response.status(200).entity(dot).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(dot).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/pontos/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response update(@PathParam("id") int id) {
		Ponto dot = new Ponto();
		try{
			dot = service.buscar(id);
			service.update(dot);
			return Response.status(200).entity(dot).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(dot).build();
		}
	}
	
	//DELETE
	@DELETE
	@Path("/pontos/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response delete(@PathParam("id") int id) {
		Ponto dot = new Ponto();
		try{
			dot = service.buscar(id);
			service.delete(dot);
			return Response.status(200).entity(dot).build();
		}
		catch (NoResultException e){
			return Response.status(204).entity(dot).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/pontos/{nomePonto}/{idUnidade}/{idSetor}")
	@Produces("application/json; charset=UTF-8")
	public List<Ponto> buscaFiltro(@PathParam("nomePonto") String nomePonto, 
			@PathParam("idUnidade")int idUnidade, @PathParam("idSetor")int idSetor) {
		
		ArrayList<Ponto> dots = new ArrayList<Ponto>();
		dots = service.buscarFiltro(nomePonto, idUnidade, idSetor);
		return dots;
	}
	
	//FIND PONTOS USUARIO
	@GET
	@Path("/pontos/usuario/{idUsuario}")
	@Produces("application/json; charset=UTF-8")
	public List<Ponto> buscaPontoUsuario(@PathParam("idUsuario") int idUsuario) {
		
		ArrayList<Ponto> dots = new ArrayList<Ponto>();
		dots = service.buscarPontosUsuario(idUsuario);
		return dots;
	}
	//FIND PONTOS USUARIO
	@GET
	@Path("/pontos/vinculo/{idVinculo}")
	@Produces("application/json; charset=UTF-8")
	public List<Ponto> buscaPontoVinculo(@PathParam("idVinculo") int idVinculo) {
		
		ArrayList<Ponto> dots = new ArrayList<Ponto>();
		dots = service.buscarPontosVinculo(idVinculo);
		return dots;
	}
}
