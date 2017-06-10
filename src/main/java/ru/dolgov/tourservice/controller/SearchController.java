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
    public ResponseEntity<Firm> searchTrend(@PathVariable String trend){
        Firm firm = null;
        try {
            firm = api2gis.getFirm(trend, "Новосибирск");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(firm, HttpStatus.OK);
    }
}
