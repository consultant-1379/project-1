package com.ericsson.gladiators.retrospectiveservice;

import com.ericsson.gladiators.retrospectiveservice.model.Retrospective;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RetrospectiveControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetALlRetrospectives() {
        ResponseEntity<List<Retrospective>>  responseEntity = testRestTemplate.exchange(
                "/retrospectives",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Retrospective>>() {
                });
        List<Retrospective> resonseBody = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void testRetrospectiveCreateWorks() throws Exception {

        String jsonItem = "{\n" +
                "\"id\": 7,\n" +
                "\"title\": \"Team Gladiator Retrospective\"\n" +
                "}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/retrospectives/create")
                .content(jsonItem)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andReturn();
        String resultItem = result.getResponse().getContentAsString();

        assertTrue(resultItem.contains("Team Gladiator Retrospective"));
    }








}
