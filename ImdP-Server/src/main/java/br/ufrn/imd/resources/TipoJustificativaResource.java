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

import br.ufrn.imd.business.TipoJustificativaService;
import br.ufrn.imd.dominio.TipoJustificativa;
import br.ufrn.imd.excecoes.DadoIncompletoException;
import br.ufrn.imd.excecoes.DadoJaExisteException;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

@Stateless
@Path("/consulta")
public class TipoJustificativaResource {

	@EJB
	private TipoJustificativaService service;

	//LIST
	@GET
	@Path("/tiposJustificativa")
	@Produces("application/json; charset=UTF-8")
	public List<TipoJustificativa> listagem() {
		try {
			return service.listar();
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//FIND BY ID
	@GET
	@Path("/tiposJustificativa/{id}")
	@Produces("application/json; charset=UTF-8")
	public TipoJustificativa buscaId(@PathParam("id") int id){
		TipoJustificativa tp;
		try {
			tp = service.buscar(id);
			return tp;
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//CREATE
	@POST
	@Path("/tiposJustificativa")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(TipoJustificativa tipoJustificativa) {
		try{
			service.save(tipoJustificativa);
			return Response.status(200).entity(tipoJustificativa).build();
		}
		catch (DadoJaExisteException | DadoIncompletoException e){
			return Response.status(204).entity(tipoJustificativa).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/tiposJustificativa")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response update(TipoJustificativa tipoJustificativa) {
		TipoJustificativa tp = new TipoJustificativa();
		try{
			tp = service.buscar(tipoJustificativa.getIdTipoJustificativa());
			service.update(tp);
			return Response.status(200).entity(tp).build();
		}
		catch (DadoNaoEncontradoException | DadoIncompletoException e){
			return Response.status(204).entity(tp).build();
		}
	}
	
	//DELETE
	@DELETE
	@Path("/tiposJustificativa/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response delete(@PathParam("id") int id) {
		TipoJustificativa tp = new TipoJustificativa();
		try{
			tp = service.buscar(id);
			service.delete(tp);
			return Response.status(200).entity(tp).build();
		}
		catch (DadoNaoEncontradoException | DadoIncompletoException e){
			return Response.status(204).entity(tp).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/tiposJustificativaFilter")
	@Produces("application/json; charset=UTF-8")
	public List<TipoJustificativa> buscaFiltro(@QueryParam(value = "nomeJustificativa") String nomeTipoJustificativa){
		
		ArrayList<TipoJustificativa> tps = new ArrayList<TipoJustificativa>();
		tps = service.buscarFiltro(nomeTipoJustificativa);
		return tps;
	}
}
