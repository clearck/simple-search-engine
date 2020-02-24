package simpleSearchEngine.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides a method to search by
 */
public class SearchContext {

    /**
     * The delimiter to separate the tokens by.
     */
    private static final String DELIMITER = " ";

    /**
     * Strategy to use when searching for the query
     */
    private SearchStrategy strategy;

    /**
     * InvertedIndex to use as the dataset to operate on
     */
    private final Map<String, List<Integer>> iIndex;

    public SearchContext(String[] data) {
        this.iIndex = buildInvertedIndex(data);
    }

    private Map<String, List<Integer>> buildInvertedIndex(String[] data) {
        var map = new HashMap<String, List<Integer>>();

        for (int i = 0, dataLength = data.length; i < dataLength; i++) {
            String line = data[i].toLowerCase();

            var tokens = line.split(DELIMITER);

            for (var token : tokens) {
                var value = map.getOrDefault(token, new ArrayList<>());
                value.add(i);
                map.put(token, value);
            }
        }

        return map;
    }

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Integer> search(String query) {
        var queries = query.split(" ");
        return strategy.filter(iIndex, queries);
    }
}
