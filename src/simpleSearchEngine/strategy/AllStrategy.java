package simpleSearchEngine.strategy;
import simpleSearchEngine.Util;

import java.util.*;
import java.util.stream.Collectors;

public class AllStrategy implements SearchStrategy {

    @Override
    public List<Integer> filter(Map<String, List<Integer>> iIndex, String... queries) {
        // get all lines for the values that satisfy any of the queries
        var any = Arrays.stream(queries)
                .filter(iIndex::containsKey)
                .map(iIndex::get)
                .collect(Collectors.toList());

        // if there's no line that matches any of the given queries, return an empty list
        if (any.isEmpty()) {
            return Collections.emptyList();
        }

        // get the lines that are contained in each resulting subset
        return any.stream().reduce(Util::intersect).orElse(Collections.emptyList());
    }
}
