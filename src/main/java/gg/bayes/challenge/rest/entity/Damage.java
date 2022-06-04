package gg.bayes.challenge.rest.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "damages")
public class Damage {
    @Id
    @GeneratedValue
    private int id;
    private int heroId;
    private long matchId;
    private int totalDamage;
    private String target;
    private int damageInstances;
}
