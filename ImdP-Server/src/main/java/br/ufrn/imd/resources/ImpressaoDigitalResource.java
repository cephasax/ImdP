package br.ufrn.imd.resources;

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
import javax.ws.rs.core.Response;

import br.ufrn.imd.business.ImpressaoDigitalService;
import br.ufrn.imd.dominio.ImpressaoDigital;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

@Stateless
@Path("/consulta")
public class ImpressaoDigitalResource {

	@EJB
	private ImpressaoDigitalService service;
	
	//FIND BY ID USUARIO
	@GET
	@Path("/digitais/usuarios/{id}")
	@Produces("application/json; charset=UTF-8")
	public List<ImpressaoDigital> buscaIdUsuario(@PathParam("id") int id){
		try {
			return service.buscarPorUsuario(id);
		} 
		catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//CREATE
	@POST
	@Path("/digitais")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(ImpressaoDigital digital) {
		try{
			service.save(digital);
			return Response.status(200).entity(digital).build();
		}
		catch (Exception e){
			return Response.status(409).entity(digital).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/digitais")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response update(ImpressaoDigital digital) {
		try{
			ImpressaoDigital c = service.buscar(digital.getIdImpressaoDigital());
			service.update(digital);
			return Response.status(200).entity(digital).build();
		}
		catch (Exception e){
			return Response.status(204).entity(digital).build();
		}
	}
	
	//DELETE
	@DELETE
	@Path("/digitals/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response delete(@PathParam("id") int id) {
		ImpressaoDigital digital = new ImpressaoDigital();
		try{
			digital = service.buscar(id);
			service.delete(digital);
			return Response.status(200).entity(digital).build();
		}
		catch (Exception e){
			return Response.status(204).entity(digital).build();
		}
	}

}
