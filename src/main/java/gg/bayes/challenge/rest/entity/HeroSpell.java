package gg.bayes.challenge.rest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class HeroSpell {

    @GeneratedValue
    @Id
    private Long id;
    private String heroName;
    private String spell;
    private int casts;
    private Long matchId;
}
