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
    @Column(name = "name")
    private String heroName;
    @Column(name = "match_id")
    private Long matchId;
    //\private Integer kills;
}
