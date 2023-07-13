package Topics.Graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import org.junit.Test;

public class AlienDictionary {
    @Test
    public void testBasic() {
        String[] words = { "fs", "ft", "t", "s" };
        OrderBuilder sol = new OrderBuilder();
        System.out.println(sol.alienOrder(words));
    }

    @Test
    public void testBasic2() {
        String[] words = { "a", "b", "ca", "cc" };
        OrderBuilder sol = new OrderBuilder();
        System.out.println(sol.alienOrder(words));
    }

    @Test
    public void testBasic3() {
        String[] words = { "abc", "ab" };
        OrderBuilder sol = new OrderBuilder();
        System.out.println(sol.alienOrder(words));
    }
}


class OrderBuilder {
    public String alienOrder(String[] words) {
        TrieNode root = this.buildTrie(words);
        List<List<Character>> orderings = this.getOrderingStrings(root);
        return this.getCombinedOrdering(orderings);
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode('0');
        for (String word : words) {
            word = word + "0";
            TrieNode cursor = root;
            for (char c : word.toCharArray()) {
                TrieNode child, lastChild;
                if (cursor.children.size() > 0
                        && (lastChild = cursor.children.get(cursor.children.size() - 1)).c == c) {
                    child = lastChild;
                } else {
                    child = new TrieNode(c);
                    cursor.children.add(child);
                }
                cursor = child;
            }
        }
        return root;
    }

    private List<List<Character>> getOrderingStrings(TrieNode root) {
        List<List<Character>> orderings = new ArrayList<>();
        ArrayDeque<TrieNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TrieNode curr = queue.pop();
            if (curr.children.size() > 0) {
                List<Character> ordering =
                        curr.children.stream().map(t -> t.c).collect(Collectors.toList());
                orderings.add(ordering);
            }

            for (TrieNode child : curr.children) {
                queue.add(child);
            }
        }
        return orderings;
    }

    private String getCombinedOrdering(List<List<Character>> orderings) {
        Map<Character, TrieNode> graph;
        try {
            graph = this.buildGraph(orderings);
        } catch (Exception e) {
            return "";
        }
        return this.getSortOrdering(graph);
    }

    private Map<Character, TrieNode> buildGraph(List<List<Character>> orderings) throws Exception {
        Map<Character, TrieNode> graph = new HashMap<>();
        for (List<Character> ordering : orderings) {
            TrieNode prevNode = null;
            for (Character c : ordering) {
                if (c == '0') {
                    if (prevNode != null) {
                        throw new Exception("Wrong Ordering");
                    } else {
                        continue;
                    }
                }
                graph.putIfAbsent(c, new TrieNode(c));
                TrieNode cnode = graph.get(c);
                if (prevNode != null) {
                    prevNode.children.add(cnode);
                }
                prevNode = cnode;
            }
        }
        return graph;
    }

    private String getSortOrdering(Map<Character, TrieNode> graph) {
        Stack<Character> stack = new Stack<>();
        Set<TrieNode> visited = new HashSet<>();
        Set<TrieNode> thisVisited = new HashSet<>();
        for (Character c : graph.keySet()) {
            try {
                this.topologicalSort(graph.get(c), visited, thisVisited, stack);
            } catch (Exception e) {
                return "";
            }
        }
        StringBuilder orderedStr = new StringBuilder();
        while (!stack.empty()) {
            orderedStr.append(stack.pop() + "");
        }
        return orderedStr.toString();
    }

    private void topologicalSort(TrieNode trieNode, Set<TrieNode> visited,
            Set<TrieNode> thisVisited,
            Stack<Character> stack) throws Exception {
        if (!visited.contains(trieNode)) {
            if (thisVisited.contains(trieNode)) {
                throw new Exception("Cycle detected");
            }
            thisVisited.add(trieNode);
            for (TrieNode c : trieNode.children) {
                this.topologicalSort(c, visited, thisVisited, stack);
            }
            stack.add(trieNode.c);
            thisVisited.remove(trieNode);
            visited.add(trieNode);
        }
    }
}


class TrieNode {
    char c;
    List<TrieNode> children;

    TrieNode(char c) {
        this.c = c;
        this.children = new ArrayList<>();
    }
}
