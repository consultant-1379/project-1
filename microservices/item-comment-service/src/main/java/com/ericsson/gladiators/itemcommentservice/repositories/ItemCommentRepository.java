package com.ericsson.gladiators.itemcommentservice.repositories;

import com.ericsson.gladiators.itemcommentservice.models.ItemComment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemCommentRepository extends CrudRepository<ItemComment, Integer> {

    List<ItemComment> findAllByItemId (Integer itemId);

    List<ItemComment> findAllByItemIdAndComment (Integer itemId, String comment);

}
