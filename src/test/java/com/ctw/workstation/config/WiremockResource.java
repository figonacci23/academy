package com.ctw.workstation.config;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import jakarta.ws.rs.core.Response;
import org.codehaus.plexus.component.configurator.converters.basic.UrlConverter;
import org.jboss.logging.Logger;

import java.util.Map;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;


public class WiremockResource implements QuarkusTestResourceLifecycleManager {

    WireMockServer wireMockServer;
    @Override
    public Map<String, String> start(){
        wireMockServer = new WireMockServer(3001);
        wireMockServer.start();
        String asd = "/external/hello";
        wireMockServer.stubFor(get(urlEqualTo(asd)).willReturn(aResponse().withBody("Hello figo!")));
        wireMockServer.stubFor(post(urlEqualTo(asd)).willReturn(aResponse().withBody("Hello figo!")));

        return Map.of("external-api.url", "http://localhost:3001");
    }

    public void stop() {
        wireMockServer.stop();
    }
}
