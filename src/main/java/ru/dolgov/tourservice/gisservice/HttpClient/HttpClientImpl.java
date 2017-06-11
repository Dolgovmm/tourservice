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
 * @author M. Dolgov
 *         08.06.2017.
 */
public class HttpClientImpl implements HttpClient {
    static final Logger logger = LoggerFactory.getLogger(HttpClientImpl.class);

    private CloseableHttpClient client;

    public HttpClientImpl() {
        logger.debug("begin constructor HttpClientImpl");
        client = HttpClientBuilder.create().build();
        logger.debug("end constructor HttpClientImpl");
    }

    @Override
    public String getJsonFromUrl(String url) throws IOException{
        logger.debug("create get request with url: " + url);
        HttpGet getRequest = new HttpGet(url);
        logger.debug("execute request by client");
        HttpResponse response = client.execute(getRequest);
        logger.debug("check client response");
        HttpEntity httpEntity = response.getEntity();
        if (httpEntity != null) {
            String json = EntityUtils.toString(response.getEntity());
            logger.debug("return json string");
            return json;
        }
        logger.debug("wrong response, httpEntity is null");
        return null;
    }
}
