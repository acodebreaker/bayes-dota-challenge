package gg.bayes.challenge.rest.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    private String heroName;
    private Long timestamp;
    private Long matchId;
    private String item;
}
