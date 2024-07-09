package com.example.CoderHack.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.CoderHack.entity.Contestent;
import com.example.CoderHack.repository.CoderHackRepository;

public class CoderHackServiceTest {
    
    @Mock
    private CoderHackRepository coderHackRepository;

    @InjectMocks
    private CoderHackService coderHackService;

    @Captor
    private ArgumentCaptor<Contestent> contestentCaptor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registerContestentTest() {
        Contestent contestent1 = new Contestent("a1", "Amit", 72, null);
        
        String expectedContestent1Name = "Amit";

        when(coderHackRepository.save(contestent1)).thenReturn(contestent1);

        Contestent savedContestent = coderHackService.registerContestent(contestent1);

        String actualContestent1Name = savedContestent.getName();

        assertEquals(expectedContestent1Name, actualContestent1Name);   


    }

    @Test
    public void getAllContestentsTest() {
        Contestent contestent1 = new Contestent("a1", "Amit", 72, null);
        Contestent contestent2 = new Contestent("2", "Sourav", 0, null);

        int expectedContestentCount = 2;
        String expectedContestent1Id = "a1";
        String expectedContestent2Name = "Sourav";

        when(coderHackRepository.findAll()).thenReturn(Arrays.asList(contestent1, contestent2));

        List<Contestent> contestentList = coderHackService.getAllContestents();

        int actualContestentCount = contestentList.size();
        String actualContestent1Id = contestentList.get(0).getUserId();
        String actualContestent2Name = contestentList.get(1).getName();
        
        
        assertEquals(expectedContestentCount, actualContestentCount);
        assertEquals(expectedContestent2Name, actualContestent2Name);
        assertEquals(expectedContestent1Id, actualContestent1Id);
    }

    @Test
    public void getContestentByIdTest() {
        Contestent contestent = new Contestent();
        contestent.setUserId("sh777");
        contestent.setName("Shree");

        String expectedContestentId = "sh777";
        String expectedContestentName = "Shree";

        when(coderHackRepository.findByUserId(expectedContestentId)).thenReturn(Optional.of(contestent));

        Optional<Contestent> savedContestent = coderHackService.getContestentById("sh777");

        String actualContestentName = savedContestent.get().getName();

        assertEquals(expectedContestentName, actualContestentName);
    }

    @Test
    public void updateScoreForContestentTest() {
        int newScore = 45;
        String userId = "a1";
        Contestent exsitingContestent = new Contestent(userId, "Amit", 65, null);
        

        when(coderHackRepository.findByUserId(userId)).thenReturn(Optional.of(exsitingContestent));
        when(coderHackRepository.save(any())).thenReturn(exsitingContestent);

        Contestent updatedContestent = coderHackService.updateScoreForContestent(userId, newScore);

        verify(coderHackRepository).save(contestentCaptor.capture());

        Contestent actualCapturedContestent = contestentCaptor.getValue();

        assertEquals(newScore, actualCapturedContestent.getScore());
        assertEquals("Code Champ", actualCapturedContestent.getBadges().get(1));
        
    }

    @Test
    public void deleteContestent() {
        Contestent contestent1 = new Contestent("a1", "Amit", 72, null);
        int expectedContestentCount = 0;
        
        coderHackService.deleteContestent(contestent1.getUserId());

        verify(coderHackRepository, times(1)).deleteById("a1");

        assertEquals(expectedContestentCount, coderHackService.getAllContestents().size());
        
    }

}
