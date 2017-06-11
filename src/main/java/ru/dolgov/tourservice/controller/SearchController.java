package ru.dolgov.tourservice.controller;

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

    @Autowired
    private MultiThreadService threadService;

    @RequestMapping(value = "/{trend}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Firm>> searchTrend(@PathVariable String trend){
        List<Firm> firmList;
        firmList = threadService.addTask(trend);
        return new ResponseEntity<>(firmList, HttpStatus.OK);
    }
}
