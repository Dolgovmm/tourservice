package ru.dolgov.tourservice.gisservice;

import ru.dolgov.tourservice.firm.Firm;

import java.util.List;

/**
 * @author M. Dolgov
 *         08.06.2017.
 */
public interface Command {

    public List<Firm> execute();
}
