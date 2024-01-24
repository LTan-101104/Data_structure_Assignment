/*
 * Copyright 2023 Marc Liberatore.
 */

package hashmaps;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;


public class SimpleHashMapTest {
    //  @Rule
    //  public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

    @Before
    public void setup() {
    }

    @Test
    public void testCreation() throws Exception {
        SimpleMap<Integer, Integer> m = new SimpleHashMap<>();
    }

    @Test
    public void testEmpty() throws Exception {
        SimpleMap<Integer, Integer> m = new SimpleHashMap<>();
        assertEquals(0, m.size());
        assertEquals(null, m.get(1));
        assertEquals(new HashSet<>(), m.keys());
    }

    @Test
    public void testPutOne() throws Exception {
        SimpleMap<Integer, Integer> m = new SimpleHashMap<>();
        m.put(1, 10);
    }

    @Test
    public void testGetOne() throws Exception {
        SimpleMap<Integer, Integer> m = new SimpleHashMap<>();
        m.put(1, 10);
        assertEquals(Integer.valueOf(10), m.get(1));
        assertEquals(null, m.get(2));
    }

    @Test
    public void testGetOrDefaultOne() throws Exception {
        SimpleMap<Integer, Integer> m = new SimpleHashMap<>();
        m.put(1, 10);
        assertEquals(Integer.valueOf(10), m.getOrDefault(1, 20));
        assertEquals(Integer.valueOf(20), m.getOrDefault(2, 20));
    }

    @Test
    public void testSizeOne() throws Exception {
        SimpleMap<Integer, Integer> m = new SimpleHashMap<>();
        m.put(1, 10);
        assertEquals(1, m.size());
    }

    @Test
    public void testSizeTwo() throws Exception {
        SimpleMap<Integer, Integer> m = new SimpleHashMap<>();
        m.put(1, 10);
        m.put(2, 20);
        assertEquals(2, m.size());
    }

    @Test
    public void testreplaceOne() throws Exception {
        SimpleMap<Integer, Integer> m = new SimpleHashMap<>();
        m.put(1, 10);
        assertEquals(Integer.valueOf(10), m.get(1));
        m.put(1, 20);
        assertEquals(Integer.valueOf(20), m.get(1));
        assertEquals(1, m.size());
    }

    @Test
    public void testRemoveOne() throws Exception {
        SimpleMap<Integer, Integer> m = new SimpleHashMap<>();
        m.put(1, 10);
        assertEquals(null, m.remove(2));
        assertEquals(1, m.size());

        assertEquals(Integer.valueOf(10), m.remove(1));
        assertEquals(0, m.size());
    }

    @Test
    public void testManyActions() throws Exception {
        final int ACTIONS = 1000;
        final int BOUND = 100;
        Random random = new Random(0);

        HashMap<Integer, Integer> hm = new HashMap<>();
        SimpleHashMap<Integer, Integer> m = new SimpleHashMap<>();

        for (int i = 0; i < ACTIONS; i++) {
            double d = random.nextDouble();
            if (d < 0.35) {
                // put
                int k = random.nextInt(BOUND);
                int v = random.nextInt(BOUND);
                m.put(k, v);
                hm.put(k, v);
                assertEquals(Integer.valueOf(v), m.get(k));
                assertEquals(hm.size(), m.size());
            } else if (d < 0.65) {
                // remove
                int k = random.nextInt(BOUND);
                assertEquals(hm.remove(Integer.valueOf(k)), m.remove(Integer.valueOf(k)));
            } else if (d < 0.85) {
                // get
                int k = random.nextInt(BOUND);
                assertEquals(hm.get(k), m.get(k));
            } else {
                // getOrDefault
                int k = random.nextInt(BOUND);
                assertEquals(hm.getOrDefault(k, BOUND), m.getOrDefault(k, BOUND));
            }
            assertEquals(hm.size(), m.size());
        }
    }
    
    @Test
    public void testManyActions1000() throws Exception {
        final int ROUNDS = 1000;
        final int ACTIONS = 1000;
        final int BOUND = 100;
        Random random = new Random(0);

        for (int round = 0; round < ROUNDS; round++) {
            HashMap<Integer, Integer> hm = new HashMap<>();
            SimpleHashMap<Integer, Integer> m = new SimpleHashMap<>();

            for (int i = 0; i < ACTIONS; i++) {
                double d = random.nextDouble();
                if (d < 0.35) {
                    // put
                    int k = random.nextInt(BOUND);
                    int v = random.nextInt(BOUND);
                    m.put(k, v);
                    hm.put(k, v);
                    assertEquals(Integer.valueOf(v), m.get(k));
                    assertEquals(hm.size(), m.size());
                } else if (d < 0.65) {
                    // remove
                    int k = random.nextInt(BOUND);
                    assertEquals(hm.remove(Integer.valueOf(k)), m.remove(Integer.valueOf(k)));
                } else if (d < 0.85) {
                    // get
                    int k = random.nextInt(BOUND);
                    assertEquals(hm.get(k), m.get(k));
                } else {
                    // getOrDefault
                    int k = random.nextInt(BOUND);
                    assertEquals(hm.getOrDefault(k, BOUND), m.getOrDefault(k, BOUND));
                }
                assertEquals(hm.size(), m.size());
            }
        }
    }

}
