package com.ericsson.gladiators.retrospectiveservice.service;

import com.ericsson.gladiators.retrospectiveservice.model.Retrospective;

import java.util.List;
import java.util.Optional;

public interface RetrospectiveService {

    List<Retrospective> getAllRetrospectiveBoards();

   Retrospective getRetrospectiveById(long id);

    List<Retrospective> getRetrospectiveByTitle(String title);

    boolean deleteRetrospective(long retrospective);

    Retrospective createNewRetrospective(Retrospective retrospective);





}
