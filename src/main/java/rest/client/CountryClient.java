//package rest.client;
//
//import java.util.List;
//
//import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
//
//import jakarta.ws.rs.GET;
//import jakarta.ws.rs.Path;
//import model.Country;
//
//@Path("/api/v3")
//@RegisterRestClient
//public interface CountryClient {
//
//	@GET
//	@Path("/AvailableCountries")
//    List<Country> getAll();
//	
//}