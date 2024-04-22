package edu.illinois.library.cantaloupe.resource.api;

import com.fasterxml.jackson.databind.SerializationFeature;
import edu.illinois.library.cantaloupe.config.Configuration;
import edu.illinois.library.cantaloupe.config.Key;
import edu.illinois.library.cantaloupe.http.Method;
import edu.illinois.library.cantaloupe.resource.JacksonRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.nio.charset.StandardCharsets;

/**
 * Disallows web crawlers via the HTTP API.
 */
public class RobotsResource extends AbstractAPIResource {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(HealthResource.class);

    private static final Method[] SUPPORTED_METHODS =
            new Method[] { Method.GET, Method.OPTIONS };

    private static final Map<SerializationFeature, Boolean> SERIALIZATION_FEATURES =
            Map.of(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @Override
    public Method[] getSupportedMethods() {
        return SUPPORTED_METHODS;
    }

    @Override
    public void doGET() throws IOException {
        // Serve robots.txt content
        String content = "User-agent: *\nDisallow: /";
        getResponse().setContentType("text/plain;charset=UTF-8");
        getResponse().getOutputStream().write(content.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    boolean requiresAuth() {
        return false;
    }

}
