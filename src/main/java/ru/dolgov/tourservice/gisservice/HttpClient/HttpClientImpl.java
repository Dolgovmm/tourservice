package ru.dolgov.tourservice.gisservice.HttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author M. Dolgov
 *         08.06.2017.
 */
public class HttpClientImpl implements HttpClient{

    private String createURL(String trend, String location) {
        String url = "http://catalog.api.2gis.ru/search?what=" + trend + "&where=" + location +"&version=1.3&key=ruuxah6217";
        return url;
    }

    @Override
    public String getJsonFromUrl(String url) throws IOException{
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(url);
        HttpResponse response = client.execute(getRequest);
        HttpEntity httpEntity = response.getEntity();
        if (httpEntity != null) {
            String json = EntityUtils.toString(response.getEntity());
            return json;
        }
        return null;
    }
}
