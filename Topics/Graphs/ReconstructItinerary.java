package Topics.Graphs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import org.junit.Test;

import Topics.TestUtils;

/**
 * Reconstruct itinerary from the tickets
 * tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 
 * Create a map of adjacency list, instead of storing the destination, store the edge index in the list sorted by their destination ordering

 * JFK - 1, 0
 * SFO - 2
 * ATL - 3, 4
 * 
 * Create a visitedEdges set for checking if all edges are covered.
 * Use backtracking:
 * visitedEdges = {}
 * pathStack = ['JFK'];
 * dfs('JFK', visitedEdges, tickets.length, pathStack);
 * 
 * dfs(currNode, visitedEdges, N, PathStack) // currNode = 'ATL', N=5

    for edge in lexically sortededges of currNode
        if edge not in visited set // edge = 4
            visitedEdges.put(edge); // visitedEdges = {1,3,0,2,4}
            pathStack.push(edge.dest); ['JFK', 'ATL', 'JFK','SFO','ATL','SFO']
            if visitedEdges.length == tickets.length (N)
                return reconstructPathFromStack(pathStack);
            path = dfs(edge.dest, visitedEdges, N);
            if(path != null)
                return path;
            visitedEdges.remove(edge);
            pathStack.pop();
    return null;
 * 
*/
public class ReconstructItinerary {
    private static int field;

    @Test
    public void test1() {
        List<List<String>> tickets = List.of(
                List.of("MUC", "LHR"),
                List.of("JFK", "MUC"),
                List.of("SFO", "SJC"),
                List.of("LHR", "SFO"));

        ReconstructItinerary solution = new ReconstructItinerary();
        List<String> itinerary = solution.findItinerary(tickets);
        assertEquals(itinerary, List.of("JFK", "MUC", "LHR", "SFO", "SJC"));
    }

    @Test
    public void test2() {
        List<List<String>> tickets = List.of(
                List.of("JFK", "SFO"),
                List.of("JFK", "ATL"),
                List.of("SFO", "ATL"),
                List.of("ATL", "JFK"),
                List.of("ATL", "SFO"));

        ReconstructItinerary solution = new ReconstructItinerary();
        List<String> itinerary = solution.findItinerary(tickets);
        assertEquals(itinerary, List.of("JFK", "ATL", "JFK", "SFO", "ATL", "SFO"));
    }

    @Test
    public void test3() {
        List<List<String>> tickets = List.of(
                List.of("JFK", "KUL"),
                List.of("JFK", "NRT"),
                List.of("NRT", "JFK"));

        ReconstructItinerary solution = new ReconstructItinerary();
        List<String> itinerary = solution.findItinerary(tickets);
        assertEquals(itinerary, List.of("JFK", "NRT", "JFK", "KUL"));
    }

    @Test
    public void test4() {
        String[][] ticketsArr = { { "EZE", "TIA" }, { "EZE", "AXA" }, { "AUA", "EZE" }, { "EZE", "JFK" },
                { "JFK", "ANU" },
                { "JFK", "ANU" }, { "AXA", "TIA" }, { "JFK", "AUA" }, { "TIA", "JFK" }, { "ANU", "EZE" },
                { "ANU", "EZE" }, { "TIA", "AUA" } };
        List<List<String>> tickets = TestUtils.convert2dArrayToNestedList(ticketsArr);

        ReconstructItinerary solution = new ReconstructItinerary();
        List<String> itinerary = solution.findItinerary(tickets);
        assertEquals(itinerary,
                List.of("JFK", "ANU", "EZE", "AXA", "TIA", "AUA", "EZE", "JFK", "ANU", "EZE", "TIA", "JFK", "AUA"));
    }

