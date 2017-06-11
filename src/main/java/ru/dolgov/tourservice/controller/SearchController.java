package ru.dolgov.tourservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dolgov.tourservice.firm.Firm;
import ru.dolgov.tourservice.gisservice.MultiThreadService;

import java.util.List;

/**
 * @author M. Dolgov
 *         06.06.2017.
 */
@Controller
@RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {
    static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private MultiThreadService threadService;

    @RequestMapping(value = "/{trend}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Firm>> searchTrend(@PathVariable String trend){
        logger.debug("search controller's method with trend: " + trend);
        List<Firm> firmList;
        logger.debug("add task to search to thread service");
        firmList = threadService.addTask(trend);
        logger.debug("return firm list and status OK");
        return new ResponseEntity<>(firmList, HttpStatus.OK);
    }
}
