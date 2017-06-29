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
@RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
public class SearchController {
    static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private MultiThreadService threadService;

    @RequestMapping(value = "/{trend}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Firm>> searchTrend(@PathVariable String trend){
        List<Firm> firmList;
        firmList = threadService.addTask(trend);
        if (logger.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Get list of firm {");
            sb.append(firmList.toString());
            sb.append("} by trend {");
            sb.append(trend);
            sb.append("}");
            logger.debug(sb.toString());
        }
        return new ResponseEntity<>(firmList, HttpStatus.OK);
    }
}
