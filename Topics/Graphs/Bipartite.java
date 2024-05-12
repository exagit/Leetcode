package Topics.Graphs;

import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

/**
queue<Integer> q = [3], Set<Integer> visited = [3]
Map<Integer, Set<Integer>> set - { // 
 0-[3]
}
curr=3, q-[0,2], visited=[3,0,2] 
set-{
0-[3],
1-[0,2]
}

curr=0, q-[2,1], visited=[3,0,1]
set-{
0-[3,1],
1-[0,2]
}
**/
public class Bipartite {

    @Test
    public void test1() {
        int[][] graph = { { 1, 3 }, { 0, 2 }, { 1, 3 }, { 0, 2 } };

        /**
         * 0 -1 -2
         * |   /
         * 3
         */
        boolean result = new Bipartite().isBipartite(graph);
        assertTrue(result);
    }

    public boolean isBipartite(int[][] graph) {
        Queue<int[]> q = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Set<Integer>> biset = new HashMap<>();

        biset.put(0, new HashSet<>());
        biset.put(1, new HashSet<>());
        for (int i = 0; i < graph.length; i++) {
            if (!visited.contains(i)) {
                boolean isBipartite = this.bfsVisit(q, visited, biset, i, graph);
                if (!isBipartite) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean bfsVisit(Queue<int[]> q, Set<Integer> visited, Map<Integer, Set<Integer>> biset, int i,
            int[][] graph) {
        visited.add(i);
        q.add(new int[] { i, 0 });
        biset.get(0).add(i); // add the root element to set 0, it is safe to add all disconnected nodes to set 0
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int currNode = curr[0];
            int currSet = curr[1];
            int[] neighbors = graph[currNode];
            int neighborSet = this.invert(currSet);
            for (int neighbor : neighbors) {
                if (biset.get(currSet).contains(neighbor)) {
                    // the neighbor is already in the same set as current node, return false as the graph is not bipartite
                    return false;
                }
                // now the node can either be present in the other set or not be present in any set in any case we assign it to the other set and continue
                biset.get(neighborSet).add(neighbor);

                // add it to queue if not visited
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    q.add(new int[] { neighbor, neighborSet });
                }
            }
        }
        return true;
    }

    private int invert(int currSet) {
        if (currSet == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
