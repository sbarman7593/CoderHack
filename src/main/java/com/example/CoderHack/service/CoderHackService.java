package com.example.CoderHack.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CoderHack.entity.Contestent;
import com.example.CoderHack.repository.CoderHackRepository;


@Service
public class CoderHackService {
    
    @Autowired
    private CoderHackRepository coderHackRepository;

    public CoderHackService(CoderHackRepository coderHackRepository) {
        this.coderHackRepository = coderHackRepository;
    }

    public Contestent registerContestent(Contestent contestent) {
        return coderHackRepository.save(contestent);
    }

    public List<Contestent> getAllContestents() {
        List<Contestent> allContestents = coderHackRepository.findAll();
        Collections.sort(allContestents, (c1, c2) -> {
            if (c1.getScore() > c2.getScore()) return -1;
            else if (c1.getScore() < c2.getScore()) return 1;
            else return 0;
        });
        return allContestents;
    }

    public Optional<Contestent> getContestentById(String id) {
        return coderHackRepository.findByUserId(id);
    }
    

    public Contestent updateScoreForContestent(String id, int score) {
        // for the given id update the score in db, find the user is present by id then set the new score by setScore()
        Optional<Contestent> existingContestent = coderHackRepository.findByUserId(id);

        if (existingContestent.isPresent()) {
            Contestent contestent = existingContestent.get();
            contestent.setScore(score);

            List<String> badges = new ArrayList<>();
            badges.clear();
            if (score >=60 && score <= 100) {
                badges.add("Code Ninja");
                badges.add("Code Champ");
                badges.add("Code Master");
            }
            else if (score >=30 && score < 60 ) {
                badges.add("Code Ninja");
                badges.add("Code Champ");
            }
            else if (score >=1 && score < 30) {
                badges.add("Code Ninja");
            }
            contestent.setBadges(badges);

            return coderHackRepository.save(contestent);

            // return contestent;
        }
        return null;
    }

    public void deleteContestent(String id) {
        coderHackRepository.deleteById(id);
    }

    public boolean findUserId(String id) {
        Optional<Contestent> contestent = coderHackRepository.findByUserId(id);

        if (contestent.isPresent()) {
            return true;
        }

        return false;
    }

}
