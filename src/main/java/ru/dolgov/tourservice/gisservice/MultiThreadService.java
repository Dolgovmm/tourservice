package ru.dolgov.tourservice.gisservice;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dolgov.tourservice.firm.Firm;
import ru.dolgov.tourservice.gisservice.api2gis.Api2gis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author M. Dolgov
 *         08.06.2017.
 */
public class MultiThreadService {

    @Autowired
    private Api2gis api2gis;

    private List<Future<Firm>> futureList;
    private ExecutorService threadService;

    public MultiThreadService() {
        threadService = Executors.newCachedThreadPool();
    }

    public List<Firm> addTask(String trend) {
        futureList = new ArrayList<>();
        List<String> locationList = getLocations();

        for (String location: locationList) {
            Callable<Firm> callable = () -> api2gis.getFirm(trend, location);
            Future<Firm> future = threadService.submit(callable);
            futureList.add(future);
        }

        List<Firm> firmList = getFirmList(futureList);

        return firmList;
    }

    private List<Firm> getFirmList(List<Future<Firm>> futureList) {
        List<Firm> firmList = new ArrayList<>();
        for (Future<Firm> future: futureList) {
            try {
                firmList.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return firmList;
    }

    private List<String> getLocations() {
        List<String> locationList = new ArrayList<>();
        locationList.add("Новосибирск");
        locationList.add("Омск");
        locationList.add("Томск");
        locationList.add("Кемерово");
        locationList.add("Новокузнецк");
        return locationList;
    }
}