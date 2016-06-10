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

import br.ufrn.imd.business.JustificativaFaltaService;
import br.ufrn.imd.dominio.JustificativaFalta;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

@Stateless
@Path("/consulta")
public class JustificativaFaltaResource {

	@EJB
	private JustificativaFaltaService service;

	//LIST
	@GET
	@Path("/justificativasFalta")
	@Produces("application/json; charset=UTF-8")
	public List<JustificativaFalta> listagem() {
		try {
			return service.listar();
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//FIND BY ID
	@GET
	@Path("/justificativasFalta/{id}")
	@Produces("application/json; charset=UTF-8")
	public JustificativaFalta buscaId(@PathParam("id") int id){
		JustificativaFalta jf = new JustificativaFalta();
		try {
			jf = service.buscar(id);
			return jf;
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//CREATE
	@POST
	@Path("/justificativasFalta")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(JustificativaFalta justificativaFalta) {
		
		try{
			service.save(justificativaFalta);
			return Response.status(200).entity(justificativaFalta).build();
		}
		catch (Exception e){
			return Response.status(204).entity(justificativaFalta).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/justificativasFalta")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response update(JustificativaFalta jf) {
		try{
			JustificativaFalta justf = service.buscar(jf.getIdJustificativaFalta());
			service.update(jf);
			return Response.status(200).entity(jf).build();
		}
		catch (Exception e){
			return Response.status(204).entity(jf).build();
		}
	}
	
	//DELETE
	@DELETE
	@Path("/justificativasFalta/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response delete(@PathParam("id") int id) {
		JustificativaFalta jf = new JustificativaFalta();
		try{
			jf = service.buscar(id);
			service.delete(jf);
			return Response.status(200).entity(jf).build();
		}
		catch (Exception e){
			return Response.status(204).entity(jf).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/justificativasFaltaFilter")
	@Produces("application/json; charset=UTF-8")
	public List<JustificativaFalta> buscaFiltro(@QueryParam("nomeUsuario") String nomeUsuario, 
			@QueryParam("idUnidade")int idUnidade, @QueryParam("idSetor")int idSetor) {
		
		ArrayList<JustificativaFalta> jfs = new ArrayList<JustificativaFalta>();
		jfs = service.buscarFiltro(nomeUsuario, idUnidade, idSetor);
		return jfs;
	}
}
