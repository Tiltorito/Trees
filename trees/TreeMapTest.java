package trees;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by harry on 23/03/2018.
 */

class TreeMapTest {
    private TreeMap<Integer, Integer> treeMap;
    private ArrayList<Integer> keys;

    @BeforeEach
    void init() {
        treeMap = new AVLMap<>();
        keys = new ArrayList<>();

        treeMap.put(4, 4);
        treeMap.put(2, 2);
        treeMap.put(8, 8);
        treeMap.put(1, 1);
        treeMap.put(18, 18);
        treeMap.put(6, 6);

        keys.add(4);
        keys.add(2);
        keys.add(8);
        keys.add(1);
        keys.add(18);
        keys.add(6);
    }

    @AfterEach
    @DisplayName("All the keys should exists")
    void testExistenceOfAllKeys() {
        for(Integer key : keys) {
            Integer value = treeMap.get(key);
            assertTrue(key.equals(value));
        }
    }

    @Test
    @DisplayName("Test the size of the treeMap")
    void testSize() {
        assertEquals( 6, treeMap.size());
    }

    @Test
    @DisplayName("It finds elements if they exists in the treeMap")
    void testGet() {
        Integer i = 18;

        assertTrue(treeMap.get(i).equals(i));
    }

    @Test
    @DisplayName("It should return null for elements that does not exist")
    void testGetNull() {
        assertNull(treeMap.get(5));
    }

    @Test
    @DisplayName("It should be able to add new elements on the treeMap")
    void addElement() {
        int prevSize = treeMap.size();

        treeMap.put(5,5);

        assertTrue(treeMap.get(5) == 5); // test that the element is indeed in the treemap

        assertEquals(prevSize + 1, treeMap.size()); // test that the size has been increased

        keys.add(5);
    }

    @Test
    @DisplayName("It should update an entry if the key already exists")
    void updateElement() {
        int prevSize = treeMap.size();

        treeMap.put(2, 5);

        assertTrue(treeMap.get(2) == 5); // test that the element in the specified key has updated

        assertEquals(prevSize, treeMap.size()); // test that size didn't change

        keys.remove(new Integer(2));
    }

    @Test
    @DisplayName("It should remove an element from the treeMap, if the element exists")
    void removeElementThatExists() {
        int prevSize = treeMap.size();

        treeMap.remove(8);

        assertNull(treeMap.get(8));

        assertEquals(prevSize -1, treeMap.size());

        keys.remove(new Integer(8));
    }


    @Test
    @DisplayName("It should nothing happen if the element of the deletion doesn't exists")
    void removeElementThatDoestExists() {
        int prevSize = treeMap.size();

        treeMap.remove(5);

        assertNull(treeMap.get(5));

        assertEquals(prevSize, treeMap.size());
    }

    @Test
    @DisplayName("You should be able to remove every element from the treeMap")
    void removeAllElements() {
        int size = keys.size();
        for(int i = 0; i < size; i++) {
            int prevSize = treeMap.size();

            treeMap.remove(keys.get(0));
            keys.remove(0);
            //testExistenceOfAllKeys();

            assertEquals(prevSize - 1, treeMap.size());
        }

        assertEquals(0, treeMap.size());
    }

    @Test
    @DisplayName("It should return the min element if it does Exists")
    void getMinElement() {
        int key = treeMap.min();

        assertEquals(key, 1);
    }

    @Test
    @DisplayName("It should return null if the min element if it doesnt Exists")
    void getMinElementIfItDoesntExists() {
        Integer key = new BSTMap<Integer, Integer>().min();

        assertNull(key);
    }

    @Test
    @DisplayName("It should return the max element if it does exists")
    void getMaxElement() {
        int key = treeMap.max();

        assertEquals(18, key);
    }

    @Test
    @DisplayName("It should return null if the max element if it doesnt Exists")
    void getMaxElementIfItDoesntExists() {
        Integer key = new BSTMap<Integer, Integer>().max();

        assertNull(key);
    }

    @Test
    @DisplayName("It should return the floor of a key if it does exists")
    void getFloorThatExists() {
        int key = treeMap.floor(3);

        assertEquals(2, key);
    }

    @Test
    @DisplayName("It should return null if the floor of a key it doesnt exists")
    void getFloorThatDoesntExists() {
        Integer key = treeMap.ceil(25);

        assertNull(key);
    }

    @Test
    @DisplayName("It should return the ceil of a key if it does exists")
    void getCeilThatExists() {
        int key = treeMap.ceil(7);

        assertEquals(8, key);
    }

    @Test
    @DisplayName("It should return null if the ceil of a key it doesnt exists")
    void getCeilThatDoesntExists() {
        Integer key = treeMap.ceil(25);

        assertNull(key);
    }

    @Test
    @DisplayName("It should be able to tell that it is non empty")
    void isEmpty() {
        assertFalse(treeMap.isEmpty());
    }

    @Test
    @DisplayName("It should be able to tell if its empty, if it is")
    void isEmpty2() {
        assertTrue(new BSTMap<>().isEmpty());
    }





}