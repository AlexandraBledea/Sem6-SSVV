package ssvv.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.Repository;
import service.Service;

import java.util.Arrays;

public class AppWBTest {

    private Repository repo;
    private Service serv;


    @BeforeEach
    public void setup() {

    }

    @Test
    public void testCheckNeighboursLowerThanBV() {

        repo = new Repository(Arrays.asList(0, 0, -1, 1, 0));
        serv = new Service(repo);

        Assertions.assertThrows(RuntimeException.class, () ->  serv.checkNeighbours(-1));
    }

    @Test
    public void testCheckNeighboursHigherThanBV() {

        repo = new Repository(Arrays.asList(0, 0, -1, 1, 0));
        serv = new Service(repo);

        Assertions.assertThrows(RuntimeException.class, () ->  serv.checkNeighbours(5));
    }

    @Test
    public void testCheckNeighboursAtLowerBV() {

        repo = new Repository(Arrays.asList(-1, 0, -1, 1, 0));
        serv = new Service(repo);

        Assertions.assertFalse(serv.checkNeighbours(0));
    }

    @Test
    public void testCheckNeighboursAtHigherBV() {

        repo = new Repository(Arrays.asList(-1, 0, -1, 1, -1));
        serv = new Service(repo);

        Assertions.assertFalse(serv.checkNeighbours(4));
    }

    @Test
    public void testCheckNeighboursNotSadFeeling() {

        repo = new Repository(Arrays.asList(-1, 0, -1, 1, -1));
        serv = new Service(repo);

        Assertions.assertThrows(RuntimeException.class, () -> serv.checkNeighbours(1));
    }

    @Test
    public void testCheckNeighboursWithinBV1() {

        repo = new Repository(Arrays.asList(-1, 0, -1, 1, -1));
        serv = new Service(repo);

        Assertions.assertFalse(serv.checkNeighbours(2));
    }

    @Test
    public void testCheckNeighboursWithinBV2() {

        repo = new Repository(Arrays.asList(-1, 1, -1, 0, -1));
        serv = new Service(repo);

        Assertions.assertFalse(serv.checkNeighbours(2));
    }

    @Test
    public void testCheckNeighboursWithinBV3() {

        repo = new Repository(Arrays.asList(-1, 1, -1, 1, -1));
        serv = new Service(repo);

        Assertions.assertTrue(serv.checkNeighbours(2));
    }
}
