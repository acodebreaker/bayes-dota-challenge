package gg.bayes.challenge.rest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HeroSpells {
    private String spell;
    private Integer casts;
}
