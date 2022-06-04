package gg.bayes.challenge.rest.command;

import java.util.Map;

public interface Command {

    default public Integer action(String[] inputs, Map<String, Object> repositories, Long matchId){
        return null;
    }
}
