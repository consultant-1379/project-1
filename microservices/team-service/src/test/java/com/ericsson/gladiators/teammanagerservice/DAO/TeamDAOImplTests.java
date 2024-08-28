package com.ericsson.gladiators.teammanagerservice.DAO;


import com.ericsson.gladiators.teammanagerservice.Model.Team;
import com.ericsson.gladiators.teammanagerservice.TeamServiceApplication;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import javax.sql.DataSource;


import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = TeamServiceApplication.class)
public class TeamDAOImplTests {

    @Autowired
    DataSource dataSource;


    TeamDAO teamDAO = new TeamDAOImpl();

    @Before
    public void setUp() {

         dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:jdbc/schema.sql")
                .addScript("classpath:jdbc/test-data.sql")
                .build();

    }


    @Transactional
    @Rollback
    @Test
    public void test_create_team(){
        teamDAO.changeDataSource(dataSource);
        teamDAO.createTeam(sampleTeam());
        assertEquals(sampleTeam().getTeamName(), teamDAO.getAllTeams().get(0).getTeamName());
    }

    @Test
    public void test_get_all_teams() {
        teamDAO.changeDataSource(dataSource);
        assertEquals(3, teamDAO.getAllTeams().size());
        assertEquals("Test team", teamDAO.getAllTeams().get(0).getTeamName());
    }

    @Test
    public void test_update_retro_id() {
        teamDAO.changeDataSource(dataSource);
        teamDAO.updateRetroID("Test team", 5);
        assertEquals(5, teamDAO.getAllTeams().get(0).getRetrospectiveId());
    }



    public Team sampleTeam(){
        Team team = new Team();
        team.setTeamName("Test team");
        team.setTeamMembers("Test, Members");
        team.setRetrospectiveId(7);
        return team;
    }

}
