package com.ericsson.gladiators.itemsservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Setter @NoArgsConstructor
public class ItemDTO {

    public ItemDTO(String description, String category, int boardId, int creatorId) {
        this.description = description;
        this.category = category;
        this.boardId = boardId;
        this.creatorId = creatorId;
        this.vote = 0;
    }

    private int id;
    private int creatorId;
    private String description;
    private String category;
    private int vote;
    private int boardId;

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", vote=" + vote +
                ", boardId=" + boardId +
                '}';
    }
}