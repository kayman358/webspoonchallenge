/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snippet.CodeChallenge.controller;

import com.snippet.CodeChallenge.model.RecipeObject;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Arowolo Ibrahim K
 */
@RestController

public class SnippetController {

    @Value("${spring.url}")
    private String url;

    Map<String, RecipeObject> map = new HashMap<>();

    @PostMapping("/snippets")
    public ResponseEntity<RecipeObject> Hello(@RequestParam String name, @RequestParam int expires_in, @RequestParam String snippet) {
        RecipeObject object = new RecipeObject();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, expires_in);

        object.setUrl(url + "snippets/" + name);
        object.setName(name);
        object.setExpires_in(calendar.getTime());
        object.setSnippet(snippet);
        if (!map.containsKey(name)) {
            map.put(name, object);
        }

        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    @GetMapping("/snippets/{name}")
    public ResponseEntity<RecipeObject> ret(@PathVariable String name) {
        RecipeObject newObject;
        newObject = map.get(name);

        if (newObject != null) {
            Date d = newObject.getExpires_in();
            Calendar calendar = Calendar.getInstance();

            long seconds = (calendar.getTime().getTime() - d.getTime()) / 1000;
            System.out.println("d secs " + seconds);
            if (seconds <= 30) { // The api expires in 60 secs - 30 secs was added to the object expiry time initially
                calendar.add(Calendar.SECOND, 30);
                newObject.setExpires_in(calendar.getTime()); //update expiry time
                return new ResponseEntity<>(newObject, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/snippets/{name}/like")
    public ResponseEntity<RecipeObject> like(@PathVariable String name) {
        RecipeObject newObject;
        newObject = map.get(name);

        if (newObject != null) {

            long like = newObject.getLike();
            like += 1;
            newObject.setLike(like);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, 30);
            newObject.setExpires_in(calendar.getTime());
            return new ResponseEntity<>(newObject, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
