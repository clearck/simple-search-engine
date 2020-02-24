package simpleSearchEngine;
import simpleSearchEngine.strategy.AllStrategy;
import simpleSearchEngine.strategy.AnyStrategy;
import simpleSearchEngine.strategy.NoneStrategy;
import simpleSearchEngine.strategy.SearchContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        if (args.length != 2 || !args[0].equals("--data")) {
            System.out.println("Proper usage is: java program --data filename");
            System.exit(0);
        }

        var filename = args[1];
        var data = loadPeopleFromFile(filename);
        var searchContext = new SearchContext(data);

        int a;
        while (true) {
            printMenu();

            a = Integer.parseInt(scanner.nextLine());

            switch (a) {
                case 1:
                    System.out.println("\nSelect a matching search simpleSearchEngine.strategy: ALL, ANY, NONE");
                    var strategy = scanner.nextLine();
                    setSearchStrategy(strategy, searchContext);
                    System.out.println("\nEnter a name or email to search all suitable people.");
                    var query = scanner.nextLine().toLowerCase().trim();
                    var result = query(searchContext, query);
                    printFoundPeople(data, result);
                    break;
                case 2:
                    printAllPeople(data);
                    break;
                case 0:
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("\nIncorrect option! Try again.");
            }
        }
    }

    /**
     * Sets the selected SearchStrategy
     *
     * @param strategy - simpleSearchEngine.strategy to search by
     * @param searchContext - context that uses the simpleSearchEngine.strategy
     */
    private static void setSearchStrategy(String strategy, SearchContext searchContext) {
        switch (strategy) {
            case "ALL":
                searchContext.setStrategy(new AllStrategy());
                break;
            case "NONE":
                searchContext.setStrategy(new NoneStrategy());
                break;
            case "ANY":
                searchContext.setStrategy(new AnyStrategy());
                break;
        }
    }

    private static String[] loadPeopleFromFile(String filename) {
        var file = new File(filename);
        var data = new ArrayList<String>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                data.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.print("No file found: " + filename);
        }

        return data.toArray(new String[]{});
    }

    /**
     * Queries the dataset for the given query
     *
     * @param context - the search algorithm
     * @param query - query containing the tokens to be searched
     * @return a list of lines on which the queried people can be found. An empy list if no
     * people are found
     */
    private static List<Integer> query(SearchContext context, String query) {
        return context.search(query);
    }

    private static void printFoundPeople(String[] data, List<Integer> lines) {
        System.out.println("\nFound people:");
        if (lines.isEmpty()) {
            System.out.println("No matching people found.");
        } else {
            System.out.println(lines.size() + " persons found:");
            for (var i : lines) {
                System.out.println(data[i]);
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
    }

    private static void printAllPeople(String[] people) {
        System.out.println("\n=== List of people ===");

        for (var p : people) {
            System.out.println(p);
        }
    }
}