package ru.dolgov.tourservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dolgov.tourservice.gisservice.HttpClient.HttpClient;
import ru.dolgov.tourservice.gisservice.HttpClient.HttpClientImpl;
import ru.dolgov.tourservice.gisservice.api2gis.Api2gis;
import ru.dolgov.tourservice.gisservice.api2gis.Api2gisImpl;
import ru.dolgov.tourservice.gisservice.jsonparser.JsonParser;
import ru.dolgov.tourservice.gisservice.jsonparser.JsonParserImpl;

/**
 * @author M. Dolgov
 *         10.06.2017.
 */
@Configuration
public class ContextConfiguration {

    @Bean
    public JsonParser jsonParser() {
        return new JsonParserImpl();
    }

    @Bean
    public Api2gis api2gis() {
        return new Api2gisImpl(myHttpClient(), jsonParser());
    }

    @Bean
    public HttpClient myHttpClient() {
        return new HttpClientImpl();
    }

//    @Bean
//    public CloseableHttpClient closeableHttpClient() {
//        return HttpClientBuilder.create().build();
//    }


}
