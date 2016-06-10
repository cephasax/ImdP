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

import br.ufrn.imd.business.PontoService;
import br.ufrn.imd.dominio.Ponto;
import br.ufrn.imd.excecoes.DadoIncompletoException;
import br.ufrn.imd.excecoes.DadoJaExisteException;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

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
		try {
			return service.listar();
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//FIND BY ID
	@GET
	@Path("/pontos/{id}")
	@Produces("application/json; charset=UTF-8")
	public Ponto buscaId(@PathParam("id") int id){
		Ponto dot;
		try {
			dot = service.buscar(id);
			return dot;
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//CREATE
	@POST
	@Path("/pontos")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(Ponto ponto) {
			try {
				service.save(ponto);
				return Response.status(200).entity(ponto).build();
			} 
			catch (DadoJaExisteException | DadoIncompletoException e) {
				e.printStackTrace();
				return Response.status(204).entity(ponto).build();
			}	
	}
	
	//UPDATE
	@PUT
	@Path("/pontos")
	@Produces("application/json; charset=UTF-8")
	public Response update(Ponto ponto) {
		Ponto dot = new Ponto();
		try{
			dot = service.buscar(ponto.getIdPonto());
			service.update(dot);
			return Response.status(200).entity(dot).build();
		}
		catch (DadoNaoEncontradoException | DadoIncompletoException e){
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
		catch (DadoNaoEncontradoException | DadoIncompletoException e){
			return Response.status(204).entity(dot).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/pontosFilter")
	@Produces("application/json; charset=UTF-8")
	public List<Ponto> buscaFiltro(@QueryParam("nomePonto") String nomePonto, 
			@QueryParam("idUnidade")int idUnidade, @QueryParam("idSetor")int idSetor) {
		
		ArrayList<Ponto> dots = new ArrayList<Ponto>();
		dots = service.buscarFiltro(nomePonto, idUnidade, idSetor);
		return dots;
	}
	
	//FIND PONTOS USUARIO
	@GET
	@Path("/pontos/usuarios/{idUsuario}")
	@Produces("application/json; charset=UTF-8")
	public List<Ponto> buscaPontoUsuario(@PathParam("idUsuario") int idUsuario) {
		
		ArrayList<Ponto> dots = new ArrayList<Ponto>();
		dots = service.buscarPontosUsuario(idUsuario);
		return dots;
	}
	//FIND PONTOS VINCULO
	@GET
	@Path("/pontos/vinculos/{idVinculo}")
	@Produces("application/json; charset=UTF-8")
	public List<Ponto> buscaPontoVinculo(@PathParam("idVinculo") int idVinculo) {
		ArrayList<Ponto> dots = new ArrayList<Ponto>();
		dots = service.buscarPontosVinculo(idVinculo);
		return dots;
	}
}
