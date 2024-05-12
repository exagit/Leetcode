package com.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

class Node {
    Integer consumerId;
    List<Node> dependencies;

    Node(Integer consumerId) {
        this.consumerId = consumerId;
        this.dependencies = new ArrayList<>();
    }
}

public class Graph {

    Map<Integer, Node> nodeMap;

    public void addDependency(Integer consumerId, Integer dependencyConsumerId) {
        Node dependencyConsumerNode = this.createOrGetNode(dependencyConsumerId);

        Node consumerNode = this.createOrGetNode(consumerId);
        consumerNode.dependencies.add(dependencyConsumerNode);
    }

    private Node createOrGetNode(Integer consumerId) {
        this.nodeMap.putIfAbsent(consumerId, new Node(consumerId));
        return this.nodeMap.get(consumerId);
    }

    public int[] getOrderedNodes() {
        Stack<Node> stack = new Stack<>();
        Set<Node> visitedSet = new HashSet<>();
        Set<Node> recursionSet = new HashSet<>();

        for (Integer consumerId : this.nodeMap.keySet()) {
            this.visitDFS(this.nodeMap.get(consumerId), visitedSet, recursionSet, stack);
        }

        return new int[] { 0 };
        // empty stack and return the reverse of the order
    }

    private void visitDFS(Node node, Set<Node> visitedSet, Set<Node> recursionSet, Stack<Node> stack) {
        if (recursionSet.contains(node)) {
            // cycle detected
        }
        recursionSet.add(node);

        if (visitedSet.contains(node)) {
            // return as no need to add it to stack, its already added
            return;
        }
        visitedSet.add(node);

        // add all children
        for (Node dependency : node.dependencies) {
            this.visitDFS(dependency, visitedSet, recursionSet, stack);
        }

        stack.add(node);

        recursionSet.remove(node);
    }

}
