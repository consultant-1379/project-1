package com.ericsson.gladiators.itemsservice.repositories;

import com.ericsson.gladiators.itemsservice.models.ItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<ItemEntity, Integer> {

    List<ItemEntity> findAllByBoardId(Integer boardId);
    List<ItemEntity> findAllByCreatorId(Integer creatorId);

}
