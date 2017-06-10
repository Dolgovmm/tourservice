package ru.dolgov.tourservice.gisservice.jsonparser;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.dolgov.tourservice.firm.Firm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author M. Dolgov
 *         08.06.2017.
 */
public class JsonParserImpl implements JsonParser {

    @Override
    public List<Firm> parseFirms(String json) {
        final JSONObject jsonObject = new JSONObject(json);
        final int status = jsonObject.getInt("response_code");
        if (status == HttpStatus.SC_OK) {
            final JSONArray jsonArray = jsonObject.getJSONArray("result");
            final int size = jsonArray.length();
            final List<Firm> dataList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                final JSONObject data = jsonArray.getJSONObject(i);
                Firm firm = new Firm();
                firm.setId(data.getLong("id"));
                firm.setName(data.getString("name"));
                firm.setAddress(data.getString("address"));
                dataList.add(firm);
            }
            return dataList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void parseProfile(String json, Firm firm) {
        final JSONObject jsonObject = new JSONObject(json);
        final int status = jsonObject.getInt("response_code");
        if (status == HttpStatus.SC_OK) {
            Double rating = jsonObject.getDouble("rating");
            if (rating != null) {
                firm.setRating(rating);
            } else {
                firm.setRating(0);
            }
        }
    }
}
