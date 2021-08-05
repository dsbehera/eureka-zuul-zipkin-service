package com.dev.libraryservice.config;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.PUT;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@Component
public class ClientService {

    private RestTemplate restTemplate;
    private EurekaClient eurekaClient;

    @Value("#{${client.server.config.details}}")
    private Map<String, String> clientConfig;

    @Autowired
    public ClientService(RestTemplate restTemplate, EurekaClient eurekaClient) {
        this.restTemplate = restTemplate;
        this.eurekaClient = eurekaClient;
    }

    public Object sendRequest(HttpServletRequest request) throws Exception {
        URI uri = this.createURI(request);
        RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());

        switch (requestMethod) {
            case GET:
                return restTemplate.getForEntity(uri, Object.class);
            case DELETE:
                return restTemplate.exchange(uri, DELETE, null, Object.class);
        }
        return HttpStatus.NOT_FOUND;
    }

    public Object sendRequest(HttpServletRequest request, Map requestBody) throws Exception {
        URI uri = this.createURI(request);
        RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());

        switch (requestMethod) {
            case POST:
                return restTemplate.postForEntity(uri, requestBody, Object.class);
            case PUT:
                return restTemplate.exchange(uri, PUT, new HttpEntity<>(requestBody), Object.class);
        }
        return HttpStatus.NOT_FOUND;
    }

    private URI createURI(HttpServletRequest request) throws URISyntaxException {
        return new URI(this.findBaseURL(request));
    }


    private String findBaseURL(HttpServletRequest request) throws URISyntaxException {
        String requestURI = request.getRequestURI();

        String serviceHost = clientConfig.entrySet()
                .stream()
                .filter(e -> requestURI.startsWith(e.getKey()))
                .map(Map.Entry::getValue)
                .map(this::getServiceUrl)
                .findFirst()
                .orElseThrow(() -> new URISyntaxException(request.getRequestURL().toString(), "Can not verify URL"));

        return serviceHost + requestURI.replaceFirst("/","");
    }

    private String getServiceUrl(String serviceName) {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka(serviceName, false);
        return instance.getHomePageUrl();
    }
}
