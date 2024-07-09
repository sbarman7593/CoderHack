package com.example.CoderHack.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CoderHack.entity.Contestent;
import com.example.CoderHack.service.CoderHackService;

@RestController
@RequestMapping("/users")
public class CoderHackController {
    
    @Autowired
    private CoderHackService coderHackService;

    public CoderHackController(CoderHackService coderHackService) {
        this.coderHackService = coderHackService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Contestent>> getAllContestents() {
        List<Contestent> contestents = coderHackService.getAllContestents();
        return ResponseEntity.ok(contestents);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getContestentById(@PathVariable("userId") String userId) {
        Optional<Contestent> contestent = coderHackService.getContestentById(userId);

        if (contestent.isPresent()) {
            return ResponseEntity.ok(contestent.get());
        }
        else {
            return ResponseEntity.status(404).body("{\"error\": \"Contestent not found\"}");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> registerContestent(@RequestBody Contestent contestent) {
        if (contestent == null) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }

        if (contestent.getName() == null || contestent.getName().isEmpty()) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        
        if (contestent.getScore() != 0 || contestent.getBadges().size() != 0) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("{\"error\": \"Not a valid data.\"}");
        }

        if (contestent.getUserId() == null || contestent.getUserId().isEmpty()) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
        else if (coderHackService.findUserId(contestent.getUserId())) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.CONFLICT).body("{\"error\": \"Given User ID already present!\"}");
        }

        
        Contestent savedContestent = coderHackService.registerContestent(contestent);
        return ResponseEntity.ok().body("{\"Successfully registered with id\": \"" + savedContestent.getUserId() + "\"}");
        
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateScoreForContestent(@PathVariable("userId") String userId, @RequestBody Contestent contestent) {
        Optional<Contestent> contestents = coderHackService.getContestentById(userId);

        if (contestents.isPresent()) {
            coderHackService.updateScoreForContestent(userId, contestent.getScore());
            return ResponseEntity.ok().body("{\"Successfully updated the score for the id\": \"" + userId + "\"}");
        }
        else {
            return ResponseEntity.status(404).body("{\"error\": \"Contestent not found\"}");
        }
       
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteContestent(@PathVariable("userId") String userId) {
        Optional<Contestent> contestent = coderHackService.getContestentById(userId);

        if (contestent.isPresent()) {
            coderHackService.deleteContestent(userId);
            return ResponseEntity.ok().body("{\"Successfully deleted the id\": \"" + userId + "\"}");
        }
        else {
            return ResponseEntity.status(404).body("{\"error\": \"Contestent not found\"}");
        }
    }

}
