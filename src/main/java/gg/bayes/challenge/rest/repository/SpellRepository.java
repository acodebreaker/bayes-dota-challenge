package gg.bayes.challenge.rest.repository;

import gg.bayes.challenge.rest.entity.Hero;
import gg.bayes.challenge.rest.entity.HeroSpell;
import gg.bayes.challenge.rest.model.HeroSpells;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpellRepository extends CrudRepository<HeroSpell,Integer> {

    List<HeroSpell> findByMatchIdAndHeroId(Long matchId, Integer heroId);
}
