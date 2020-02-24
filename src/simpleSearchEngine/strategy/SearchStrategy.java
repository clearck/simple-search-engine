package simpleSearchEngine.strategy;

import java.util.List;
import java.util.Map;

public interface SearchStrategy {

    List<Integer> filter(Map<String, List<Integer>> iIndex, String... queries);
}
