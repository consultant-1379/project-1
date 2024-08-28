package com.ericsson.gladiators.itemsservice.services;

import com.ericsson.gladiators.itemsservice.ItemsServiceApplication;
import com.ericsson.gladiators.itemsservice.models.ItemEntity;
import com.ericsson.gladiators.itemsservice.repositories.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = ItemsServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemEntityServiceImplTest {

    @InjectMocks
    ItemServiceImpl itemService;

    @Mock
    ItemRepository itemRepository;


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
    void testItemDeletedSuccessfullyById() {
        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));
        String deleted = itemService.removeItem(1);
        assertThat(deleted, containsString("ItemEntity deleted."));
    }

    @Test
    void testItemNotFoundToBeDeleted() {
        Optional<ItemEntity> emptyOptional = Optional.empty();
        when(itemRepository.findById(anyInt())).thenReturn(emptyOptional);
        String deleted = itemService.removeItem(1);
        assertThat(deleted, containsString("Problem deleting item."));
    }

    @Test
    void testItemCanBeRetrievedById() {
        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));
        ItemEntity itemById = itemService.getItemsById(1);
        assertNotNull(itemById);
        assertThat(itemById.getCategory(), containsString("mad"));
    }

    @Test
    void testReturnsNullWhenItemNotFound() {
        Optional<ItemEntity> emptyOptional = Optional.empty();
        when(itemRepository.findById(anyInt())).thenReturn(emptyOptional);
        ItemEntity itemById = itemService.getItemsById(1);
        assertNull(itemById);
    }

    @Test
    void testGetAllItems() {
        when(itemRepository.findAll()).thenReturn(items);
        List<ItemEntity> allItems = itemService.getAllItems();
        assertThat(allItems.isEmpty(), is(false));
    }

    @Test
    void testGetAllItemsByBoardId() {
        when(itemRepository.findAllByBoardId(anyInt())).thenReturn(items);
        List<ItemEntity> allItemsByBoardId = itemService.getItemsByBoardId(1);
        assertThat(allItemsByBoardId.isEmpty(), is(false));
    }

    @Test
    void testGetAllItemsByCreatorId() {
        when(itemRepository.findAllByCreatorId(anyInt())).thenReturn(items);
        List<ItemEntity> allItemsByCreatorId = itemService.getItemsbyCreatorId(1);
        assertThat(allItemsByCreatorId.isEmpty(), is(false));
    }

    @Test
    void testItemVotDown() {
        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));
        String voteUpWorked = itemService.itemDownVote(1);
        assertThat(voteUpWorked, containsString("Down Vote Noticed"));
    }

    @Test
    void testItemVotUp() {
        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));
        String voteUpWorked = itemService.itemUpVote(1);
        assertThat(voteUpWorked, containsString("Up Vote Noticed"));
    }

    @Test
    void testItemVotDownWithNoItemFound() {
        Optional<ItemEntity> emptyOptional = Optional.empty();
        when(itemRepository.findById(anyInt())).thenReturn(emptyOptional);
        String voteUpWorked = itemService.itemDownVote(1);
        assertThat(voteUpWorked, containsString("Down Vote did not work."));
    }

    @Test
    void testItemVotUpWithNoItemFound() {
        Optional<ItemEntity> emptyOptional = Optional.empty();
        when(itemRepository.findById(anyInt())).thenReturn(emptyOptional);
        String voteUpWorked = itemService.itemUpVote(1);
        assertThat(voteUpWorked, containsString("Up Vote did not work."));
    }

    @Test
    void testItemAdded() {
        when(itemRepository.save(item)).thenReturn(item);
        ItemEntity newItem= itemService.addItem(item);
        assertNotNull(newItem);
    }

    @Test
    void testItemUpdate() {
        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));
        String updateItem = itemService.updateItem("new", 1);
        assertThat(updateItem, containsString("ItemEntity Updated"));
    }

    @Test
    void testItemUpdateItemNotFound() {
        Optional<ItemEntity> emptyOptional = Optional.empty();
        when(itemRepository.findById(anyInt())).thenReturn(emptyOptional);
        String updateItem = itemService.updateItem("new", 1);
        assertThat(updateItem, containsString("ItemEntity not updated."));
    }

}

