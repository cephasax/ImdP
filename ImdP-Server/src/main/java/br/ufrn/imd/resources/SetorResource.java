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

import br.ufrn.imd.business.SetorService;
import br.ufrn.imd.dominio.Setor;
import br.ufrn.imd.excecoes.DadoIncompletoException;
import br.ufrn.imd.excecoes.DadoJaExisteException;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

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
		try {
			return service.listar();
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//FIND BY ID
	@GET
	@Path("/setores/{id}")
	@Produces("application/json; charset=UTF-8")
	public Setor buscaId(@PathParam("id") int id){
		Setor place = new Setor();
		try {
			place = service.buscar(id);
			return place;
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//CREATE
	@POST
	@Path("/setores")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(Setor setor) {
		try{
			service.save(setor);
			return Response.status(200).entity(setor).build();
		}
		catch (DadoJaExisteException | DadoIncompletoException e){
			return Response.status(204).entity(setor).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/setores")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response update(Setor setor) {
		try{
			Setor place = service.buscar(setor.getIdSetor());
			service.update(setor);
			return Response.status(200).entity(setor).build();
		}
		catch (DadoNaoEncontradoException | DadoIncompletoException e){
			return Response.status(204).entity(setor).build();
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
		catch (DadoNaoEncontradoException | DadoIncompletoException e){
			return Response.status(204).entity(place).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/setoresFilter")
	@Produces("application/json; charset=UTF-8")
	public List<Setor> buscaFiltro(@DefaultValue("")@QueryParam("nomeSetor") String nomeSetor, 
			@QueryParam("idUnidade")int idUnidade) {
		
		ArrayList<Setor> places = new ArrayList<Setor>();
		places = service.buscarFiltro(nomeSetor, idUnidade);
		return places;
	}
}
