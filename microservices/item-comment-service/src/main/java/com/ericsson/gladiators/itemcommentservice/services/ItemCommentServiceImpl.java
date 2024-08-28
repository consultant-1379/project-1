package com.ericsson.gladiators.itemcommentservice.services;

import com.ericsson.gladiators.itemcommentservice.models.ItemComment;
import com.ericsson.gladiators.itemcommentservice.repositories.ItemCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCommentServiceImpl implements ItemCommentService {

    @Autowired
    private ItemCommentRepository itemCommentRepository;


    @Override
    public ItemComment getItemCommentById(Integer itemCommentId) {
        return itemCommentRepository.findById(itemCommentId).orElse(null);
    }

    @Override
    public List<ItemComment> getAllItemComments() {
        List<ItemComment> itemComments = new ArrayList<>();
        itemCommentRepository.findAll().forEach(itemComments::add);
        return itemComments;
    }

    @Override
    public List<ItemComment> getItemCommentsByItemId(Integer itemId) {
        return itemCommentRepository.findAllByItemId(itemId);
    }

    @Override
    public ItemComment addItemComment(ItemComment itemComment) {
        List<ItemComment> itemCommentList = itemCommentRepository.findAllByItemIdAndComment(itemComment.getItemId(), itemComment.getComment());

        if (itemCommentList.size() > 0) {return null;}

        itemComment.setId(0); //sets id to 0 in order to prevent altering existing comments instead of adding a new one.
        return itemCommentRepository.save(itemComment);

    }

}
