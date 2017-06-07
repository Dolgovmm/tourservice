package ru.dolgov.tourservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dolgov.tourservice.firm.Firm;

/**
 * @author M. Dolgov
 *         06.06.2017.
 */
@Controller
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {

    @RequestMapping(value = "search/{trend}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Firm> addContact(@PathVariable String trend){


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
