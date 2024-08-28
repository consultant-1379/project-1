package com.ericsson.gladiators.itemcommentservice.controllers;

import com.ericsson.gladiators.itemcommentservice.models.ItemComment;
import com.ericsson.gladiators.itemcommentservice.services.ItemCommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemCommentController {

    @Autowired
    private ItemCommentServiceImpl itemCommentServiceImpl;

    @GetMapping(value = "/itemComment/{itemId}")
    public ResponseEntity<Object> getItemCommentsByItemId (@PathVariable Integer itemId){

        List<ItemComment> itemComments = itemCommentServiceImpl.getItemCommentsByItemId(itemId);

        if(itemComments.isEmpty())
            return new ResponseEntity<>(
                "No item comments could be found!",
                HttpStatus.BAD_REQUEST);
        else{
            return ResponseEntity.ok().body(itemComments);
        }
    }



    @PostMapping(value = "/itemComment")
    public ResponseEntity<Object> addItemComment (@RequestBody ItemComment itemComment){
        ItemComment addedItemComment = itemCommentServiceImpl.addItemComment(itemComment);

        if(addedItemComment == null)
            return new ResponseEntity<>(
                    "Could not add new item comment!",
                    HttpStatus.BAD_REQUEST);
        else{
            return ResponseEntity.ok().body(addedItemComment);
        }
    }

}
