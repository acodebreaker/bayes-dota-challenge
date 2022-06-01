package gg.bayes.challenge.rest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HeroKills {
    private String hero;
    private Integer kills;
}
