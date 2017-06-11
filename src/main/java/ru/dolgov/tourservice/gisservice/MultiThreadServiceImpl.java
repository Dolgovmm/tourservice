package ru.dolgov.tourservice.gisservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dolgov.tourservice.firm.Firm;
import ru.dolgov.tourservice.gisservice.api2gis.Api2gis;

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

    @Autowired
    private Api2gis api2gis;

    private List<Future<Firm>> futureList;
    private ExecutorService threadService;

    public MultiThreadServiceImpl() {
        logger.debug("begin constructor MultiThreadServiceImpl");
        threadService = Executors.newCachedThreadPool();
        logger.debug("end constructor MultiThreadServiceImpl");
    }

    @Override
    public List<Firm> addTask(String trend) {
        logger.debug("add task method");
        logger.debug("create future list");
        futureList = new ArrayList<>();
        logger.debug("get locations to list");
        List<String> locationList = getLocations();

        for (String location: locationList) {
            logger.debug("create callable thread with method get firm");
            Callable<Firm> callable = () -> api2gis.getFirm(trend, location);
            logger.debug("add callable to thread service");
            Future<Firm> future = threadService.submit(callable);
            logger.debug("add future to future list");
            futureList.add(future);
        }
        logger.debug("get firm list from future list");
        List<Firm> firmList = getFirmList(futureList);
        logger.debug("sorting firm list by rating");
        sortList(firmList);
        logger.debug("return firm list");
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
                logger.error("error get firm list from future list with exception messsage: " + e.getMessage());
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