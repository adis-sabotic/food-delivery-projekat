package rest;

import java.util.List;

import excp.KorisnikException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import model.Korisnik;
import service.KorisnikService;

@Path("/api/korisnik")
public class KorisnikRest {
	
	@Inject
	private KorisnikService korisnikService;
	
	@POST
	@Path("/addKorisnik")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addKorisnik(Korisnik korisnik){
		Korisnik k;
		try {
			k = korisnikService.createKorisnik(korisnik);
		} catch (KorisnikException e) {
			return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
		}
		return Response.ok().entity(k).build();
	}
	
	@GET
	@Path("/getAllKorisnik")
	@jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public Response getAllKorisnik(Korisnik korisnik) {
		List<Korisnik> korisnici = korisnikService.getAllKorisnik();
		return Response.ok().entity(korisnici).build();
	}
}
