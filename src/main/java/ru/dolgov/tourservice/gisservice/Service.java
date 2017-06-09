package ru.dolgov.tourservice.gisservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author M. Dolgov
 *         08.06.2017.
 */
public class Service {

    private ExecutorService service;

    public Service() {
        service = Executors.newFixedThreadPool(20);
    }


}