package com.ericsson.gladiators.retrospectiveservice.service;

import com.ericsson.gladiators.retrospectiveservice.Repository.RetrospectiveRepository;
import com.ericsson.gladiators.retrospectiveservice.model.Retrospective;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RetrospectiveServiceImpl implements RetrospectiveService {

    @Autowired
    private RetrospectiveRepository retrospectiveRepository;


    @Override
    public List<Retrospective> getAllRetrospectiveBoards() {
        List<Retrospective> items = new ArrayList<>();
        retrospectiveRepository.findAll().forEach(items::add);
        return items;
    }

    @Override
    public Retrospective getRetrospectiveById(long id) {
        return retrospectiveRepository.findById(id).orElse(null  );
    }

    @Override
    public List<Retrospective> getRetrospectiveByTitle(String title) {
        return null;
    }


    public boolean deleteRetrospective(long retrospectiveId) {
        Optional<Retrospective> item = retrospectiveRepository.findById(retrospectiveId);

        if(item.isPresent()) {
            retrospectiveRepository.deleteById(retrospectiveId);
            return true;
        } else {
            return false;
        }


    }

    @Override
    public Retrospective createNewRetrospective(Retrospective retrospective) {
        return retrospectiveRepository.save(retrospective);

    }




}