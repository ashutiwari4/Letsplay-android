package ashutosh.letsplay.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by ashutosh on 3/4/17.
 */

public class IdGenerator {

    private static IdGenerator idGenerator;
    private Set<Integer> ids;


    private IdGenerator() {
        ids = new HashSet<>();
    }


    public static IdGenerator getInstance() {
        if (idGenerator == null) {
            idGenerator = new IdGenerator();
        }
        return idGenerator;
    }

    public boolean add(int id) {
        return ids.add(id);
    }

    public boolean remove(int id) {
        return ids.remove(id);
    }

    public int getAId() {
        for (int i = 0; i < ids.size(); i++) {
            Random rand = new Random();
            int n = rand.nextInt(5000) + 1;
            if(ids.add(n)){
                return n;
            }
        }
        return -1;
    }

}
