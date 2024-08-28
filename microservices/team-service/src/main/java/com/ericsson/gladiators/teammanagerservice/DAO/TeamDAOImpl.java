package com.ericsson.gladiators.teammanagerservice.DAO;



import com.ericsson.gladiators.teammanagerservice.Model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class TeamDAOImpl extends JdbcDaoSupport implements TeamDAO {

    @Autowired
    DataSource datasource;

    @PostConstruct
    private void init(){
        setDataSource(datasource);
    }


    @Override
    public List<Team> getAllTeams(){
        String sql = "Select * from Teams";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Team> res = new ArrayList<Team>();
        for(Map<String, Object> row : rows) {
            Team team = new Team();
            team.setTeamId((int) row.get("teamId"));
            team.setTeamName((String) row.get("teamName"));
            team.setTeamMembers((String) row.get("teamMembers"));
            team.setRetrospectiveId((int) row.get("retrospectiveId"));
            res.add(team);
        }
        return res;
    }

    @Override
    public void createTeam(Team team) {

        String sql = "INSERT INTO Teams " + "(teamName, teamMembers, retrospectiveId) VALUES (?, ?, ?)";
        getJdbcTemplate().update(sql, new Object[]{team.getTeamName(), team.getTeamMembers(), team.getRetrospectiveId()});
    }

    @Override
    public void changeDataSource(DataSource source) {
        setDataSource(source);
    }

    @Override
    public void updateRetroID(String teamName, int retroID) {
        String sql = "UPDATE Teams set retrospectiveId = (?) WHERE teamName = (?)";
        getJdbcTemplate().update(sql, retroID, teamName);
    }


}
