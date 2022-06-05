package gg.bayes.challenge.rest.command;

import java.util.Map;

public interface Command {

    default void action(String[] inputs, Map<String, Object> repositories, Long matchId){

    }
}
