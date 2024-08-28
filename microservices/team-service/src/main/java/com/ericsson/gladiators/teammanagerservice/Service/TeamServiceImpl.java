package com.ericsson.gladiators.teammanagerservice.Service;




import com.ericsson.gladiators.teammanagerservice.DAO.TeamDAO;
import com.ericsson.gladiators.teammanagerservice.Model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamDAO teamDAO;

    public List<Team> getAllTeams(){
        List<Team> teams = teamDAO.getAllTeams();
        return teams;
    }

    public void createTeam(Team team){
        teamDAO.createTeam(team);
    }

    @Override
    public void updateRetroID(String teamName, int retroID) {
        teamDAO.updateRetroID(teamName, retroID);
    }


}
