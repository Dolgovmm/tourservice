package ru.dolgov.tourservice.gisservice.jsonparser;

import ru.dolgov.tourservice.firm.Firm;

import java.util.List;

/**
 * @author M. Dolgov
 *         08.06.2017.
 */
public interface JsonParser {

    public List<Firm> parseFirm(String json);

    public Firm parseProfile(String json);
}
