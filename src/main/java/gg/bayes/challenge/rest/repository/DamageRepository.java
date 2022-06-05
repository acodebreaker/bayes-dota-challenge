package gg.bayes.challenge.rest.repository;

import gg.bayes.challenge.rest.entity.Damage;
import gg.bayes.challenge.rest.entity.Hero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DamageRepository extends CrudRepository<Damage,Integer> {

    List<Damage> findByMatchIdAndHeroName(Long matchId, String heroName);
    Damage findByMatchIdAndHeroNameAndTarget(Long matchId, String heroName, String target);
}
