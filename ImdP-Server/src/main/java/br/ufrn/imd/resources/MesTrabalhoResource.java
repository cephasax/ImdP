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

import br.ufrn.imd.business.MesTrabalhoService;
import br.ufrn.imd.dominio.MesTrabalho;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

@Stateless
@Path("/consulta")
public class MesTrabalhoResource {

	@EJB
	private MesTrabalhoService service;

	//LIST
	@GET
	@Path("/mesesTrabalho")
	@Produces("application/json; charset=UTF-8")
	public List<MesTrabalho> listagem() {
		try {
			return service.listar();
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//FIND BY ID
	@GET
	@Path("/mesesTrabalho/{id}")
	@Produces("application/json; charset=UTF-8")
	public MesTrabalho buscaId(@PathParam("id") int id){
		MesTrabalho mt;
		try {
			mt = service.buscar(id);
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
		return mt;
	}
	
	//CREATE
	@POST
	@Path("/mesesTrabalho")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(MesTrabalho mesTrabalho) {
		try{
			service.save(mesTrabalho);
			return Response.status(200).entity(mesTrabalho).build();
		}
		catch (Exception e){
			return Response.status(204).entity(mesTrabalho).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/mesesTrabalho")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response update(MesTrabalho mesTrabalho) {
		try{
			MesTrabalho mt = service.buscar(mesTrabalho.getIdMesTrabalho());
			service.update(mesTrabalho);
			return Response.status(200).entity(mesTrabalho).build();
		}
		catch (Exception e){
			return Response.status(204).entity(mesTrabalho).build();
		}
	}
	
	//DELETE
	@DELETE
	@Path("/mesesTrabalho/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response delete(@PathParam("id") int id) {
		MesTrabalho mt = new MesTrabalho();
		try{
			mt = service.buscar(id);
			service.delete(mt);
			return Response.status(200).entity(mt).build();
		}
		catch (Exception e){
			return Response.status(204).entity(mt).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/mesesTrabalhoFilter")
	@Produces("application/json; charset=UTF-8")
	public List<MesTrabalho> buscaFiltro(
			@DefaultValue("0")@QueryParam("ano")int ano,
			@DefaultValue("0")@QueryParam("numeroMes") int numeroMes) {
		
		ArrayList<MesTrabalho> mts = new ArrayList<MesTrabalho>();
		mts = service.buscarFiltro(ano, numeroMes);
		return mts;
	}
}
