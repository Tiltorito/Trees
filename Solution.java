import trees.AVLMap;
import trees.BSTMap;
import trees.TreeMap;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by harry on 23/03/2018.
 */

public class Solution {
    public static void main(String[] args) {
        BSTMap<Integer, Integer> tree = new BSTMap<>();

        Random random = new Random(System.currentTimeMillis());

        StopWatch BSTwatch = new StopWatch();

        for(int i = 0; i < 100000000; i++) {
            int rand = random.nextInt();
            tree.put(rand,rand);
        }

        System.out.println("BST insertion time: " + BSTwatch.elapsedTime() / 1000 + "s");

        tree = new AVLMap<>();

        random = new Random(System.currentTimeMillis());

        StopWatch AVLwatch = new StopWatch();

        for(int i = 0; i < 100000000; i++) {
            int rand = random.nextInt();
            tree.put(rand,rand);
        }

        System.out.println("AVL insertion time: " + AVLwatch.elapsedTime() / 1000 + "s");

        //tree.levelOrder();
    }
}
