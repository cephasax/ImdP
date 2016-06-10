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

import br.ufrn.imd.business.UnidadeService;
import br.ufrn.imd.dominio.Unidade;
import br.ufrn.imd.excecoes.DadoIncompletoException;
import br.ufrn.imd.excecoes.DadoJaExisteException;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

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
		try {
			return service.listar();
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//FIND BY ID
	@GET
	@Path("/unidades/{id}")
	@Produces("application/json; charset=UTF-8")
	public Unidade buscaId(@PathParam("id") int id){
		Unidade und = new Unidade();
		try {
			und = service.buscar(id);
			return und;

		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
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
			return Response.status(200).entity(unidade).build();
		}
		catch (DadoJaExisteException | DadoIncompletoException e){
			return Response.status(204).entity(unidade).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/unidades")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response update(Unidade unidade) {
		Unidade und = new Unidade();
		try{
			und = service.buscar(unidade.getIdUnidade());
			service.update(unidade);
			return Response.status(200).entity(unidade).build();
		}
		catch (DadoNaoEncontradoException | DadoIncompletoException e){
			return Response.status(204).entity(unidade).build();
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
		catch (DadoNaoEncontradoException | DadoIncompletoException e){
			return Response.status(204).entity(und).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/unidadesFilter")
	@Produces("application/json; charset=UTF-8")
	public List<Unidade> buscaFiltro(@QueryParam("nomeUnidade") String nomeUnidade) {
		
		ArrayList<Unidade> unds = new ArrayList<Unidade>();
		unds = service.buscarFiltro(nomeUnidade);
		return unds;
	}
}
