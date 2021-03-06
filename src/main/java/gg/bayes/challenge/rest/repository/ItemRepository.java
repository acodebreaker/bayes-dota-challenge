package gg.bayes.challenge.rest.repository;

import gg.bayes.challenge.rest.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item ,Integer> {
    public List<Item> findByMatchIdAndHeroName(Long matchId, String heroName);
}
