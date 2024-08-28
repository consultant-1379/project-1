package com.ericsson.gladiators.itemsservice.controllers;

import com.ericsson.gladiators.itemsservice.ItemsServiceApplication;
import com.ericsson.gladiators.itemsservice.models.ItemEntity;
import com.ericsson.gladiators.itemsservice.repositories.ItemRepository;
import com.ericsson.gladiators.itemsservice.services.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ItemsServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ItemsControllerTests {

    @InjectMocks
    ItemServiceImpl itemService;

    @Mock
    ItemRepository itemRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    List<ItemEntity> items = new ArrayList<>();
    ItemEntity item = new ItemEntity();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        item.setId(1);
        item.setDescription("check");
        item.setVote(0);
        item.setCreatorId(1);
        item.setCategory("mad");
        item.setBoardId(1);

        items.add(item);


    }


    @Test
    void testGetAllItems() {
        ResponseEntity<List<ItemEntity>> responseEntity = restTemplate.exchange(
                "/items",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ItemEntity>>() {
                });
        List<ItemEntity> responseBody = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    void testItemCreateWorks() throws Exception {

        String jsonItem = "{\"id\":5,\"description\":\"Functional tests keep breaking\",\"category\":\"sad\",\"boardID\":null}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/items/create")
                .content(jsonItem)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andReturn();
        String resultItem = result.getResponse().getContentAsString();

        assertTrue(resultItem.contains("ItemEntity Created"));
    }

    @Test
    void testVoteDownControllerMapping() throws Exception {

        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/items/votedown")
                .param("id", "1" )
                .accept(MediaType.ALL))
                .andExpect(status().is(200))
                .andReturn();

        String resultItem = result.getResponse().getContentAsString();
        assertThat(resultItem, containsString("Down Vote did not work."));
    }

    @Test
    void testVoteUpControllerMapping() throws Exception {

        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/items/voteup")
                .param("id", "1" )
                .accept(MediaType.ALL))
                .andExpect(status().is(200))
                .andReturn();

        String resultItem = result.getResponse().getContentAsString();
        assertThat(resultItem, containsString("Up Vote did not work."));
    }

    @Test
    void testUpdateItemByPostParams() throws Exception {

        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/items/update/params")
                .param("id", "1" )
                .param("description", "New Description")
                .accept(MediaType.ALL))
                .andExpect(status().is(200))
                .andReturn();

        String resultItem = result.getResponse().getContentAsString();
        assertThat(resultItem, containsString("ItemEntity not updated."));
    }

    @Test
    void testCreateItemByPostParams() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/items/create/params")
                .param("category", "mad" )
                .param("description", "New Description")
                .param("boardId", "1")
                .param("creatorId", "1")
                .accept(MediaType.ALL))
                .andExpect(status().is(201))
                .andReturn();

    }

    @Test
    void testGetOneItemControllerMapping() throws Exception {

        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));
        mockMvc.perform(MockMvcRequestBuilders.get("/items/1")
                .param("id", "1" )
                .accept(MediaType.ALL))
                .andExpect(status().is(200))
                .andReturn();

    }

    @Test
    void testGetItemsWithBoardIdControllerMapping() throws Exception {

        when(itemRepository.findAllByBoardId(anyInt())).thenReturn(items);
        mockMvc.perform(MockMvcRequestBuilders.get("/items/board/1")
                .param("id", "1" )
                .accept(MediaType.ALL))
                .andExpect(status().is(200))
                .andReturn();

    }

    @Test
    void testGetItemsForCreatorIdControllerMapping() throws Exception {

        when(itemRepository.findAllByCreatorId(anyInt())).thenReturn(items);
        mockMvc.perform(MockMvcRequestBuilders.get("/items/creator/1")
                .param("id", "1" )
                .accept(MediaType.ALL))
                .andExpect(status().is(200))
                .andReturn();

    }

    @Test
    void testDeleteItemControllerMapping() throws Exception {

        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));
        mockMvc.perform(MockMvcRequestBuilders.get("/items/delete/1")
                .param("id", "1" )
                .accept(MediaType.ALL))
                .andExpect(status().is(200))
                .andExpect(content().string("Problem deleting item."));

    }
}

