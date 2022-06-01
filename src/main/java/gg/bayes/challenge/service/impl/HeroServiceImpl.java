package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.rest.entity.Hero;
import gg.bayes.challenge.rest.entity.Kill;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.rest.repository.DamageRepository;
import gg.bayes.challenge.rest.repository.HeroRepository;
import gg.bayes.challenge.rest.repository.KillsRepository;
import gg.bayes.challenge.rest.repository.SpellRepository;
import gg.bayes.challenge.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HeroServiceImpl implements HeroService {

    private final KillsRepository killsRepository;
    private final SpellRepository spellRepository;
    private final DamageRepository damageRepository;
    private final HeroRepository heroRepository;

    @Autowired
    public HeroServiceImpl(KillsRepository killsRepository, SpellRepository spellRepository, DamageRepository damageRepository, HeroRepository heroRepository) {
        this.killsRepository = killsRepository;
        this.spellRepository = spellRepository;
        this.damageRepository = damageRepository;
        this.heroRepository = heroRepository;
    }

    @Override
    public List<HeroItems> getItems(Long matchId, String heroName) {
        return null;
    }

    @Override
    public List<HeroKills> getMatchKills(Long matchId) {
        List<Kill> kills = killsRepository.findByMatchId(matchId);
        return kills.stream().map(kill -> {
            return HeroKills.builder()
                    .kills(kill.getKills())
                    .hero(kill.getHeroId())
                    .build();

        }).collect(Collectors.toList());
    }

    @Override
    public List<HeroSpells> getSpells(Long matchId, String heroName) {
        Integer heroId = getHeroId(heroName, matchId);
        return Optional.ofNullable(spellRepository.findByMatchIdAndHeroId(matchId, heroId))
                .orElseGet(ArrayList::new)
                .stream()
                .map(spell -> HeroSpells.builder()
                        .casts(spell.getCasts())
                        .spell(spell.getSpell())
                        .build())
                .collect(Collectors.toList());
    }

    private Integer getHeroId(String heroName, Long matchId) {
        return heroRepository.findByMatchIdAndHeroName(matchId, heroName)
                .map(Hero::getId)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }

    @Override
    public List<HeroDamage> getDamage(Long matchId, String heroName) {
        Integer heroId = getHeroId(heroName, matchId);
        return Optional.ofNullable(damageRepository.findByMatchIdAndHeroId(matchId, heroId))
                .orElseGet(ArrayList::new)
                .stream()
                .map(damage -> HeroDamage.builder()
                        .target(damage.getTarget())
                        .damageInstances(damage.getDamageInstances())
                        .totalDamage(damage.getTotalDamage())
                        .build())
                .collect(Collectors.toList());
    }
}
