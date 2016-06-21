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

import br.ufrn.imd.business.VinculoService;
import br.ufrn.imd.dominio.Vinculo;
import br.ufrn.imd.excecoes.DadoIncompletoException;
import br.ufrn.imd.excecoes.DadoJaExisteException;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

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
		try {
			return service.listar();
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//FIND BY ID
	@GET
	@Path("/vinculos/{id}")
	@Produces("application/json; charset=UTF-8")
	public Vinculo buscaId(@PathParam("id") int id){
		Vinculo user;
		try {
			user = service.buscar(id);
			return user;
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	//CREATE
	@POST
	@Path("/vinculos")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(Vinculo vinculo) {
		try{
			service.save(vinculo);
			return Response.status(200).entity(vinculo).build();
		}
		catch (DadoJaExisteException | DadoIncompletoException e){
			return Response.status(204).entity(vinculo).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/vinculos")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response update(Vinculo vinculo) {
		try{
			Vinculo vinc = service.buscar(vinculo.getIdVinculo());
			service.update(vinculo);
			return Response.status(200).entity(vinculo).build();
		}
		catch (DadoIncompletoException | DadoNaoEncontradoException e){
			return Response.status(204).entity(vinculo).build();
		}
	}
	
	//DELETE
	@DELETE
	@Path("/vinculos/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response delete(@PathParam("id") int id) {
		Vinculo vinculo = new Vinculo();
		try{
			vinculo = service.buscar(id);
			service.delete(vinculo);
			return Response.status(200).entity(vinculo).build();
		}
		catch (DadoNaoEncontradoException | DadoIncompletoException e){
			return Response.status(204).entity(vinculo).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/vinculosFilter")
	@Produces("application/json; charset=UTF-8")
	public List<Vinculo> buscaFiltro(@DefaultValue("") @QueryParam("nomeUsuario") String nomeUsuario, 
			@QueryParam("idUnidade")int idUnidade, @QueryParam("idSetor")int idSetor) {
		
		ArrayList<Vinculo> users = new ArrayList<Vinculo>();
		users = service.buscarFiltro(nomeUsuario, idUnidade, idSetor);
		return users;
	}
	
	//FIND BY ID_USUARIO
	@GET
	@Path("/vinculos/usuarios/{idUsuario}")
	@Produces("application/json; charset=UTF-8")
	public ArrayList<Vinculo> buscaVinculosUsuario(@PathParam("idUsuario") int id){
		ArrayList<Vinculo> vincs = new ArrayList<Vinculo>();
		vincs = service.buscarVinculosUsuario(id);
		return vincs;
	}
}
