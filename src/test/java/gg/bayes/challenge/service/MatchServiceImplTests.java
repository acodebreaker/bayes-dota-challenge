package gg.bayes.challenge.service;

import gg.bayes.challenge.rest.entity.Item;
import gg.bayes.challenge.rest.repository.DamageRepository;
import gg.bayes.challenge.rest.repository.HeroRepository;
import gg.bayes.challenge.rest.repository.ItemRepository;
import gg.bayes.challenge.rest.repository.SpellRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MatchServiceImplTests {

    @Autowired
    MatchService matchService;

    @MockBean
    ItemRepository itemRepository;

    @MockBean
    HeroRepository heroRepository;

    @MockBean
    SpellRepository spellRepository;

    @MockBean
    DamageRepository damageRepository;

    private static final String MOCK_LINE_BUYS = "[00:28:03.443] npc_dota_hero_death_prophet buys item item_ogre_axe\n";
    private static final String MOCK_LINE_HITS = "[00:28:03.510] npc_dota_hero_bane hits npc_dota_hero_puck with dota_unknown for 12 damage (1099->1087)\n";
    private static final String MOCK_LINE_CASTS = "[00:28:04.710] npc_dota_hero_rubick casts ability rubick_fade_bolt (lvl 4) on npc_dota_hero_puck\n";
    private static final String MOCK_LINE_KILLS ="[00:28:10.242] npc_dota_hero_puck is killed by npc_dota_hero_rubick\n";

    @Test
    public void testIngestMatchWhenBuysItem(){
        matchService.ingestMatch(MOCK_LINE_BUYS);
        verify(itemRepository , times(1)).save( any() );

    }

    @Test
    public void testIngestMatchWhenCasts(){
        matchService.ingestMatch(MOCK_LINE_CASTS);
        verify(spellRepository , times(1)).save( any() );

    }

    @Test
    public void testIngestMatchWhenHits(){
        matchService.ingestMatch(MOCK_LINE_HITS);
        verify(damageRepository , times(1)).save( any() );
    }

    @Test
    public void testIngestMatchWhenKills(){
        matchService.ingestMatch(MOCK_LINE_KILLS);
        verify(heroRepository , times(1)).save( any() );
    }


}
