package UberCards.Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

public class Itinerary {
    @Test
    public void basicTest() {
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("MUC", "LHR"),
                Arrays.asList("JFK", "MUC"),
                Arrays.asList("SFO", "SJC"),
                Arrays.asList("LHR", "SFO"));
        FindItinerary sol = new FindItinerary();
        System.out.println(sol.findItinerary(tickets));
    }

    @Test
    public void basicTest2() {
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("JFK", "SFO"),
                Arrays.asList("JFK", "ATL"),
                Arrays.asList("SFO", "ATL"),
                Arrays.asList("ATL", "JFK"),
                Arrays.asList("ATL", "SFO"));
        FindItinerary sol = new FindItinerary();
        System.out.println(sol.findItinerary(tickets));
    }

    @Test
    public void basicTest3() {
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("JFK", "KUL"),
                Arrays.asList("JFK", "NRT"),
                Arrays.asList("NRT", "JFK"));
        FindItinerary sol = new FindItinerary();
        System.out.println(sol.findItinerary(tickets));
    }
}


class FindItinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, List<List<String>>> graph = this.buildGraph(tickets);
        return this.findPath("JFK", graph);
    }

    private List<String> findPath(String start, Map<String, List<List<String>>> graph) {
        String curr = start;
        Set<List<String>> visitedEdges = new HashSet<>();
        List<String> itinerary = new ArrayList<>();
        while (true) {
            itinerary.add(curr);
            List<List<String>> edges = graph.get(curr);
            List<String> selectedEdge = null;
            if (edges != null) {
                Collections.sort(edges, (edge1, edge2) -> {
                    return edge2.get(1).compareTo(edge1.get(1));
                });
                for (List<String> edge : edges) {
                    if (!visitedEdges.contains(edge)) {
                        selectedEdge = edge;
                        visitedEdges.add(selectedEdge);
                        break;
                    }
                }
            }
            if (selectedEdge == null) {
                break;
            }
            curr = selectedEdge.get(1);
        }
        return itinerary;
    }

    // Map of "JFK", List(["JFK", "SFO"], ["JFK", "LJR"])
    private Map<String, List<List<String>>> buildGraph(List<List<String>> tickets) {
        Map<String, List<List<String>>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String start = ticket.get(0);
            graph.putIfAbsent(start, new ArrayList<>());
            graph.get(start).add(ticket);
        }
        return graph;
    }
}
