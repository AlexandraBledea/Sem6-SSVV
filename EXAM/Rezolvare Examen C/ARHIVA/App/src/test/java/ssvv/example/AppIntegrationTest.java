package ssvv.example;


import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import repository.Repository;
import service.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;


public class AppIntegrationTest {

    private Repository repo;

    private Service service;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void testAddFeelingValueAndPosition(){
        repo = new Repository(new ArrayList<>(List.of(0, 0, -1, -1, 1)));
        repo.addFeeling(1, 1);
        Assertions.assertEquals(repo.getEmotions().get(1), 1);
    }

    @Test
    public void testAddFeeling(){
        repo = new Repository(new ArrayList<>(List.of(0, 0, -1, -1)));
        repo.addFeeling(1);
        Assertions.assertEquals(repo.getEmotions().get(repo.getEmotions().size()-1), 1);
    }

    @Test
    public void testGetEmotions(){
        var arr = new ArrayList<>(List.of(0, 0, -1, -1));
        repo = new Repository(arr);
        Assertions.assertEquals(arr, repo.getEmotions());
    }

    @Test
    public void testFindSadFeeling(){
        repo = new Repository(new ArrayList<>(List.of(0, 0, 1, -1)));
        int index = repo.findSadFeeling(0);
        Assertions.assertEquals(index, repo.getEmotions().size()-1);
    }

    @Test
    public void testCheckNeighboursHappyFeelingLeft(){
        repo = new Repository(new ArrayList<>(List.of(0, 0, 1, -1)));
        service = new Service(repo);

        Assertions.assertFalse(service.checkNeighbours(repo.getEmotions().size()-1));
    }

    @Test
    public void testCheckNeighboursHappyFeelingRight(){
        repo = new Repository(new ArrayList<>(List.of(0, 0, -1, 1)));
        service = new Service(repo);

        Assertions.assertFalse(service.checkNeighbours(2));
    }


    @Test
    public void testCheckNeighboursHappyFeelingBothDirections(){
        repo = new Repository(new ArrayList<>(List.of(0, 0, 1, -1, 1)));
        service = new Service(repo);

        Assertions.assertTrue(service.checkNeighbours(3));
    }

    @Test
    public void testSurroundByHappyFeelingV1(){
        var arr = new ArrayList<>(List.of(0, 0, -1, 0));
        var arr_2 = new ArrayList<>(List.of(0, 0, 1, -1, 1, 0));
        repo = new Repository(arr);
        service = new Service(repo);


        int index = service.surroundByHappyFeeling(2);
        Assertions.assertEquals(repo.getEmotions().get(index), -1);
        Assertions.assertEquals(repo.getEmotions(), arr_2);

    }

    @Test
    public void testSurroundByHappyFeelingV2(){
        var arr = new ArrayList<>(List.of(0, 0, -1));
        var arr_2 = new ArrayList<>(List.of(0, 0, 1, -1, 1));
        repo = new Repository(arr);
        service = new Service(repo);


        int index = service.surroundByHappyFeeling(2);
        Assertions.assertEquals(repo.getEmotions().size(), index);
        Assertions.assertEquals(repo.getEmotions(), arr_2);

    }

    @Test
    public void testBeHappyV1(){
        var arr = new ArrayList<>(List.of(0, 0, -1, 0));
        var arr_2 = new ArrayList<>(List.of(0, 0, 1, -1, 1, 0));
        repo = new Repository(arr);
        service = new Service(repo);

        service.beHappy();

        Assertions.assertEquals(repo.getEmotions(), arr_2);
    }

    @Test
    public void testBeHappyV2(){
        var arr = new ArrayList<>(List.of(0, 0, -1, 1, 0, -1, -1, 1, 0, -1));
        var arr_2 = new ArrayList<>(List.of(0, 0, 1, -1, 1, 0, 1, -1, 1, -1, 1, 0, 1, -1, 1));
        repo = new Repository(arr);
        service = new Service(repo);

        service.beHappy();

        Assertions.assertEquals(repo.getEmotions(), arr_2);
    }

}
