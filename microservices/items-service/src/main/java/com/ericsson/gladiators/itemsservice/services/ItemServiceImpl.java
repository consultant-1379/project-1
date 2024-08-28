package com.ericsson.gladiators.itemsservice.services;

import com.ericsson.gladiators.itemsservice.models.ItemEntity;
import com.ericsson.gladiators.itemsservice.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<ItemEntity> getAllItems() {
        List<ItemEntity> items = new ArrayList<>();
        itemRepository.findAll().forEach(items::add);
        return items;
    }

    @Override
    public List<ItemEntity> getItemsByBoardId(Integer boardID) {
        List<ItemEntity> items = new ArrayList<>();
        itemRepository.findAllByBoardId(boardID).forEach(items::add);
        return items;
    }

    @Override
    public ItemEntity getItemsById(Integer itemId) {
        return itemRepository.findById(itemId).orElse(null  );
    }

    @Override
    public List<ItemEntity> getItemsbyCreatorId(Integer creatorId) {
        List<ItemEntity> items = new ArrayList<>();
        itemRepository.findAllByCreatorId(creatorId).forEach(items::add);
        return items;
    }

    @Override
    public String removeItem(Integer itemId) {
        Optional<ItemEntity> item = itemRepository.findById(itemId);

        if(item.isPresent()) {
            itemRepository.deleteById(itemId);
            return "ItemEntity deleted.";
        } else {
            return "Problem deleting item.";
        }

    }

    @Override
    public ItemEntity addItem(ItemEntity item) {
        return itemRepository.save(item);
    }

    @Override
    public String updateItem(String description, Integer itemId) {
        Optional<ItemEntity> itemInDB = itemRepository.findById(itemId);

        if(itemInDB.isPresent()){
            ItemEntity itemToUpdate = itemInDB.get();
            itemToUpdate.setDescription(description);
            itemRepository.save(itemToUpdate);
            return "ItemEntity Updated";
        }

        return "ItemEntity not updated.";
    }

    @Override
    public String itemUpVote(Integer id) {
        Optional<ItemEntity> itemInDB = itemRepository.findById(id);

        if(itemInDB.isPresent()){
            ItemEntity itemToUpdate = itemInDB.get();
            itemToUpdate.setVote(itemToUpdate.getVote()+1);
            itemRepository.save(itemToUpdate);
            return "Up Vote Noticed";
        }

        return "Up Vote did not work.";
    }

    @Override
    public String itemDownVote(Integer id) {
        Optional<ItemEntity> itemInDB = itemRepository.findById(id);

        if(itemInDB.isPresent()){
            ItemEntity itemToUpdate = itemInDB.get();
            itemToUpdate.setVote(itemToUpdate.getVote()-1);
            itemRepository.save(itemToUpdate);
            return "Down Vote Noticed";
        }

        return "Down Vote did not work.";
    }


}
