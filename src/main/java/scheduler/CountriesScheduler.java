//package scheduler;
//
//import java.util.List;
//
//import org.eclipse.microprofile.rest.client.inject.RestClient;
//
//import io.quarkus.scheduler.Scheduled;
//import jakarta.inject.Inject;
//import model.Country;
//import service.CountryService;
//import rest.client.CountryClient;
//
//public class CountriesScheduler {
//
//	@Inject
//	@RestClient
//	private CountryClient countryClient;
//	
//	@Inject
//	private CountryService countryService;
//	
//	@Scheduled(every="10s")
//	public void getAllCountries() {
//		List<Country> countries = countryClient.getAll();
//		countryService.saveCountries(countries);
//		
//	}
//}
