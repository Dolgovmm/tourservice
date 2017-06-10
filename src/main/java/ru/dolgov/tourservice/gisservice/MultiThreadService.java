package ru.dolgov.tourservice.gisservice;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dolgov.tourservice.firm.Firm;
import ru.dolgov.tourservice.gisservice.api2gis.Api2gis;
import ru.dolgov.tourservice.gisservice.api2gis.Api2gisImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author M. Dolgov
 *         08.06.2017.
 */
public class MultiThreadService {

    private ExecutorService threadService;

    @Autowired
    private Api2gis api2gis;
    private List<Future<Firm>> futureList;

    public MultiThreadService() {
        threadService = Executors.newCachedThreadPool();
    }

    public List<Firm> addTask(String trend, List<String> locationList) {
        futureList = new ArrayList<>();

        for (String location: locationList) {
            Callable<Firm> callable = () -> api2gis.getFirm(trend, location);
            Future<Firm> future = threadService.submit(callable);
            futureList.add(future);
        }

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
}