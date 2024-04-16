package rest;

import java.util.List;

import excp.NarudzbaException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import model.Narudzba;
import service.NarudzbaService;

@Path("/api/narudzba")
public class NarudzbaRest {

	@Inject
	private NarudzbaService narudzbaService;

	@POST
	@Path("/addNarudzba")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNarudzba(Narudzba narudzba) {
		try {
			Narudzba createdNarudzba = narudzbaService.createNarudzba(narudzba);
			return Response.ok().entity(createdNarudzba).build();
		} catch (NarudzbaException e) {
			return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/getAllNarudzba")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllNarudzba() {
		List<Narudzba> narudzbe = narudzbaService.getAllNarudzba();
		return Response.ok().entity(narudzbe).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNarudzbaById(@PathParam("id") Long id) {
		Narudzba narudzba = narudzbaService.getNarudzbaById(id);
		if (narudzba == null) {
			return Response.status(Status.NOT_FOUND).entity("Narudzba not found").build();
		}
		return Response.ok().entity(narudzba).build();
	}

	@PUT
	@Path("/updateNarudzba")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateNarudzba(Narudzba narudzba) {
		try {
			Narudzba updatedNarudzba = narudzbaService.updateNarudzba(narudzba);
			return Response.ok().entity(updatedNarudzba).build();
		} catch (NarudzbaException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/deleteNarudzba/{id}")
	public Response deleteNarudzba(@PathParam("id") Long id) {
		try {
			narudzbaService.deleteNarudzba(id);
			return Response.ok().entity("Narudzba deleted").build();
		} catch (NarudzbaException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}
}
