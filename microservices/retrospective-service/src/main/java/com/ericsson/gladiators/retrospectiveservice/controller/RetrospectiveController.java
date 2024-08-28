package com.ericsson.gladiators.retrospectiveservice.controller;

import com.ericsson.gladiators.retrospectiveservice.model.Retrospective;
import com.ericsson.gladiators.retrospectiveservice.service.RetrospectiveServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/retrospectives")
public class RetrospectiveController {



    @Autowired
    private RetrospectiveServiceImpl retrospectiveServiceImpl;

    @GetMapping(produces = {"application/json"})
    public @ResponseBody
    List<Retrospective> getAllRetrospectiveBoards(){
        return retrospectiveServiceImpl.getAllRetrospectiveBoards();
    }



    @GetMapping(value="/{id}", produces={"application/json"})
    public @ResponseBody Retrospective getRetrospectiveById(@PathVariable int id){
        return retrospectiveServiceImpl.getRetrospectiveById(id);
    }





    @PostMapping(value="/create", consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<Retrospective> createNewRetrospectived(@RequestBody Retrospective retrospective){
        Retrospective newRetrospective = new Retrospective(retrospective.getTitle());
        retrospectiveServiceImpl.createNewRetrospective(newRetrospective);


        URI uri = URI.create("/"+newRetrospective.getId());
        return ResponseEntity.created(uri).body(newRetrospective);
    }















}



