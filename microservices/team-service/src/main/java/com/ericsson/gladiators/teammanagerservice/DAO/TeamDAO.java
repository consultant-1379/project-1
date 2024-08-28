package com.ericsson.gladiators.teammanagerservice.DAO;

import com.ericsson.gladiators.teammanagerservice.Model.Team;

import java.util.List;
import javax.sql.DataSource;

public interface TeamDAO {
    List<Team> getAllTeams();
    void createTeam(Team team);
    void changeDataSource(DataSource source);
    void updateRetroID(String teamName, int retroID);
}
