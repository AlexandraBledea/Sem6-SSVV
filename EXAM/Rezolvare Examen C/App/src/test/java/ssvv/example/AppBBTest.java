package ssvv.example;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import repository.Repository;
import service.Service;

import java.util.Arrays;

public class AppBBTest {

    private Repository repo;
    private Service serv;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void testFindSadFeelingFirstInList() {

        repo = new Repository(Arrays.asList(-1, 0, 0, 1));
        Assertions.assertEquals(0, repo.findSadFeeling(0));
    }

    @Test
    public void testFindSadFeelingLastInList() {

        repo = new Repository(Arrays.asList(1, 0, 0, -1));
        Assertions.assertEquals(repo.getEmotions().size()-1, repo.findSadFeeling(repo.getEmotions().size()-1));
    }

    @Test
    public void testFindSadFeelingWithinBV() {

        repo = new Repository(Arrays.asList(0, 0, -1, 1, 0));
        Assertions.assertEquals(2, repo.findSadFeeling(2));
    }

    @Test
    public void testFindSadFeelingHigherThanBV() {

        repo = new Repository(Arrays.asList(0, 0, -1, 1, 0));
        Assertions.assertEquals(repo.getEmotions().size(), repo.findSadFeeling(7));
    }

    @Test
    public void testFindSadFeelingLowerThanBV() {

        repo = new Repository(Arrays.asList(0, 0, -1, 1, 0));
        Assertions.assertThrows(RuntimeException.class, () -> repo.findSadFeeling(-1));
    }
}
