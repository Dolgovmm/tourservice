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
@RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
public class SearchController {

    @RequestMapping(value = "/{trend}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Firm> addContact(@PathVariable String trend){
        Firm firm = new Firm(trend, "novosibrsk", 1);

        return new ResponseEntity<Firm>(firm, HttpStatus.OK);
    }

}
