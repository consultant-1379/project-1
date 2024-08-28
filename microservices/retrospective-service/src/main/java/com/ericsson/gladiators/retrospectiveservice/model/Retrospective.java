package com.ericsson.gladiators.retrospectiveservice.model;



import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "retrospective")
public class Retrospective {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    public Retrospective(){

    }

    public String getTitle() {
        return title;
    }

    public Retrospective(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    //Need to add a list of tasks
    //tasks can be a high level view of stuff that is placed on the retro board



}


/**
 * @Entity public class Retrospective {
 * <p>
 * private int id;
 * <p>
 * private String item;
 * <p>
 * public Retrospective(int id, String task) {
 * this.id = id;
 * this.item = task;
 * }
 * <p>
 * public int getId() {
 * return id;
 * }
 * <p>
 * public void setId(int id) {
 * this.id = id;
 * }
 * <p>
 * public String getItem() {
 * return item;
 * }
 * <p>
 * public void setItem(String item) {
 * this.item = item;
 * }
 * }
 **/