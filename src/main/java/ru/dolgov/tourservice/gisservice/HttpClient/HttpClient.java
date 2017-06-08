package ru.dolgov.tourservice.gisservice.HttpClient;

import java.io.IOException;

/**
 * @author M. Dolgov
 *         08.06.2017.
 */
public interface HttpClient {

    public String getJsonFromUrl(String url) throws IOException;
}
