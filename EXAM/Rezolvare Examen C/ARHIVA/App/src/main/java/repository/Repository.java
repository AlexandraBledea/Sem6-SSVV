package repository;

import java.util.List;

public class Repository {

    private final List<Integer> emotions;

    public Repository(List<Integer> emotions) {
        this.emotions = emotions;
    }

    public int findSadFeeling(int index) {

        if (index < 0) {

            throw new RuntimeException("Invalid index");
        }

        for (int i = index; i < emotions.size(); i++) {
            if (emotions.get(i) == -1) {
                return i;
            }
        }

        return emotions.size();
    }

    public void addFeeling(int index, int value) {

        emotions.add(index, value);
    }

    public void addFeeling(int value) {

        emotions.add(value);
    }

    public List<Integer> getEmotions() {
        return emotions;
    }
}
