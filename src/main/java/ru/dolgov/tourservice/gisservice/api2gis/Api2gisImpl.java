package ru.dolgov.tourservice.gisservice.api2gis;

import ru.dolgov.tourservice.firm.Firm;
import ru.dolgov.tourservice.gisservice.HttpClient.HttpClient;
import ru.dolgov.tourservice.gisservice.HttpClient.HttpClientImpl;
import ru.dolgov.tourservice.gisservice.jsonparser.JsonParser;
import ru.dolgov.tourservice.gisservice.jsonparser.JsonParserImpl;

import java.io.IOException;
import java.util.List;

/**
 * @author M. Dolgov
 *         08.06.2017.
 */
public class Api2gisImpl implements Api2gis {

    @Override
    public List<Firm> getFirm(String trend, String location) throws IOException {
        HttpClient client = new HttpClientImpl();
        String jsonFirms = client.getJsonFromUrl(createURL(trend, location));
        JsonParser parser = new JsonParserImpl();
        List<Firm> list = parser.parseFirms(jsonFirms);
        return list;
    }

    private String createURL(String trend, String location) {
        String url = "http://catalog.api.2gis.ru/search?what=" + trend + "&where=" + location +"&version=1.3&key=ruuxah6217";
        return url;
    }

    private String createURL(long id) {
        String url = "http://catalog.api.2gis.ru/profile?&version=1.3&key=ruuxah6217&id=" + id;
        return url;
    }
}