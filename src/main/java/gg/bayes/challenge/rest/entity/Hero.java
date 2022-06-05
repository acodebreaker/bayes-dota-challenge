package gg.bayes.challenge.rest.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Hero {

    @Id
    @GeneratedValue
    private Integer id;
    private String hero;
    private Long matchId;
    private int kills;
}
