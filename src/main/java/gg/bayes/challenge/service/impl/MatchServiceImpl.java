package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.rest.command.Command;
import gg.bayes.challenge.rest.command.CommandTypes;
import gg.bayes.challenge.rest.repository.DamageRepository;
import gg.bayes.challenge.rest.repository.HeroRepository;
import gg.bayes.challenge.rest.repository.ItemRepository;
import gg.bayes.challenge.rest.repository.SpellRepository;
import gg.bayes.challenge.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    DamageRepository damageRepository;

    @Autowired
    HeroRepository heroRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    SpellRepository spellRepository;

    Map<String, Object> repositories;

    private static final String DELIMITER = "\n";


    @PostConstruct
    public void initialize() {
        repositories = new HashMap<>();
        repositories.put("damageRepository", damageRepository);
        repositories.put("heroRepository", heroRepository);
        repositories.put("itemRepository", itemRepository);
        repositories.put("spellRepository", spellRepository);

    }


    @Override
    public Long ingestMatch(String payload) {
        Long matchId = System.currentTimeMillis();

        String[] lines = payload.split(DELIMITER);
        Stream.of(lines).forEach(line -> processLine(line, matchId));
        return matchId;
    }

    private void processLine(String line, Long matchId) {
        String[] inputs = line.split(" ");
        inputs = sanitizeInput(inputs);
        boolean contains = Arrays.stream(CommandTypes.values())
                .map(Enum::toString)
                .collect(Collectors.toList()).contains(inputs[2].toUpperCase());
        if (contains)
            CommandTypes.valueOf(inputs[2].toUpperCase()).action(inputs, repositories, matchId);
    }

    private String[] sanitizeInput(String[] inputs) {

        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].contains("npc_dota_hero_"))
                inputs[i] = StringUtils.substringAfter(inputs[i], "npc_dota_hero_");
        }
        inputs[0] = StringUtils.substringAfter(inputs[0], "[");
        inputs[0] = StringUtils.substringBefore(inputs[0], "]");
        String[] times = inputs[0].split(":");
        long milliseconds = Integer.valueOf(times[0]) * 60 * 60 * 1000;
        milliseconds += Integer.valueOf(times[1]) * 60 * 1000;
        milliseconds += Integer.valueOf(StringUtils.substringBefore(times[2], ".")) * 1000;
        milliseconds += Integer.valueOf(StringUtils.substringAfter(times[2], "."));
        inputs[0] = String.valueOf(milliseconds);
        return inputs;
    }
}
