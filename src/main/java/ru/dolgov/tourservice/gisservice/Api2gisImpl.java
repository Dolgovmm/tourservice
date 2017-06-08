package ru.dolgov.tourservice.gisservice;

import ru.dolgov.tourservice.firm.Firm;
import ru.dolgov.tourservice.gisservice.HttpClient.HttpClientImpl;

import java.util.List;

/**
 * @author M. Dolgov
 *         08.06.2017.
 */
public class Api2gisImpl implements Api2gis {

    private HttpClientImpl client;

    @Override
    public List<Firm> getFirmsFrom2gisByTrend(String trend, String location) {
        client = new HttpClientImpl();

        return null;
    }
}