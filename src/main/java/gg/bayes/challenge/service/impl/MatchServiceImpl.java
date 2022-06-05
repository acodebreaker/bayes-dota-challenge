package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.rest.command.Command;
import gg.bayes.challenge.rest.command.CommandTypes;
import gg.bayes.challenge.rest.repository.DamageRepository;
import gg.bayes.challenge.rest.repository.HeroRepository;
import gg.bayes.challenge.rest.repository.ItemRepository;
import gg.bayes.challenge.rest.repository.SpellRepository;
import gg.bayes.challenge.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {

    @PostConstruct
    public void initializing() {
        repositories = new HashMap<>();
        repositories.put("damageRepository", damageRepository);
        repositories.put("heroRepository", heroRepository);
        repositories.put("itemRepository", itemRepository);
        repositories.put("spellRepository", spellRepository);

    }

    Map<String, Object> repositories;

    @Autowired
    DamageRepository damageRepository;

    @Autowired
    HeroRepository heroRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    SpellRepository spellRepository;


    @Override
    public Long ingestMatch(String payload) {
        Long matchId = System.currentTimeMillis();

        String[] lines = payload.split("\n");
        Stream.of(lines).forEach(line -> processLine(line, matchId));
        return matchId;
    }

    private void processLine(String line, Long matchId) {
        String[] inputs = line.split(" ");
        boolean contains = Arrays.stream(CommandTypes.values())
                .map(Enum::toString)
                .collect(Collectors.toList()).contains(inputs[2].toUpperCase());
        if(contains)
            CommandTypes.valueOf(inputs[2].toUpperCase()).action(inputs, repositories, matchId);
    }
}
