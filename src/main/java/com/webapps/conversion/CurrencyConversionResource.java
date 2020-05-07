/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapps.conversion;

import java.util.Map;
import java.util.HashMap;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author marks
 */
@Singleton
@Path("/{currency1}/{currency2}/{amount_of_currency1}")
public class CurrencyConversionResource extends Application {

    @Context
    private UriInfo context;

    private final String EURO_SHORT = "eur";
    private final String POUND_SHORT = "gbp";
    private final String DOLLAR_SHORT = "usd";

    private final Map<String, Map<String, Float>> currencyConversionRates;

    /**
     * Creates a new instance of CurrencyConversionResource
     */
    public CurrencyConversionResource() {
        this.currencyConversionRates = intializeConversionRates(); 
    }

    private Map<String, Map<String, Float>> intializeConversionRates() {
        Map<String, Float> eurMap = new HashMap<>();
        eurMap.put(EURO_SHORT, 1f); // EUR 1 = EUR 1
        eurMap.put(POUND_SHORT, 0.88f); // EUR 1 = GBP 0.88
        eurMap.put(DOLLAR_SHORT, 1.08f); // EUR 1 = USD 1.08

        Map<String, Float> gbpMap = new HashMap<>();
        gbpMap.put(EURO_SHORT, 1.14f); // GBP 1 = EUR 1.14
        gbpMap.put(POUND_SHORT, 1f); // GBP 1 = GBP 1
        gbpMap.put(DOLLAR_SHORT, 1.23f); // GBP 1 = USD 1.23

        Map<String, Float> usdMap = new HashMap<>();
        usdMap.put(EURO_SHORT, 0.93f); // USD 1 = EUR 1.14
        usdMap.put(POUND_SHORT, 0.81f); // USD 1 = GBP 1
        usdMap.put(DOLLAR_SHORT, 1f); // USD 1 = USD 1.23

        Map<String, Map<String, Float>> ratesMap = new HashMap<>();
        ratesMap.put(EURO_SHORT, eurMap);
        ratesMap.put(POUND_SHORT, gbpMap);
        ratesMap.put(DOLLAR_SHORT, usdMap);

        return ratesMap;
    }

    private Response doGetJson(
            final String currency1, 
            final String currency2, 
            final float amount
    ) {
        if (!currencyConversionRates.containsKey(currency1.toLowerCase())) {
            throw new NotFoundException(currency1 + " is not yet a valid currency");
        }

        if (!currencyConversionRates.containsKey(currency2.toLowerCase())) {
            throw new NotFoundException(currency2 + " is not yet a valid currency");
        }

        Float rate = currencyConversionRates
                .get(currency1.toLowerCase())
                .get(currency2.toLowerCase());

        CurrencyConversion converted = new CurrencyConversion();
        converted.setCurrency(currency2);
        System.out.println(rate);
        
        converted.setAmount(rate * amount);

        return Response.ok(converted).build();
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Asynchronous
    public void getJson(
            @Suspended final AsyncResponse asyncResponse,
            @PathParam(value = "currency1") final String currency1,
            @PathParam(value = "currency2") final String currency2,
            @PathParam(value = "amount_of_currency1") final float amount
    ) {
        asyncResponse.resume(doGetJson(currency1, currency2, amount));
    }
}
