package gg.bayes.challenge.rest.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(indexes = {@Index(columnList = "matchId,heroName")})
public class Damage {
    @Id
    @GeneratedValue
    private int id;
    private String heroName;
    private long matchId;
    private int totalDamage;
    private String target;
    private int damageInstances;
}
