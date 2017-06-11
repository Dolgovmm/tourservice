package ru.dolgov.tourservice.gisservice.jsonparser;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dolgov.tourservice.firm.Firm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author M. Dolgov
 *         08.06.2017.
 */
public class JsonParserImpl implements JsonParser {
    static final Logger logger = LoggerFactory.getLogger(JsonParserImpl.class);

    @Override
    public List<Firm> parseFirms(String json) {
        logger.debug("parse json string to firm method");
        logger.debug("create json object by json string");
        final JSONObject jsonObject = new JSONObject(json);
        logger.debug("get value response code from json object");
        final int status = jsonObject.getInt("response_code");
        if (status == HttpStatus.SC_OK) {
            logger.debug("create json array from json object by key result");
            final JSONArray jsonArray = jsonObject.getJSONArray("result");
            logger.debug("get size json array");
            final int size = jsonArray.length();
            logger.debug("create array list to save firm");
            final List<Firm> dataList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                logger.debug("get json object from json array");
                final JSONObject data = jsonArray.getJSONObject(i);
                logger.debug("write fields firm from json object");
                Firm firm = new Firm();
                firm.setId(data.getLong("id"));
                firm.setName(data.getString("name"));
                firm.setAddress(data.getString("address"));
                logger.debug("add firm to array list of firm");
                dataList.add(firm);
            }
            logger.debug("return array list of firm");
            return dataList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void parseProfile(String json, Firm firm) {
        logger.debug("parse json string to rating method");
        logger.debug("create json object from json string");
        final JSONObject jsonObject = new JSONObject(json);
        logger.debug("get value response code from json object");
        final int status = jsonObject.getInt("response_code");
        if (status == HttpStatus.SC_OK) {
            logger.debug("get rating firm from json object");
            Double rating = jsonObject.getDouble("rating");
            if (rating != null) {
                logger.debug("set rating to firm");
                firm.setRating(rating);
            } else {
                logger.debug("rating is empty, set rating 0");
                firm.setRating(0);
            }
        }
    }
}
