package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    public MatchServiceImpl() {
    }

    @Override
    public Long ingestMatch(String payload) {
        String [] lines = payload.split("\n");
        Stream.of(lines).forEach(this::processLine);
        return 1L;
    }

    private void processLine(String line){



    }


}
