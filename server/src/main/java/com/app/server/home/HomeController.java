package com.app.server.home;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping({"/api"})
public class HomeController {
  
  @GetMapping({"", "/"}) 
  public ResponseEntity<Object> homeRoute(){
    Map<String, Object> body = new HashMap<String, Object>();
    body.put("success", true);
    body.put("message", "Influx server is running");
    return new ResponseEntity<Object>(body, HttpStatus.OK);
  }
}
