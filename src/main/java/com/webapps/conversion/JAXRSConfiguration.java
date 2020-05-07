package com.webapps.conversion;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures JAX-RS for the application.
 */
@ApplicationPath("/conversion")
public class JAXRSConfiguration extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        //register resource
        classes.add(CurrencyConversionResource.class);
        return classes;
    }
}
