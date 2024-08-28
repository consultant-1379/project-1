package com.ericsson.gladiators.retrospectiveservice.Repository;

import com.ericsson.gladiators.retrospectiveservice.model.Retrospective;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface RetrospectiveRepository extends CrudRepository<Retrospective, Long> {

    List<Retrospective> findByTitle(String title);


}