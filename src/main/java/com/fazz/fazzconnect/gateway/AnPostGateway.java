package com.fazz.fazzconnect.gateway;

import com.fazz.fazzconnect.gateway.model.Address;
import com.fazz.fazzconnect.gateway.model.Consignment;
import com.fazz.fazzconnect.gateway.model.Package;
import com.fazz.fazzconnect.gateway.model.Item;
import com.fazz.fazzconnect.gateway.model.Recipient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Component
public class AnPostGateway {

    private final String baseUrl;
    private final RestTemplate restTemplate;

    public  AnPostGateway() {
        restTemplate = new RestTemplate();
        baseUrl = "https://sandbox.scurri.co.uk/api/v1/company/fazz-online/";
    }

    public HttpEntity<String> getAuthenticatedHeader (AnPostUser anPostUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(anPostUser.getUsername(), anPostUser.getPassword());

        return new HttpEntity<>(headers);
    }

    public <T> HttpEntity<T>  getAuthenticatedHeaderWithBody (AnPostUser anPostUser, T body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(anPostUser.getUsername(), anPostUser.getPassword());

        return new HttpEntity<>(body,headers);
    }

    public HttpEntity<String> getAuthenticatedHeader () {
        return getAuthenticatedHeader(AnPostUser.builder().username("test@fazzonline").password("sUl6qOH13^8i").build());
    }

    public <T> HttpEntity<T> getAuthenticatedHeaderWithBody (T body) {
        return getAuthenticatedHeaderWithBody(AnPostUser.builder()
                .username("test@fazzonline")
                .password("sUl6qOH13^8i")
                .build(),
            body);
    }

    public String getCarriers() {
        var url = baseUrl + "carriers";

        var exchange = restTemplate
                .exchange(url, GET, getAuthenticatedHeader(), String.class);

        return exchange.getBody();
    }

    public String sendConsignments(ArrayList<Consignment> consignments) {
        var url = baseUrl + "consignments";

        var exchange = restTemplate
                .exchange(url, POST, getAuthenticatedHeaderWithBody(consignments), String.class);

        return exchange.getBody();
    }

    public String sendShipment(List<Consignment> consignment) {
        var url = baseUrl + "consignments";
        var exchange = restTemplate
                        .exchange(url, POST, getAuthenticatedHeaderWithBody(consignment), String.class);

        return exchange.getBody();
    }

    public String sendShipment() {
        var url = baseUrl + "consignments";

//        var consignment = new Consignment(
//              "batch", "orderId", "2020-02-20T11:48:03+0000", "carrier",
//                "An Post|Express International Packet",
//                "buyerName", "shipAddress1", "shipAddress2", "shipCity",
//                "shipState", "shipPostalCode", "shipCountry", "buyerEmail",
//                "buyerPhoneNumber",
//                Arrays.asList(new Package(
//                        "reference", 12.2, 0.5, 10, 1,
//                        Arrays.asList(new Item("sku", "name", 1, "tariff_code",
//                                "IE", "harmonisation_code",
//                                "fabric_content", 10, 2)
//                        )
//                )),
//                new Recipient("name", "first_name", "last_name", "company_name",
//                       "email_address@rmail.com", "contact_number", "tax_identifier",
//                        "eori_number",
//                        new Address("address1", "address2", "address3", "city", "state",
//                                "SW1H 8ZW", "GB", "store_code")
//                        )
//        );
//
//        var exchange = restTemplate
//                .exchange(url, POST, getAuthenticatedHeaderWithBody(Arrays.asList(consignment)), String.class);
//
//        return exchange.getBody();
        return null;
    }
}
