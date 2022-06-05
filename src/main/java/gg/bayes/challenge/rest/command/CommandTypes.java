package gg.bayes.challenge.rest.command;


import gg.bayes.challenge.rest.entity.Damage;
import gg.bayes.challenge.rest.entity.Hero;
import gg.bayes.challenge.rest.entity.HeroSpell;
import gg.bayes.challenge.rest.entity.Item;
import gg.bayes.challenge.rest.repository.DamageRepository;
import gg.bayes.challenge.rest.repository.HeroRepository;
import gg.bayes.challenge.rest.repository.ItemRepository;
import gg.bayes.challenge.rest.repository.SpellRepository;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public enum CommandTypes implements Command {

    BUYS {
        @Override
        public void action(String[] inputs, Map<String, Object> repositories, Long matchId) {
            ItemRepository itemRepository = (ItemRepository) repositories.get("itemRepository");
            Item item = new Item();

            item.setHeroName(inputs[1]);
            item.setMatchId(matchId);
            item.setTimestamp(Long.valueOf(inputs[0]));
            item.setItem(inputs[4].substring(inputs[4].lastIndexOf("_") + 1));

            itemRepository.save(item);
        }
    },

    CASTS {
        @Override
        public void action(String[] inputs, Map<String, Object> repositories, Long matchId) {
            SpellRepository spellRepository = (SpellRepository) repositories.get("spellRepository");
            String spell = inputs[4];
            String heroName = inputs[1];

            HeroSpell heroSpellResponse = spellRepository.findByMatchIdAndHeroNameAndSpell(matchId, heroName, spell);
            if (heroSpellResponse == null) {
                heroSpellResponse = new HeroSpell();

                heroSpellResponse.setMatchId(matchId);
                heroSpellResponse.setSpell(spell);
                heroSpellResponse.setHeroName(heroName);

            }
            heroSpellResponse.setCasts(heroSpellResponse.getCasts() + 1);
            spellRepository.save(heroSpellResponse);
        }
    },

    IS {
        @Override
        public void action(String[] inputs, Map<String, Object> repositories, Long matchId) {
            HeroRepository heroRepository = (HeroRepository) repositories.get("heroRepository");
            String killer = inputs[5].substring(StringUtils.lastIndexOf(inputs[5],"_") + 1);
            Hero hero = heroRepository.findByMatchIdAndHero(matchId, killer);
            if (hero == null) {
                hero = new Hero();
                hero.setMatchId(matchId);
                hero.setHero(killer);
            }

            hero.setKills(hero.getKills() + 1);
            heroRepository.save(hero);
        }
    },
    HITS {
        @Override
        public void action(String[] inputs, Map<String, Object> repositories, Long matchId) {
            DamageRepository damageRepository = (DamageRepository) repositories.get("damageRepository");
            String heroName = inputs[1];
            String target = inputs[3];
            Integer damageDone = Integer.valueOf(inputs[7]);
            Damage damage = damageRepository.findByMatchIdAndHeroNameAndTarget(matchId, heroName, target);
            if (damage == null) {
                damage = new Damage();
                damage.setHeroName(heroName);
                damage.setMatchId(matchId);
                damage.setTarget(target);
            }
            damage.setTotalDamage(damage.getTotalDamage() + damageDone);
            damage.setDamageInstances(damage.getDamageInstances() + 1);
            damageRepository.save(damage);

        }

    }


}
