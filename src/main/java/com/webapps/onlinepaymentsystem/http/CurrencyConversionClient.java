/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.onlinepaymentsystem.http;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:CurrencyConversionResource
 * [/{currency1}/{currency2}/{amount_of_currency1}]<br>
 * USAGE:
 * <pre>
 *        CurrencyConversionClient client = new CurrencyConversionClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author marks
 */
public class CurrencyConversionClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "conversion";

    public CurrencyConversionClient(String currency1, String currency2, String amount_of_currency1) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        String resourcePath = java.text.MessageFormat.format("{0}/{1}/{2}", new Object[]{currency1, currency2, amount_of_currency1});
        webTarget = client.target(BASE_URI).path(resourcePath);
    }

    public void setResourcePath(String currency1, String currency2, String amount_of_currency1) {
        String resourcePath = java.text.MessageFormat.format("{0}/{1}/{2}", new Object[]{currency1, currency2, amount_of_currency1});
        webTarget = client.target(BASE_URI).path(resourcePath);
    }

    public <T> T getJson_XML(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getJson_JSON(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
