package gg.bayes.challenge.rest.entity;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
public class Item {

    @Id
    @GeneratedValue
    private int id;
    private String heroName;
    private long timestamp;
    private long matchId;
    private String itemName;
}
