package com.ericsson.gladiators.itemsservice.controllers;

import com.ericsson.gladiators.itemsservice.models.ItemDTO;
import com.ericsson.gladiators.itemsservice.models.ItemEntity;
import com.ericsson.gladiators.itemsservice.services.ItemServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemServiceImpl itemServiceImpl;

    @GetMapping(produces = {"application/json"})
    public List<ItemEntity> getAllItems(){
        return itemServiceImpl.getAllItems();
    }

    @GetMapping(value="/{id}", produces={"application/json"})
    public @ResponseBody
    ItemEntity getAnItem(@PathVariable int id){
        return itemServiceImpl.getItemsById(id);
    }

    @GetMapping(value="/board/{boardId}", produces={"application/json"})
    public @ResponseBody List<ItemEntity> getItemsForBoard(@PathVariable int boardId){
        return itemServiceImpl.getItemsByBoardId(boardId);
    }

    @GetMapping(value="/creator/{creatorId}", produces={"application/json"})
    public @ResponseBody List<ItemEntity> getItemsForCreator(@PathVariable int creatorId){
        return itemServiceImpl.getItemsbyCreatorId(creatorId);
    }

    @GetMapping(value="/delete/{id}")
    public @ResponseBody String deleteItem(@PathVariable int id){
        return itemServiceImpl.removeItem(id);

    }

    @PostMapping(value="/create")
    public ResponseEntity<String> addItem(@RequestBody ItemDTO itemDTO){
        ItemEntity itemEntity = new ItemEntity();
        BeanUtils.copyProperties(itemDTO, itemEntity);
        itemServiceImpl.addItem(itemEntity);
        return ResponseEntity.status(201).body("ItemEntity Created");

    }

    @PostMapping(value="/create/params", produces = {"application/json"})
    public ResponseEntity<ItemEntity> addItem(@RequestParam String description,
                                              @RequestParam String category,
                                              @RequestParam Integer boardId,
                                              @RequestParam Integer creatorId){

        ItemEntity newItem = new ItemEntity(description, category, boardId, creatorId);
        itemServiceImpl.addItem(newItem);

        URI uri = URI.create("/items/"+newItem.getId());
        return ResponseEntity.created(uri).body(newItem);
    }

    @PostMapping(value="/update/params", produces = {"application/json"})
    public @ResponseBody String updateItemDescription(@RequestParam int id,
                                                        @RequestParam String description) {
        return itemServiceImpl.updateItem(description, id);
    }

    @PostMapping(value="/voteup", produces = {"application/json"})
    public @ResponseBody String itemUpVote(@RequestParam int id) {
        return itemServiceImpl.itemUpVote(id);
    }

    @PostMapping(value="/votedown", produces = {"application/json"})
    public @ResponseBody String itemDownVote(@RequestParam int id) {
        return itemServiceImpl.itemDownVote(id);
    }
}
