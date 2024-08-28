package com.ericsson.gladiators.itemcommentservice.controllers;

import com.ericsson.gladiators.itemcommentservice.models.ItemComment;
import com.ericsson.gladiators.itemcommentservice.services.ItemCommentServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(ItemCommentController.class)
class ItemCommentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemCommentServiceImpl itemCommentService;


    @Test
    public void When_getItemCommentsByItemId_And_Given_Valid_ItemId_Then_Return_JSonArray() throws Exception{

        Integer itemId = 1;

        // Given.
        ItemComment itemComment = new ItemComment(1,1,"A comment");
        ItemComment itemComment2 = new ItemComment(2,1,"A second comment");
        ItemComment itemComment3 = new ItemComment(3,1,"A third comment");
        ItemComment itemComment4 = new ItemComment(4,1,"A fourth comment");

        List<ItemComment> itemCommentList = Arrays.asList(itemComment, itemComment2,itemComment3,itemComment4);


        given(itemCommentService.getItemCommentsByItemId(itemId)).willReturn(itemCommentList);

        // When.
        MockHttpServletResponse response = mockMvc.perform(
                get("/itemComment/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Then.
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String itemCommentListJson = new Gson().toJson(itemCommentList);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(itemCommentListJson);
    }

    @Test
    public void When_getItemCommentsByItemId_And_Given_Non_Existent_ItemId_Then_Return_Not_Found_Message() throws Exception{

        // When.
        MockHttpServletResponse response = mockMvc.perform(
                get("/itemComment/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).isEqualTo("No item comments could be found!");
    }

    /*
    @Test
    public void Test_Item_Comment_Addition_Works() throws Exception {

        ItemComment itemComment = new ItemComment(1,1,"A comment");

        given(itemCommentService.addItemComment(itemComment)).willReturn(itemComment);


        String itemCommentJson = new Gson().toJson(itemComment);

        System.out.println(itemCommentJson);

        MockHttpServletResponse response = mockMvc.perform(post("/itemComment/")
                .contentType(MediaType.APPLICATION_JSON)
        .content(itemCommentJson))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(itemCommentJson);
    }

     */

    @Test
    public void Test_Item_Comment_Addition_Error_When_Empty_Object_Supplied() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(post("/itemComment/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).isEqualTo("Could not add new item comment!");
    }
}