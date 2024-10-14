package server.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import server.soap.RouterSoap;
import soap.countries.Country;
import soap.countries.GetCountryRequest;
import soap.countries.GetCountryResponse;

@Endpoint
public class CountryEndpoint {

	@PayloadRoot(namespace = RouterSoap.NAMESPACE_COUNTRY, localPart = "GetCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {

		Country country = new Country();
		
		country.setName(request.getName());
		country.setCapital("Buenos Aires");
		country.setPopulation(45000000);

		GetCountryResponse response = new GetCountryResponse();
		
		response.setCountry(country);

		return response;
	}
}
