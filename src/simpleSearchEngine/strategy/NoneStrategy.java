package simpleSearchEngine.strategy;

import java.util.*;
import java.util.stream.Collectors;

public class NoneStrategy implements SearchStrategy {
    @Override
    public List<Integer> filter(Map<String, List<Integer>> iIndex, String... queries) {

        var queriesAsList = Arrays.asList(queries);

        // find the lines in which all queries occur
        var any = iIndex.keySet().stream()
                .filter(queriesAsList::contains)
                .map(iIndex::get)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // if there's no line that matches any of the given queries, return an empty list
        if (any.isEmpty()) {
            return Collections.emptyList();
        }

        // get the values and flatten them, so that we can simply subtract the lines that contain any of the
        // queries from all the lines, so that we are left with only the lines that contain none of the queries
        var values = iIndex.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        values.removeAll(any);

        return List.copyOf(values);
    }
}
