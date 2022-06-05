package gg.bayes.challenge.rest.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(indexes = {@Index(columnList = "matchId")})
public class Hero {

    @Id
    @GeneratedValue
    private Integer id;
    private String hero;
    private Long matchId;
    private int kills;
}
