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

import br.ufrn.imd.business.UsuarioService;
import br.ufrn.imd.dominio.Usuario;
import br.ufrn.imd.excecoes.DadoIncompletoException;
import br.ufrn.imd.excecoes.DadoJaExisteException;
import br.ufrn.imd.excecoes.DadoNaoEncontradoException;

@Stateless
@Path("/consulta")
public class UsuarioResource {

	@EJB
	private UsuarioService service;

	//LIST
	@GET
	@Path("/usuarios")
	@Produces("application/json; charset=UTF-8")
	public List<Usuario> listagem() {
		try {
			return service.listar();
		} catch (DadoNaoEncontradoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//FIND BY ID
	@GET
	@Path("/usuarios/{id}")
	@Produces("application/json; charset=UTF-8")
	public Usuario buscaId(@PathParam("id") int id){
		Usuario user;
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
	@Path("/usuarios")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response novo(Usuario usuario) {
		Usuario user = new Usuario();
		try{
			service.save(usuario);
			return Response.status(200).entity(usuario).build();
		}
		catch (DadoJaExisteException | DadoIncompletoException e){
			return Response.status(204).entity(usuario).build();
		}
	}
	
	//UPDATE
	@PUT
	@Path("/usuarios")
	@Consumes("application/json")
	@Produces("application/json; charset=UTF-8")
	public Response update(Usuario usuario) {
		Usuario user = new Usuario();
		try{
			user = service.buscar(usuario.getIdUsuario());
			service.update(usuario);
			return Response.status(200).entity(usuario).build();
		}
		catch (DadoNaoEncontradoException | DadoIncompletoException e){
			return Response.status(204).entity(usuario).build();
		}
	}
	
	//DELETE
	@DELETE
	@Path("/usuarios/{id}")
	@Produces("application/json; charset=UTF-8")
	public Response delete(@PathParam("id") int id) {
		Usuario user = new Usuario();
		try{
			user = service.buscar(id);
			service.delete(user);
			return Response.status(200).entity(user).build();
		}
		catch (DadoNaoEncontradoException | DadoIncompletoException e){
			return Response.status(204).entity(user).build();
		}
	}

	//FIND FILTRO
	@GET
	@Path("/usuariosFilter")
	@Produces("application/json; charset=UTF-8")
	public List<Usuario> buscaFiltro(@QueryParam("nomeUsuario") String nomeUsuario, 
			@QueryParam("idUnidade")int idUnidade, @QueryParam("idSetor")int idSetor) {
		
		ArrayList<Usuario> users = new ArrayList<Usuario>();
		users = service.buscarFiltro(nomeUsuario, idUnidade, idSetor);
		return users;
	}
}
