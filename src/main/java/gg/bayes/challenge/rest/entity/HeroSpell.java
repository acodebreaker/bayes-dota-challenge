package gg.bayes.challenge.rest.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(indexes = {@Index(columnList = "heroName")})
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
