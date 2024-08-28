package com.ericsson.gladiators.itemcommentservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class ItemComment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer itemId;
    private String comment;


    public ItemComment(Integer id, Integer itemId, String comment) {
        this.id = id;
        this.itemId = itemId;
        this.comment = comment;
    }


    public ItemComment(Integer itemId, String comment) {
        this.itemId = itemId;
        this.comment = comment;
    }

    public ItemComment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ItemComment{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
