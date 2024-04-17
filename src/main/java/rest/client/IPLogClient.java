package rest.client;


import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import model.IPLog;

@Path("/data")
@RegisterRestClient(configKey = "rest.client.IPLog")
public interface IPLogClient {

	@GET
	@Path("/client-ip")
	IPLog getIpLog();
}
