package ru.dolgov.tourservice.gisservice.api2gis;

import ru.dolgov.tourservice.firm.Firm;
import ru.dolgov.tourservice.gisservice.HttpClient.HttpClient;
import ru.dolgov.tourservice.gisservice.jsonparser.JsonParser;
import ru.dolgov.tourservice.gisservice.jsonparser.JsonParserImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author M. Dolgov
 *         08.06.2017.
 */
public class Api2gisImpl implements Api2gis{

    private HttpClient client;
    private JsonParser jsonParser;

    public Api2gisImpl(HttpClient client, JsonParser jsonParser) {
        this.client = client;
        this.jsonParser = jsonParser;
    }

    @Override
    public Firm getFirm(String trend, String location) throws IOException {
        String jsonFirms = client.getJsonFromUrl(createURL(trend, location));

        List<Firm> firmList = jsonParser.parseFirms(jsonFirms);

        for (Firm firm: firmList) {
            getRatingFirm(firm);
        }

        Firm firm = Collections.max(firmList);
        firm.setName(firm.getName() + ", " + trend);
        firm.setAddress(location + ", " + firm.getAddress());
        return firm;
    }

    private void getRatingFirm(Firm firm) throws IOException {
        String jsonRating = client.getJsonFromUrl(createURL(firm.getId()));
        JsonParser jsonParser = new JsonParserImpl();
        jsonParser.parseProfile(jsonRating, firm);
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