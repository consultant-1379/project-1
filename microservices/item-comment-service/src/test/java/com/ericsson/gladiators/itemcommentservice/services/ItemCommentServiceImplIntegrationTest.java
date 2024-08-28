package com.ericsson.gladiators.itemcommentservice.services;

import com.ericsson.gladiators.itemcommentservice.ItemCommentServiceApplication;
import com.ericsson.gladiators.itemcommentservice.models.ItemComment;
import com.ericsson.gladiators.itemcommentservice.repositories.ItemCommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = ItemCommentServiceApplication.class, loader = AnnotationConfigContextLoader.class)
public class ItemCommentServiceImplIntegrationTest {


    @TestConfiguration
    static class ItemCommentServiceImplTestContextConfiguration {

        @Bean
        public ItemCommentService itemCommentService(){
            return new ItemCommentServiceImpl();
        }
    }

    @Autowired
    private ItemCommentService itemCommentService;

    @MockBean
    private ItemCommentRepository itemCommentRepository;

    List<ItemComment> itemCommentList;

    @Before
    public void setUp(){
        ItemComment itemComment = new ItemComment(1,1, "A comment about an item");
        ItemComment itemComment2 = new ItemComment(2,2, "A different comment about an item");
        ItemComment itemComment3 = new ItemComment(3,2, "A second comment that is for item with id of 2");
        ItemComment itemComment4 = new ItemComment(3,3, "fourth comment");
        ItemComment itemComment5 = new ItemComment(5,4, "A the fifth comment object but the item id is 4");

        itemCommentList = new ArrayList<>();
        itemCommentList.add(itemComment);
        itemCommentList.add(itemComment2);
        itemCommentList.add(itemComment3);
        itemCommentList.add(itemComment4);
        itemCommentList.add(itemComment5);



        Mockito.when(itemCommentRepository.findById(1))
                .thenReturn(java.util.Optional.of(itemComment));


        List<ItemComment> itemCommentListOnlyForItemId2 = new ArrayList<>();
        itemCommentListOnlyForItemId2.add(itemComment2);
        itemCommentListOnlyForItemId2.add(itemComment3);

        Mockito.when(itemCommentRepository.findAllByItemId(2))
                .thenReturn(itemCommentListOnlyForItemId2);


        Mockito.when(itemCommentRepository.findAll())
                .thenReturn(itemCommentList);


        ItemComment newItemComment = new ItemComment(1,1,"A new item comment");
        Mockito.when(itemCommentRepository.save(newItemComment))
                .thenReturn(newItemComment);

        List<ItemComment> emptyCommentList = new ArrayList<>();
        Mockito.when(itemCommentRepository.findAllByItemIdAndComment(itemCommentList.get(0).getItemId(),itemCommentList.get(0).getComment()))
                .thenReturn(emptyCommentList);


        Mockito.when(itemCommentRepository.save(itemCommentList.get(0)))
                .thenReturn(itemCommentList.get(0));
    }

    @Test
    public void When_Valid_Id_Then_Item_Comment_Should_Be_Found(){

        Integer id = 1;

        ItemComment foundItemComment = itemCommentService.getItemCommentById(id);

        assertThat(foundItemComment.getId(), equalTo(1));
    }


    @Test
    public void When_Valid_ItemId_Then_Item_All_Comments_With_ItemId_Should_Be_Found(){
        Integer itemId = 2;

        //list containing only the item comments that belong to ItemId2
        List<ItemComment> hamcrestMatchers = new ArrayList<>();
        hamcrestMatchers.add(itemCommentList.get(1));
        hamcrestMatchers.add(itemCommentList.get(2));

        List<ItemComment> foundItemCommentList = itemCommentService.getItemCommentsByItemId(itemId);

        assertThat(foundItemCommentList, hasSize(2));
        assertThat(foundItemCommentList, containsInAnyOrder(hamcrestMatchers.toArray()));
    }

    @Test
    public void When_Item_Comments_Present_Get_All_Item_Comments(){

        List<ItemComment> hamcrestMatchers = itemCommentList;

        List<ItemComment> foundItemCommentList = itemCommentService.getAllItemComments();

        assertThat(foundItemCommentList, hasSize(5));
        assertThat(foundItemCommentList, containsInAnyOrder(hamcrestMatchers.toArray()));
    }

    @Test
    public void When_Adding_A_New_Item_Comment_Then_Item_Comment_Is_Added(){

        ItemComment hamcrestMatcher = itemCommentList.get(0);
        ItemComment foundItemComment = itemCommentService.addItemComment(itemCommentList.get(0));

        assertThat(foundItemComment, equalTo(hamcrestMatcher));
    }
}