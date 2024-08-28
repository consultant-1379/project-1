package com.ericsson.gladiators.itemsservice.services;

import com.ericsson.gladiators.itemsservice.models.ItemEntity;

import java.util.List;

public interface ItemService {

    ItemEntity getItemsById(Integer itemId);
    List<ItemEntity> getAllItems();
    List<ItemEntity> getItemsByBoardId(Integer itemId);
    List<ItemEntity> getItemsbyCreatorId(Integer creatorId);
    String removeItem(Integer itemId);
    String updateItem(String description, Integer itemId);
    String itemUpVote(Integer id);
    String itemDownVote(Integer id);
    ItemEntity addItem(ItemEntity item);

}
