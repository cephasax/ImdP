package br.ufrn.imd.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.ufrn.imd.business.MaquinaService;
import br.ufrn.imd.dominio.Maquina;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

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
		try {
			return service.listar();
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//FIND BY ID
	@GET
	@Path("/maquinas/{id}")
	@Produces("application/json; charset=UTF-8")
	public Maquina buscaId(@PathParam("id") int id){
		Maquina maq;
		try {
			maq = service.buscar(id);
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
		return maq;
	}
	
	//CREATE
	@POST
	@Path("/maquinas")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(Maquina maquina) {
		try{
			service.save(maquina);
			return Response.status(200).entity(maquina).build();
		}
		catch (Exception e){
			return Response.status(204).entity(maquina).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/maquinas")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response update(Maquina maquina) {
		Maquina maq = new Maquina();
		try{
			maq = service.buscar(maquina.getIdMaquina());
			service.update(maquina);
			return Response.status(200).entity(maquina).build();
		}
		catch (Exception e){
			return Response.status(204).entity(maquina).build();
		}
	}
	
	//DELETE
	@DELETE
	@Path("/maquinas/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response delete(@PathParam("id") int idMaquina) {
		Maquina maq = new Maquina();
		try{
			maq = service.buscar(idMaquina);
			service.delete(maq);
			return Response.status(200).entity(maq).build();
		}
		catch (Exception e){
			return Response.status(204).entity(maq).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/maquinasFilter")
	@Produces("application/json; charset=UTF-8")
	public List<Maquina> buscaFiltro(@QueryParam("nomeMaquina") String nomeMaquina, 
			@QueryParam("idUnidade")int idUnidade) {
	
		ArrayList<Maquina> maqs = new ArrayList<Maquina>();
		maqs = service.buscarFiltro(nomeMaquina, idUnidade);
		return maqs;
	}
}
