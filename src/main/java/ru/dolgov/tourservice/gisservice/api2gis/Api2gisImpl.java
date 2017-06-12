package ru.dolgov.tourservice.gisservice.api2gis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dolgov.tourservice.firm.Firm;
import ru.dolgov.tourservice.gisservice.HttpClient.HttpClient;
import ru.dolgov.tourservice.gisservice.HttpClient.HttpClientImpl;
import ru.dolgov.tourservice.gisservice.jsonparser.JsonParser;
import ru.dolgov.tourservice.gisservice.jsonparser.JsonParserImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Class implement methods to work with 2gis api. Method getFirm() return list of firm by trend and city.
 * Trend - word what to search, city - where to search.
 * Method getRating() set rating to firm from firm list.
 * @author M. Dolgov
 *         08.06.2017.
 */
public class Api2gisImpl implements Api2gis{
    static final Logger logger = LoggerFactory.getLogger(Api2gisImpl.class);

    private HttpClient client;
    private JsonParser jsonParser;

    public Api2gisImpl() {
        logger.debug("begin constructor Api2gisImpl");
        this.client = new HttpClientImpl();
        this.jsonParser = new JsonParserImpl();
        logger.debug("end constructor Api2gisImpl");
    }

    @Override
    public Firm getFirm(String trend, String location) throws IOException {
        logger.debug("get firm by trend and location method api 2 gis");
        logger.debug("get json string with firms from url");
        String jsonFirms = client.getJsonFromUrl(createURL(trend, location));
        logger.debug("parse json string to firm list");
        List<Firm> firmList = jsonParser.parseFirms(jsonFirms);
        logger.debug("add rating to firm in firm list");
        getRatingFirm(firmList);
        logger.debug("get firm with max rating");
        Firm firm = Collections.max(firmList);
        firm.setName(firm.getName() + ", " + trend);
        firm.setAddress(location + ", " + firm.getAddress());
        logger.debug("return firm with max rating");
        return firm;
    }

    private void getRatingFirm(List<Firm> firmList) throws IOException {
        logger.debug("get rating by firm id method api 2 gis");
        logger.debug("create json parser");
        JsonParser jsonParser = new JsonParserImpl();
        for (Firm firm: firmList) {
            logger.debug("get json string with firm rating from url");
            String jsonRating = client.getJsonFromUrl(createURL(firm.getId()));
            logger.debug("parse json string to firm");
            jsonParser.parseProfile(jsonRating, firm);
        }
    }

    private String createURL(String trend, String location) {
        String url = "http://catalog.api.2gis.ru/search?what=" + trend + "&where=" + location +"&version=1.3&key=ruuxah6217";
        logger.debug("create URL to get firm list: " + url);
        return url;
    }

    private String createURL(long id) {
        String url = "http://catalog.api.2gis.ru/profile?&version=1.3&key=ruuxah6217&id=" + id;
        logger.debug("create URL to get firm rating: " + url);
        return url;
    }
}