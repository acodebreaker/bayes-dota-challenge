package gg.bayes.challenge.rest.service.impl;

import gg.bayes.challenge.rest.command.CommandTypes;
import gg.bayes.challenge.rest.input.InputUtils;
import gg.bayes.challenge.rest.repository.DamageRepository;
import gg.bayes.challenge.rest.repository.HeroRepository;
import gg.bayes.challenge.rest.repository.ItemRepository;
import gg.bayes.challenge.rest.repository.SpellRepository;
import gg.bayes.challenge.rest.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private DamageRepository damageRepository;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private SpellRepository spellRepository;

    private Map<String, Object> repositories;

    private static final String DELIMITER = "\n";
    private static final String DAMAGE_REPO = "damageRepository";
    private static final String HERO_REPO = "heroRepository";
    private static final String ITEM_REPO = "itemRepository";
    private static final String SPELL_REPO = "spellRepository";


    @PostConstruct
    public void initialize() {
        repositories = new HashMap<>();
        repositories.put(DAMAGE_REPO, damageRepository);
        repositories.put(HERO_REPO, heroRepository);
        repositories.put(ITEM_REPO, itemRepository);
        repositories.put(SPELL_REPO, spellRepository);
    }

    @Override
    public Long ingestMatch(String payload) {
        Long matchId = System.currentTimeMillis();

        String[] lines = payload.split(DELIMITER);
        Stream.of(lines).filter(line -> !line.isEmpty()).forEach(line -> processLine(line, matchId));
        return matchId;
    }

    private void processLine(String line, Long matchId) {
        String[] inputs = line.split(" ");
        inputs = InputUtils.sanitizeInput(inputs);
        boolean contains = Arrays.stream(CommandTypes.values())
                .map(Enum::toString)
                .collect(Collectors.toList()).contains(inputs[2].toUpperCase());
        if (contains)
            CommandTypes.valueOf(inputs[2].toUpperCase()).action(inputs, repositories, matchId);
    }
}
