package gg.bayes.challenge.rest.repository;

import gg.bayes.challenge.rest.entity.Kill;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KillsRepository extends CrudRepository<Kill, Integer> {


    public List<Kill> findByMatchId(Long matchId);

}
