package rest.server;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import model.Dostavljac;
import service.DostavljacService;

@Path("/api/dostavljac")
public class DostavljacRest {

    @Inject
    private DostavljacService dostavljacService;

    @POST
    @Path("/addDostavljac")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDostavljac(Dostavljac dostavljac) {
        Dostavljac d;
        try {
            d = dostavljacService.createDostavljac(dostavljac);
        } catch (Exception e) {
            return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(d).build();
    }

    @GET
    @Path("/getAllDostavljac")
    @jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
    public Response getAllDostavljac() {
        List<Dostavljac> dostavljaci = dostavljacService.getAllDostavljac();
        return Response.ok().entity(dostavljaci).build();
    }

    @GET
    @Path("/{id}")
    @jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
    public Response getDostavljacById(@PathParam("id") Long id) {
        Dostavljac dostavljac = dostavljacService.getDostavljacById(id);
        if (dostavljac == null) {
            return Response.status(Status.NOT_FOUND).entity("Dostavljac not found").build();
        }
        return Response.ok().entity(dostavljac).build();
    }

    @PUT
    @Path("/updateDostavljac")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDostavljac(Dostavljac dostavljac) {
        try {
            Dostavljac updatedDostavljac = dostavljacService.updateDostavljac(dostavljac);
            return Response.ok().entity(updatedDostavljac).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/deleteDostavljac/{id}")
    public Response deleteDostavljac(@PathParam("id") Long id) {
        try {
            dostavljacService.deleteDostavljac(id);
            return Response.ok().entity("Dostavljac deleted").build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
