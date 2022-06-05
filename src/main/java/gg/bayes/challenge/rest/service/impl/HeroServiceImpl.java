package gg.bayes.challenge.rest.service.impl;

import gg.bayes.challenge.rest.mapper.DamageMapper;
import gg.bayes.challenge.rest.mapper.HeroMapper;
import gg.bayes.challenge.rest.mapper.ItemMapper;
import gg.bayes.challenge.rest.mapper.SpellMapper;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.rest.repository.*;
import gg.bayes.challenge.rest.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroServiceImpl implements HeroService {

    private final SpellRepository spellRepository;
    private final DamageRepository damageRepository;
    private final HeroRepository heroRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final SpellMapper spellMapper;
    private final HeroMapper heroMapper;
    private final DamageMapper damageMapper;

    @Autowired
    public HeroServiceImpl(
            SpellRepository spellRepository,
            DamageRepository damageRepository,
            HeroRepository heroRepository,
            ItemRepository itemRepository,
            ItemMapper itemMapper,
            SpellMapper spellMapper,
            HeroMapper heroMapper,
            DamageMapper damageMapper) {
        this.spellRepository = spellRepository;
        this.damageRepository = damageRepository;
        this.heroRepository = heroRepository;
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.spellMapper = spellMapper;
        this.heroMapper = heroMapper;
        this.damageMapper = damageMapper;
    }

    @Override
    public List<HeroItems> getItems(Long matchId, String heroName) {
        return itemMapper.map(itemRepository.findByMatchIdAndHeroName(matchId, heroName));
    }

    @Override
    public List<HeroKills> getMatchKills(Long matchId) {
        return heroMapper.map(heroRepository.findByMatchId(matchId));
    }

    @Override
    public List<HeroSpells> getSpells(Long matchId, String heroName) {
        return spellMapper.map(spellRepository.findByMatchIdAndHeroName(matchId, heroName));

    }

    @Override
    public List<HeroDamage> getDamage(Long matchId, String heroName) {
        return damageMapper.map(damageRepository.findByMatchIdAndHeroName(matchId, heroName));
    }
}
