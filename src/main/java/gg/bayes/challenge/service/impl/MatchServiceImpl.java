package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    public MatchServiceImpl() {
    }

    @Override
    public Long ingestMatch(String payload) {
        // TODO
        throw new NotImplementedException("should be implemented by the applicant");
    }
}
