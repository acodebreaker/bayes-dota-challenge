package gg.bayes.challenge.rest.command;


import gg.bayes.challenge.rest.entity.Damage;
import gg.bayes.challenge.rest.entity.Hero;
import gg.bayes.challenge.rest.entity.HeroSpell;
import gg.bayes.challenge.rest.entity.Item;
import gg.bayes.challenge.rest.repository.DamageRepository;
import gg.bayes.challenge.rest.repository.HeroRepository;
import gg.bayes.challenge.rest.repository.ItemRepository;
import gg.bayes.challenge.rest.repository.SpellRepository;

import java.util.Map;

public enum CommandTypes implements Command {

    BUYS {
        @Override
        public Integer action(String[] inputs, Map<String, Object> repositories, Long matchId) {
            ItemRepository itemRepository = (ItemRepository) repositories.get("itemRepository");
            Item item = new Item();

            item.setHeroName(inputs[1].substring(inputs[1].lastIndexOf("_") + 1));
            item.setMatchId(matchId);
            item.setTimestamp(matchId);
            item.setItem(inputs[4].substring(inputs[4].lastIndexOf("_") + 1));

            itemRepository.save(item);
            return 1;
        }
    },

    CASTS {
        @Override
        public Integer action(String[] inputs, Map<String, Object> repositories, Long matchId) {
            SpellRepository spellRepository = (SpellRepository) repositories.get("spellRepository");
            String spell = inputs[4];
            String heroName = inputs[1].substring(inputs[1].lastIndexOf("_") + 1);

            HeroSpell heroSpellResponse =spellRepository.findByMatchIdAndHeroNameAndSpell(matchId,heroName,spell);
            if(heroSpellResponse==null) {
                HeroSpell heroSpell = new HeroSpell();

                heroSpell.setMatchId(matchId);
                heroSpell.setSpell(spell);
                heroSpell.setHeroName(heroName);
                heroSpell.setCasts(1);
                spellRepository.save(heroSpell);

            }
            else
            {
                heroSpellResponse.setCasts(heroSpellResponse.getCasts() + 1);
                spellRepository.save(heroSpellResponse);
            }
            return 1;
        }
    },

    IS {
        @Override
        public Integer action(String[] inputs, Map<String, Object> repositories, Long matchId) {
            HeroRepository heroRepository = (HeroRepository) repositories.get("heroRepository");
            String killer = inputs[5].substring(inputs[1].lastIndexOf("_") + 1);
            Hero hero =heroRepository.findByMatchIdAndHeroName(matchId, killer);
            if(hero==null){
                hero = new Hero();
                hero.setMatchId(matchId);
                hero.setHero(killer);
                hero.setKills(1);
                heroRepository.save(hero);
            }
            else{
                hero.setKills(hero.getKills() + 1);
                heroRepository.save(hero);
            }
            return 1;
        }
    },
    HITS {
        @Override
        public Integer action(String[] inputs, Map<String, Object> repositories, Long matchId) {
            DamageRepository damageRepository = (DamageRepository) repositories.get("damageRepository");
            String heroName = inputs[1].substring(inputs[1].lastIndexOf("_") + 1);
            String target = inputs[3].substring(inputs[3].lastIndexOf("_") + 1);
            Integer damageDone = Integer.valueOf(inputs[6]);
            Damage damage = damageRepository.findByMatchIdAndHeroNameAndTarget(matchId, heroName, target);
            if(damage == null){
                Damage damages = new Damage();
                damages.setDamageInstances(1);
                damages.setHeroName(heroName);
                damages.setMatchId(matchId);
                damages.setTotalDamage(damageDone);
                damageRepository.save(damages);

            }
            else{
                damage.setTotalDamage(damage.getTotalDamage() + damageDone);
                damage.setDamageInstances(damage.getDamageInstances() + 1);
                damageRepository.save(damage);
            }

            return 1;
        }

    },
    USES {
        @Override
        public Integer action(String[] inputs, Map<String, Object> repositories, Long matchId) {
            return null;
        }

    },


}