    @Test
    public void test5() {
        String[][] ticketsArr = { { "JFK", "SFO" }, { "JFK", "ATL" }, { "SFO", "JFK" }, { "ATL", "AAA" },
                { "AAA", "BBB" }, { "BBB", "ATL" }, { "ATL", "AAA" }, { "AAA", "BBB" }, { "BBB", "ATL" },
                { "ATL", "AAA" }, { "AAA", "BBB" }, { "BBB", "ATL" }, { "ATL", "AAA" }, { "AAA", "BBB" },
                { "BBB", "ATL" }, { "ATL", "AAA" }, { "AAA", "BBB" }, { "BBB", "ATL" }, { "ATL", "AAA" },
                { "AAA", "BBB" }, { "BBB", "ATL" }, { "ATL", "AAA" }, { "AAA", "BBB" }, { "BBB", "ATL" },
                { "ATL", "AAA" }, { "AAA", "BBB" }, { "BBB", "ATL" }, { "ATL", "AAA" }, { "AAA", "BBB" },
                { "BBB", "ATL" }, { "ATL", "AAA" }, { "AAA", "BBB" }, { "BBB", "ATL" }, { "ATL", "AAA" },
                { "AAA", "BBB" }, { "BBB", "ATL" }, { "ATL", "AAA" }, { "AAA", "BBB" }, { "BBB", "ATL" },
                { "ATL", "AAA" }, { "AAA", "BBB" }, { "BBB", "ATL" }, { "ATL", "AAA" }, { "AAA", "BBB" },
                { "BBB", "ATL" }, { "ATL", "AAA" }, { "AAA", "BBB" }, { "BBB", "ATL" }, { "ATL", "AAA" },
                { "AAA", "BBB" }, { "BBB", "ATL" }, { "ATL", "AAA" }, { "AAA", "BBB" }, { "BBB", "ATL" },
                { "ATL", "AAA" }, { "AAA", "BBB" }, { "BBB", "ATL" }, { "ATL", "AAA" }, { "AAA", "BBB" },
                { "BBB", "ATL" }, { "ATL", "AAA" }, { "AAA", "BBB" }, { "BBB", "ATL" }, { "ATL", "AAA" },
                { "AAA", "BBB" }, { "BBB", "ATL" }, { "ATL", "AAA" }, { "AAA", "BBB" }, { "BBB", "ATL" },
                { "ATL", "AAA" }, { "AAA", "BBB" }, { "BBB", "ATL" }, { "ATL", "AAA" }, { "AAA", "BBB" },
                { "BBB", "ATL" }, { "ATL", "AAA" }, { "AAA", "BBB" }, { "BBB", "ATL" }, { "ATL", "AAA" },
                { "AAA", "BBB" }, { "BBB", "ATL" } };
        List<List<String>> tickets = TestUtils.convert2dArrayToNestedList(ticketsArr);

        ReconstructItinerary solution = new ReconstructItinerary();
        List<String> itinerary = solution.findItinerary(tickets);
        assertEquals(itinerary,
                List.of("JFK", "ANU", "EZE", "AXA", "TIA", "AUA", "EZE", "JFK", "ANU", "EZE", "TIA", "JFK", "AUA"));
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, TreeSet<Integer>> graph = this.createAdjacencyMap(tickets);
        Set<Integer> visitedEdges = new HashSet<>();
        Stack<String> pathStack = new Stack<>();
        pathStack.push("JFK");
        return this.visitedEdgesInternal("JFK", visitedEdges, tickets, pathStack, graph);
    }

    private Map<String, TreeSet<Integer>> createAdjacencyMap(List<List<String>> tickets) {
        Comparator<? super Integer> comparator = (t1, t2) -> {
            int destinationComparesTo = tickets.get(t1).get(1).compareTo(tickets.get(t2).get(1));
            if (destinationComparesTo == 0) {
                return t1 - t2;
            }
            return destinationComparesTo;
        };
        Map<String, TreeSet<Integer>> graph = new HashMap<>();
        for (int edgeIndex = 0; edgeIndex < tickets.size(); edgeIndex++) {
            List<String> ticket = tickets.get(edgeIndex);
            String ticketSrc = ticket.get(0);
            graph.putIfAbsent(ticketSrc,
                    new TreeSet<Integer>(comparator));
            graph.get(ticketSrc).add(edgeIndex);
        }
        return graph;
    }

    private List<String> visitedEdgesInternal(String curr, Set<Integer> visitedEdges, List<List<String>> tickets,
            Stack<String> pathStack,
            Map<String, TreeSet<Integer>> graph) {

        if (graph.containsKey(curr) && graph.get(curr) != null) {
            for (Integer edgeIndex : graph.get(curr)) {
                if (!visitedEdges.contains(edgeIndex)) {
                    visitedEdges.add(edgeIndex);
                    String edgeDest = tickets.get(edgeIndex).get(1);
                    pathStack.push(edgeDest);

                    if (visitedEdges.size() == tickets.size()) {
                        return this.createPathFromStack(pathStack);
                    }
                    List<String> path = this.visitedEdgesInternal(edgeDest, visitedEdges, tickets, pathStack, graph);
                    if (path != null)
                        return path;
                    visitedEdges.remove(edgeIndex);
                    pathStack.pop();
                }
            }
        }
        return null;
    }

    private List<String> createPathFromStack(Stack<String> pathStack) {
        List<String> result = new ArrayList<>();
        while (!pathStack.isEmpty()) {
            result.add(0, pathStack.pop());
        }
        return result;
    }
}
