package gg.bayes.challenge.rest.command;


import gg.bayes.challenge.rest.entity.Item;
import gg.bayes.challenge.rest.repository.ItemRepository;

import java.util.Map;

public enum CommandTypes implements Command {

    BUYS {
        @Override
        public Integer action(String[] inputs, Map<String, Object> repositories, Long matchId) {
            ItemRepository itemRepository = (ItemRepository) repositories.get("itemRepository");
            Item item = Item.builder().
                    heroName(inputs[1]).
                    matchId(matchId).
                    timestamp(matchId).
                    itemName(inputs[4]).
                    build();

            itemRepository.save(item);
            return 1;
        }
    },

    CASTS {
        @Override
        public Integer action(String[] inputs, Map<String, Object> repositories, Long matchId) {
            return null;
        }
    },

    HITS {
        @Override
        public Integer action(String[] inputs, Map<String, Object> repositories, Long matchId) {
            return null;
        }
    },
    STATE {
        @Override
        public Integer action(String[] inputs, Map<String, Object> repositories, Long matchId) {
            return null;
        }

    },
    USES {
        @Override
        public Integer action(String[] inputs, Map<String, Object> repositories, Long matchId) {
            return null;
        }

    },


}
