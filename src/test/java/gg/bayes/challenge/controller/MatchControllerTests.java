package gg.bayes.challenge.controller;

import gg.bayes.challenge.rest.controller.MatchController;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.rest.service.HeroService;
import gg.bayes.challenge.rest.service.MatchService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MatchControllerTests {


    @Autowired
    MatchController matchController;

    @MockBean
    MatchService matchService;

    @MockBean
    HeroService heroService;

    private static final Long MOCK_MATCH_ID = 123L;
    private static final String MOCK_HERO_NAME = "HERONAME";
    private static final String MOCK_ITEM = "HEROITEM";
    private static final int MOCK_KILLS = 2;
    private static final Long MOCK_TIMESTAMP = 56789L;
    private static final String MOCK_SPELL = "SPELL";
    private static final int CASTS = 9;
    private static final String MOCK_TARGET = "TARGET";
    private static final int MOCK_DAMAGE = 567;
    private static final int MOCK_DAMAGE_INSTANCES = 10;


    @Test
    public void testIngestMatch(){
        when(matchService.ingestMatch(anyString())).thenReturn(MOCK_MATCH_ID);
        ResponseEntity<Long> actualMatchId = matchController.ingestMatch("payload");
        Assertions.assertEquals(actualMatchId.getBody(),MOCK_MATCH_ID );

    }

    @Test
    public void testGetMatch(){
        when(heroService.getMatchKills(anyLong())).thenReturn(getMockKills());
        ResponseEntity<List<HeroKills>> killsResponse = matchController.getMatch(MOCK_MATCH_ID);
        Assertions.assertEquals(killsResponse.getBody().get(0).getHero(),MOCK_HERO_NAME );
        Assertions.assertEquals(killsResponse.getBody().get(0).getKills(),MOCK_KILLS );

    }

    @Test
    public void testGetItems(){
        when(heroService.getItems(anyLong(), anyString())).thenReturn(getMockItems());
        ResponseEntity<List<HeroItems>> itemsResponse = matchController.getItems(MOCK_MATCH_ID, MOCK_HERO_NAME);
        Assertions.assertEquals(itemsResponse.getBody().get(0).getItem(),MOCK_ITEM );
        Assertions.assertEquals(itemsResponse.getBody().get(0).getTimestamp(),MOCK_TIMESTAMP );

    }

    @Test
    public void testGetSpells(){
        when(heroService.getSpells(anyLong(), anyString())).thenReturn(getMockSpells());
        ResponseEntity<List<HeroSpells>> itemsResponse = matchController.getSpells(MOCK_MATCH_ID, MOCK_HERO_NAME);
        Assertions.assertEquals(itemsResponse.getBody().get(0).getSpell(),MOCK_SPELL );
        Assertions.assertEquals(itemsResponse.getBody().get(0).getCasts(),CASTS );
    }

    @Test
    public void testGetDamage(){
        when(heroService.getDamage(anyLong(), anyString())).thenReturn(getMockDamage());
        ResponseEntity<List<HeroDamage>> matchControllerDamage = matchController.getDamage(MOCK_MATCH_ID, MOCK_HERO_NAME);
        Assertions.assertEquals(matchControllerDamage.getBody().get(0).getDamageInstances(),MOCK_DAMAGE_INSTANCES );
        Assertions.assertEquals(matchControllerDamage.getBody().get(0).getTotalDamage(),MOCK_DAMAGE );
    }

    private List<HeroKills> getMockKills(){
        List<HeroKills> kills = new ArrayList<>();
        HeroKills heroKills = new HeroKills();
        heroKills.setHero(MOCK_HERO_NAME);
        heroKills.setKills(MOCK_KILLS);
        kills.add(heroKills);
        return kills;
    }

    private List<HeroItems> getMockItems(){
        List<HeroItems> items = new ArrayList<>();
        HeroItems heroItems = new HeroItems();
        heroItems.setItem(MOCK_ITEM);
        heroItems.setTimestamp(MOCK_TIMESTAMP);
        items.add(heroItems);
        return items;
    }

    private List<HeroSpells> getMockSpells(){
        List<HeroSpells> spells = new ArrayList<>();
        HeroSpells heroSpells = new HeroSpells();
        heroSpells.setSpell(MOCK_SPELL);
        heroSpells.setCasts(CASTS);
        spells.add(heroSpells);
        return spells;
    }

    private List<HeroDamage> getMockDamage(){
        List<HeroDamage> damages = new ArrayList<>();
        HeroDamage heroDamage = new HeroDamage();
        heroDamage.setTarget(MOCK_TARGET);
        heroDamage.setDamageInstances(MOCK_DAMAGE_INSTANCES);
        heroDamage.setTotalDamage(MOCK_DAMAGE);
        damages.add(heroDamage);
        return damages;
    }

}
