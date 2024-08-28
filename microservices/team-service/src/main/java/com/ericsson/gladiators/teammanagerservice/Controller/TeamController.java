package com.ericsson.gladiators.teammanagerservice.Controller;




import com.ericsson.gladiators.teammanagerservice.Model.Team;
import com.ericsson.gladiators.teammanagerservice.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping(consumes={"application/json","application/xml"}, produces={"application/json","application/xml"})
    public ResponseEntity getTeams() {
        List<Team> teams = teamService.getAllTeams();
         if(teams!= null){
             return  ResponseEntity.ok().body(teams);
         }
         else{
             return  ResponseEntity.notFound().build();
         }

    }

    @PostMapping(consumes={"application/json","application/xml"}, produces={"application/json","application/xml"})
    public ResponseEntity createTeam(@RequestBody Team team){
        if(team != null){
            teamService.createTeam(team);
            return ResponseEntity.status(201).body("Team Created");
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value="/retro", produces={"application/json","application/xml"})
    public ResponseEntity updateRetroID(@RequestParam int id,
                                        @RequestParam String teamName){
        if(teamName != null){
            teamService.updateRetroID(teamName, id);
            return ResponseEntity.status(200).body("Team retro id updated");
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

}
