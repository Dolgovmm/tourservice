package ru.dolgov.tourservice.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author M. Dolgov
 *         06.06.2017.
 */
@Controller
@RequestMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
public class SearchController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Contact> addContact(@RequestBody String json){


    }

}
