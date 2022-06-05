package gg.bayes.challenge.rest.repository;

import gg.bayes.challenge.rest.entity.Hero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepository extends CrudRepository<Hero,Integer> {

    Hero findByMatchIdAndHeroName(Long matchId, String heroName);
    List<Hero> findByMatchId(Long matchId);
}
