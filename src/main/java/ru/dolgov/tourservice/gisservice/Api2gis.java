package ru.dolgov.tourservice.gisservice;

import ru.dolgov.tourservice.firm.Firm;

import java.util.List;

/**
 * @author M. Dolgov
 *         07.06.2017.
 */
public interface Api2gis {

    public List<Firm> getFirmsFrom2gisByTrend(String trend);
}
