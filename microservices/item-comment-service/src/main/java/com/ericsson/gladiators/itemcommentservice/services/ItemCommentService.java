package com.ericsson.gladiators.itemcommentservice.services;

import com.ericsson.gladiators.itemcommentservice.models.ItemComment;

import java.util.List;

public interface ItemCommentService {

    List<ItemComment> getItemCommentsByItemId(Integer itemId);

    ItemComment addItemComment(ItemComment itemComment);


    ItemComment getItemCommentById(Integer itemId);

    List<ItemComment> getAllItemComments();
}
