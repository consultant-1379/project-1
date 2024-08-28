package com.ericsson.gladiators.teammanagerservice.Service;

import com.ericsson.gladiators.teammanagerservice.Model.Team;

import java.util.List;


public interface TeamService {
    List<Team> getAllTeams();
    void createTeam(Team team);
    void updateRetroID(String teamName, int retroID);
}
