package ru.dolgov.tourservice.gisservice.api2gis;

import ru.dolgov.tourservice.firm.Firm;

import java.io.IOException;
import java.util.List;

/**
 * @author M. Dolgov
 *         07.06.2017.
 */
public interface Api2gis {

    public Firm getFirm(String trend, String location) throws IOException;

    public void getRatingFirm(Firm firm) throws IOException;
}
