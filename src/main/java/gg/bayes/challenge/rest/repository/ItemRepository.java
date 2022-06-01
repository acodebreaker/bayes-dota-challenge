package gg.bayes.challenge.rest.repository;

import gg.bayes.challenge.rest.entity.Hero;
import gg.bayes.challenge.rest.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item,Integer> {

}
