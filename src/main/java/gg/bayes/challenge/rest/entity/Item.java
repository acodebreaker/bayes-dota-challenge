package gg.bayes.challenge.rest.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue
    private int id;
    private int heroId;
    private long timestamp;
    private int matchId;
}
