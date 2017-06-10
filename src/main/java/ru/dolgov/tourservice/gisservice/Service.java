package ru.dolgov.tourservice.gisservice;

import ru.dolgov.tourservice.firm.Firm;
import ru.dolgov.tourservice.gisservice.api2gis.Api2gis;
import ru.dolgov.tourservice.gisservice.api2gis.Api2gisImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author M. Dolgov
 *         08.06.2017.
 */
public class Service {

    private ExecutorService service;
    private Api2gis api2gis;
    private List<String> locationList;

    public Service() {
        service = Executors.newCachedThreadPool();
        //this.api2gis = new Api2gisImpl();
        this.locationList = new ArrayList<>();
        locationList.add("Новосибирск");
        locationList.add("Кемерово");
        locationList.add("Томск");
        locationList.add("Омск");
        locationList.add("Новокузнецк");
    }

    public void addTask(Command command) {

    }

//    public Firm getFirm(String trend) {
//        for (int i = 0; i < locationList.size(); i++) {
//            api2gis.getFirm(trend, locationList.get(i));
//        }
//
//    }
}