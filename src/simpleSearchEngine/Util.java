package simpleSearchEngine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class Util {

    /**
     * Subtracts two given lists by finding intersecting elements.
     *
     * @param l1
     * @param l2
     * @return list with intersecting elements of both given lists
     */
    public static List<Integer> intersect(List<Integer> l1, List<Integer> l2) {
        List<Integer> result = new ArrayList<>();
        for (Integer i : l1) {
            if (l2.contains(i)) {
                result.add(i);
            }
        }
        return result;
    }
}
