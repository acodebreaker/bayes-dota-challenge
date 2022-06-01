package gg.bayes.challenge.rest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "kills")
public class Kill {

    @Id
    @GeneratedValue
    private int id;
    private String heroId;
    private int kills;
    private int matchId;
}
