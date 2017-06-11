package ru.dolgov.tourservice.gisservice;

import ru.dolgov.tourservice.firm.Firm;

import java.util.List;

/**
 * @author M. Dolgov
 *         11.06.2017.
 */
public interface MultiThreadService {

    public List<Firm> addTask(String trend);
}
