package service;

import repository.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Clasa Service
 */
public class Service {

    private Repository repo;

    /**
     * Constructorul clasei Service
     * @param repo
     */
    public Service(Repository repo) {
        this.repo = repo;
    }

    public List<Integer> beHappy() {

        int index = repo.findSadFeeling(0);

        while (index < repo.getEmotions().size()) {

            boolean check = checkNeighbours(index);

            if (check) {

                index++;
            }
            else {

                int newIndex = surroundByHappyFeeling(index);

                index = newIndex + 1;
            }

            index = repo.findSadFeeling(index);
        }

        return repo.getEmotions();
    }


    public boolean checkNeighbours(int index) {

        List<Integer> emotions = repo.getEmotions();

        if (index < 0 || index >= emotions.size()) {

            throw new RuntimeException("Invalid index");
        }

        if (emotions.get(index) != -1) {
            throw new RuntimeException("Not a sad feeling");
        }

        if (index == 0) {
            return false;
        }

        if (index == emotions.size() - 1) {
            return false;
        }

        return emotions.get(index - 1) == 1 && emotions.get(index + 1) == 1;
    }


    public int surroundByHappyFeeling(int index) {

        List<Integer> emotions = repo.getEmotions();

        if (emotions.get(index) != -1) {
            throw new RuntimeException("Not a sad feeling");
        }

        if (emotions.size() == 1) {

            repo.addFeeling(0, 1);
            repo.addFeeling(1);
            return 1;
        }

        if (index == 0) {

            repo.addFeeling(0, 1);

            if (emotions.get(2) != 1) {

                repo.addFeeling(2, 1);
            }

            return 1;
        }
        else if (index == emotions.size() - 1) {

            if (emotions.get(emotions.size() - 2) != 1) {

                repo.addFeeling(emotions.size() - 1, 1);
            }

            repo.addFeeling(1);

            return emotions.size();
        }
        else {

            if (emotions.get(index-1) != 1) {

                repo.addFeeling(index, 1);
                index++;
            }

            if (emotions.get(index+1) != 1) {

                repo.addFeeling(index+1, 1);
            }

            return index;
        }
    }
}
