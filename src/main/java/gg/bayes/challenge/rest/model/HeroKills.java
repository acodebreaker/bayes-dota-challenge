package gg.bayes.challenge.rest.model;

import lombok.Builder;
import lombok.Data;

@Data
public class HeroKills {
    private String hero;
    private Integer kills;
}
