package ru.dolgov.tourservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.dolgov.tourservice.firm.Firm;
import ru.dolgov.tourservice.gisservice.Service;
import ru.dolgov.tourservice.gisservice.api2gis.Api2gis;
import ru.dolgov.tourservice.gisservice.api2gis.Api2gisImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author M. Dolgov
 *         06.06.2017.
 */
@Controller
@RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {

    @Autowired
    private Api2gis api2gis;

    @RequestMapping(value = "/{trend}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Firm>> searchTrend(@PathVariable String trend){
        List<String> locations = getLocations();
        List<Firm> firmList = new ArrayList<>();
        try {
            for (String location: locations) {
                firmList.add(api2gis.getFirm(trend, location));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(firmList);
        return new ResponseEntity<>(firmList, HttpStatus.OK);
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
