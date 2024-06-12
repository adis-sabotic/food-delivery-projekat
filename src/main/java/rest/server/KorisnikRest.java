package rest.server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import excp.KorisnikException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import model.IPLog;
import model.Korisnik;
import rest.client.IPLogClient;
import service.KorisnikService;
import service.MultipartRequest;


@Path("/api/korisnik")
public class KorisnikRest {

	@RestClient
	@Inject
	IPLogClient iplogclient;

	@Inject
	private KorisnikService korisnikService;
	
	@POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/addKorisnik")
    public Response addKorisnik(MultipartRequest multipartRequest){
        Korisnik korisnik = multipartRequest.getKorisnik();
        FileUpload file = (FileUpload) multipartRequest.getFile();

        String directory = "C:\\Users\\Administrator\\Desktop\\img quarkus\\";
        java.nio.file.Path newFilePath = Paths.get(directory + file.fileName());
        try {
        	Files.move(file.uploadedFile(), (java.nio.file.Path) newFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            return Response.serverError().entity("Error: " + e.getMessage()).build();
        }
        korisnik.setFileName(newFilePath.toString());
        Korisnik k = null;
        try {
            IPLog i = iplogclient.getIpLog();
            k = korisnikService.createKorisnik(korisnik, i);

        } catch (KorisnikException e) {
            return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(k).build();
    }
	
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Path("/getKorisnikByName")
	public Response getKorisnikByName(@QueryParam(value = "name") String name) {
	    List<Korisnik> korisnici = korisnikService.getKorisnikByName(name);
	    if (korisnici.isEmpty()) {
	        return Response.status(Status.NOT_FOUND).entity("No entries.").build();
	    }
	    Korisnik korisnik = korisnici.get(0);
	    java.nio.file.Path filePath = Paths.get(korisnik.getFileName());
	    byte[] fileContent;
	    try {
	        fileContent = Files.readAllBytes(filePath);
	    } catch (IOException e) {
	        return Response.serverError().entity("Error: " + e.getMessage()).build();
	    }
	    String fileName = filePath.getFileName().toString();
	    return Response.ok(fileContent)
	            .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
	            .build();
	}

	@GET
	@jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllKorisnik")
	public Response getAllKorisnik() {
		List<Korisnik> korisnici = korisnikService.getAllKorisnik();
		return Response.ok().entity(korisnici).build();
	}

	@GET
	@Path("/{id}")
	@jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public Response getKorisnikById(@PathParam("id") Long id) {
		Korisnik korisnik = korisnikService.getKorisnikById(id);
		if (korisnik == null) {
			return Response.status(Status.NOT_FOUND).entity("Korisnik not found").build();
		}
		return Response.ok().entity(korisnik).build();
	}

	@PUT
	@Path("/updateKorisnik")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateKorisnik(Korisnik korisnik) {
		try {
			Korisnik updatedKorisnik = korisnikService.updateKorisnik(korisnik);
			return Response.ok().entity(updatedKorisnik).build();
		} catch (KorisnikException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/deleteKorisnik/{id}")
	public Response deleteKorisnik(@PathParam("id") Long id) {
		try {
			korisnikService.deleteKorisnik(id);
			return Response.ok().entity("Korisnik deleted").build();
		} catch (KorisnikException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

}
