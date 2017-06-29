package ru.dolgov.tourservice.gisservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dolgov.tourservice.firm.Firm;
import ru.dolgov.tourservice.gisservice.api2gis.Api2gis;
import ru.dolgov.tourservice.gisservice.api2gis.Api2gisImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * Class implement method to add search method to threads. Keyword trend searches in specified cities.
 * @author M. Dolgov
 *         08.06.2017.
 */
public class MultiThreadServiceImpl implements MultiThreadService{
    static final Logger logger = LoggerFactory.getLogger(MultiThreadServiceImpl.class);

    private ExecutorService threadService;

    public MultiThreadServiceImpl() {
        threadService = Executors.newCachedThreadPool();
    }

    @Override
    public List<Firm> addTask(String trend) {
        List<Future<Firm>> futureList = new ArrayList<>();
        List<String> locationList = getLocations();

        for (String location: locationList) {
            Callable<Firm> callable = () -> {
                Api2gis api2gis = new Api2gisImpl();
                return api2gis.getFirm(trend, location);
            };
            Future<Firm> future = threadService.submit(callable);
            futureList.add(future);
        }
        List<Firm> firmList = getFirmList(futureList);
        sortList(firmList);
        return firmList;
    }

    private void sortList(List<Firm> firmList) {
        Collections.sort(firmList);
        Collections.reverse(firmList);
    }

    private List<Firm> getFirmList(List<Future<Firm>> futureList) {
        List<Firm> firmList = new ArrayList<>();
        for (Future<Firm> future: futureList) {
            try {
                firmList.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                if (logger.isErrorEnabled()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Error get firm list from future list{");
                    sb.append(future.toString());
                    sb.append("} with message: ");
                    sb.append(e.getMessage());
                    logger.error(sb.toString());
                }
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