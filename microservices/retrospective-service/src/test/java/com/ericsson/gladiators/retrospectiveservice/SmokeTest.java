package com.ericsson.gladiators.retrospectiveservice;

import com.ericsson.gladiators.retrospectiveservice.controller.RetrospectiveController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SmokeTest {

    @Autowired
    private RetrospectiveController controller;




    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
