package com.ericsson.gladiators.teammanagerservice.Controller;

import com.ericsson.gladiators.teammanagerservice.Model.Team;
import com.ericsson.gladiators.teammanagerservice.Service.TeamService;
import com.ericsson.gladiators.teammanagerservice.TeamServiceApplication;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = TeamServiceApplication.class)
public class TeamControllerTests {

    @Mock
    TeamService teamService;

    @InjectMocks
    TeamController teamController;


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

    }

    @Test
    public void test_get_all_teams() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(teamService.getAllTeams()).thenReturn(teams);

        ResponseEntity<?> responseEntity = teamController.getTeams();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isEqualTo(teams);
    }

    @Test
    public void test_get_all_teams_null() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        when(teamService.getAllTeams()).thenReturn(null);

        ResponseEntity<?> responseEntity = teamController.getTeams();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void test_create_team() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(teamService.getAllTeams()).thenReturn(teams);

        ResponseEntity<?> responseEntity = teamController.createTeam(team);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody()).isEqualTo("Team Created");
    }

    @Test
    public void test_null_team_create_team() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        ResponseEntity<?> responseEntity = teamController.createTeam(null);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void test_update_retro_id() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<?> responseEntity = teamController.updateRetroID(5, "Test");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void test_empty_update_retro_id() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        ResponseEntity<?> responseEntity = teamController.updateRetroID(5, null);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }
}
