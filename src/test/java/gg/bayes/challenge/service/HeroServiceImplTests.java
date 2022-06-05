package gg.bayes.challenge.service;

import gg.bayes.challenge.rest.entity.Damage;
import gg.bayes.challenge.rest.entity.Hero;
import gg.bayes.challenge.rest.entity.HeroSpell;
import gg.bayes.challenge.rest.entity.Item;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.rest.repository.DamageRepository;
import gg.bayes.challenge.rest.repository.HeroRepository;
import gg.bayes.challenge.rest.repository.ItemRepository;
import gg.bayes.challenge.rest.repository.SpellRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HeroServiceImplTests {

    @Autowired
    HeroService heroService;

    @MockBean
    ItemRepository itemRepository;

    @MockBean
    HeroRepository heroRepository;

    @MockBean
    SpellRepository spellRepository;

    @MockBean
    DamageRepository damageRepository;

    private static final Long MOCK_MATCH_ID = 123L;
    private static final String MOCK_HERO_NAME = "HERONAME";
    private static final String MOCK_ITEM = "HEROITEM";
    private static final int MOCK_KILLS = 2;
    private static final Long MOCK_TIMESTAMP = 56789L;
    private static final String MOCK_SPELL = "SPELL";
    private static final int MOCK_CASTS = 9;
    private static final String MOCK_TARGET = "TARGET";
    private static final int MOCK_DAMAGE = 567;
    private static final int MOCK_DAMAGE_INSTANCES = 10;

    @Test
    public void testGetItems(){
        when(itemRepository.findByMatchIdAndHeroName(anyLong(),anyString())).thenReturn(getMockItems());
        List<HeroItems> items =heroService.getItems(MOCK_MATCH_ID,MOCK_HERO_NAME);
        Assertions.assertEquals(items.get(0).getTimestamp(), MOCK_TIMESTAMP);
        Assertions.assertEquals(items.get(0).getItem(), MOCK_ITEM);

    }

    @Test
    public void testGetMatchKills(){
        when(heroRepository.findByMatchId(anyLong())).thenReturn(getMockMatchKills());
        List<HeroKills> matchKills =heroService.getMatchKills(MOCK_MATCH_ID);

        Assertions.assertEquals(matchKills.get(0).getKills(), MOCK_KILLS);
        Assertions.assertEquals(matchKills.get(0).getHero(), MOCK_HERO_NAME);

    }

    @Test
    public void testGetSpells(){
        when(spellRepository.findByMatchIdAndHeroName(anyLong(), anyString())).thenReturn(getMockSpells());
        List<HeroSpells> spells =heroService.getSpells(MOCK_MATCH_ID, MOCK_HERO_NAME);

        Assertions.assertEquals(spells.get(0).getCasts(), MOCK_CASTS);
        Assertions.assertEquals(spells.get(0).getSpell(), MOCK_SPELL);

    }

    @Test
    public void testDamage(){
        when(damageRepository.findByMatchIdAndHeroName(anyLong(), anyString())).thenReturn(getMockDamage());
        List<HeroDamage> damages =heroService.getDamage(MOCK_MATCH_ID, MOCK_HERO_NAME);

        Assertions.assertEquals(damages.get(0).getTotalDamage(), MOCK_DAMAGE);
        Assertions.assertEquals(damages.get(0).getDamageInstances(), MOCK_DAMAGE_INSTANCES);

    }

    private List<Damage> getMockDamage() {
        List<Damage> damages = new ArrayList<>();
        Damage damage = new Damage();
        damage.setTarget(MOCK_TARGET);
        damage.setDamageInstances(MOCK_DAMAGE_INSTANCES);
        damage.setTotalDamage(MOCK_DAMAGE);
        damage.setMatchId(MOCK_MATCH_ID);
        damage.setHeroName(MOCK_HERO_NAME);
        damages.add(damage);
        return damages;
    }

    private List<HeroSpell> getMockSpells() {
        List<HeroSpell> spells = new ArrayList<>();
        HeroSpell spell = new HeroSpell();
        spell.setCasts(MOCK_CASTS);
        spell.setHeroName(MOCK_HERO_NAME);
        spell.setMatchId(MOCK_MATCH_ID);
        spell.setSpell(MOCK_SPELL);
        spells.add(spell);
        return spells;
    }

    private List<Hero> getMockMatchKills() {
        List<Hero> matchKills = new ArrayList<>();
        Hero hero = new Hero();
        hero.setKills(MOCK_KILLS);
        hero.setHero(MOCK_HERO_NAME);
        hero.setMatchId(MOCK_MATCH_ID);
        matchKills.add(hero);
        return matchKills;
    }

    private List<Item> getMockItems(){
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setItem(MOCK_ITEM);
        item.setTimestamp(MOCK_TIMESTAMP);
        item.setMatchId(MOCK_MATCH_ID);
        item.setHeroName(MOCK_HERO_NAME);
        items.add(item);
        return items;
    }



}
