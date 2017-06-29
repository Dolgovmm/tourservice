package ru.dolgov.tourservice.gisservice.HttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Class implement method to get json string from URL. Class used GET request.
 * @author M. Dolgov
 *         08.06.2017.
 */
public class HttpClientImpl implements HttpClient {
    static final Logger logger = LoggerFactory.getLogger(HttpClientImpl.class);

    private CloseableHttpClient client;

    public HttpClientImpl() {
        client = HttpClientBuilder.create().build();
    }

    @Override
    public String getJsonFromUrl(String url) throws IOException{
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
