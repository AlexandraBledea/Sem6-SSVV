package app;

import repository.Repository;
import service.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainApplication {

    public static void main(String[] args) {

        int[] arr2 = new int[]{1, -1, 1, -1, 1, 0, 0, 1, 1, -1, 1, 0, 1, -1, 1, 0, 1, 1, -1, 1, 0, 1, 1};

        Repository repo = new Repository(new ArrayList<>(List.of(-1, -1, 0, 0, 1, 1, -1, 1, 0, -1, 1, 0, 1, 1, -1, 0, 1, 1)));

        Service serv = new Service(repo);

        var newList = serv.beHappy();

        System.out.println(newList);
        System.out.println(Arrays.toString(arr2));
    }

}
