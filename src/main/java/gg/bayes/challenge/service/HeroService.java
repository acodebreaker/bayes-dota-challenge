package gg.bayes.challenge.service;

import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface HeroService {

    List<HeroItems> getItems(Long matchId, String heroName);

    List<HeroKills> getMatchKills( Long matchId);

    List<HeroSpells>getSpells(Long matchId, String heroName) ;

    List<HeroDamage> getDamage(Long matchId, String heroName) ;


}
