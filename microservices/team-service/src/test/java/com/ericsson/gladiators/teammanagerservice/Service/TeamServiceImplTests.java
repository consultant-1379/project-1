package com.ericsson.gladiators.teammanagerservice.Service;

import com.ericsson.gladiators.teammanagerservice.DAO.TeamDAO;
import com.ericsson.gladiators.teammanagerservice.Model.Team;
import com.ericsson.gladiators.teammanagerservice.TeamServiceApplication;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest(classes = TeamServiceApplication.class)
public class TeamServiceImplTests {

    @Mock
    TeamDAO teamDAO;

    @InjectMocks
    TeamServiceImpl teamService;

    List<Team> teams = new ArrayList<Team>();
    Team team = new Team();

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);


        team.setTeamId(1);
        team.setTeamName("Test");
        team.setTeamMembers("test");
        team.setRetrospectiveId(2);

        teams.add(team);

        Mockito.when(teamDAO.getAllTeams())
                .thenReturn(teams);
    }

    @Test
    public void test_service_get_all_teams(){
        List<Team> teams = teamService.getAllTeams();
        assertArrayEquals(teams.toArray(), teams.toArray());
    }

    @Test
    public void test_service_create_team(){
        teamService.createTeam(team);
        verify(teamDAO, times(1)).createTeam(team);
    }

    @Test
    public void test_update_retro_id(){
        teamService.updateRetroID(team.getTeamName(), team.getTeamId());
        verify(teamDAO, times(1)).updateRetroID(team.getTeamName(), team.getTeamId());
    }


}
