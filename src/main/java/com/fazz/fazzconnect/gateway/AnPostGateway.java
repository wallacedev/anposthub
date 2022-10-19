package com.fazz.fazzconnect.gateway;

import com.fazz.fazzconnect.gateway.model.Address;
import com.fazz.fazzconnect.gateway.model.Consignment;
import com.fazz.fazzconnect.gateway.model.Package;
import com.fazz.fazzconnect.gateway.model.Item;
import com.fazz.fazzconnect.gateway.model.Recipient;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

    public String sendConsignments(List<Consignment> consignments) {
        var url = baseUrl + "consignments";
        log.info("Sending consignment");
        log.info("url: {}", url);
        log.info("consignments: {}", consignments);
        var exchange = restTemplate
                        .exchange(url, POST, getAuthenticatedHeaderWithBody(consignments), String.class);

        return exchange.getBody();
    }

    public Consignment getConsignment(final String id) {
        var url = baseUrl + "consignment/" + id;

        var exchange = restTemplate
                .exchange(url, GET, getAuthenticatedHeader(), Consignment.class);

        return exchange.getBody();
    }
}
