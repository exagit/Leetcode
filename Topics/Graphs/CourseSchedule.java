package Topics.Graphs;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

public class CourseSchedule {

    @Test
    public void test1() {
        int numCourses = 2, prerequisites[][] = { { 1, 0 } };
        int[] order = new CourseSchedule().findOrder(numCourses, prerequisites);
        assertArrayEquals(order, new int[] { 0, 1 });
    }

    @Test
    public void test2() {
        int numCourses = 4, prerequisites[][] = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
        int[] order = new CourseSchedule().findOrder(numCourses, prerequisites);
        assertArrayEquals(order, new int[] { 0, 2, 1, 3 });
    }

    @Test
    public void test3() {
        int numCourses = 1, prerequisites[][] = {};
        int[] order = new CourseSchedule().findOrder(numCourses, prerequisites);
        assertArrayEquals(order, new int[] { 0 });
    }

    @Test
    public void test4() {
        int numCourses = 2, prerequisites[][] = { { 0, 1 }, { 1, 0 } };
        int[] order = new CourseSchedule().findOrder(numCourses, prerequisites);
        assertArrayEquals(order, new int[] {});
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[][] matrix = this.createDependencyMatrix(prerequisites, numCourses);
        Stack<Integer> orderedStack = new Stack<>();
        Set<Integer> recursionSet = new HashSet<>();
        try {
            for (int i = 0; i < numCourses; i++) {
                this.addNodeToStack(i, orderedStack, recursionSet, matrix);
            }
        } catch (Exception e) {
            return new int[0];
        }

        int[] orderedNodes = new int[numCourses];
        int c = 0;
        while (!orderedStack.isEmpty()) {
            orderedNodes[c++] = orderedStack.pop();
        }
        return orderedNodes;
    }

    private void addNodeToStack(int node, Stack<Integer> orderedStack,
            Set<Integer> recursionSet, int[][] matrix) throws Exception {
        if (recursionSet.contains(node)) {
            throw new Exception("Cycle found");
        }

        if (orderedStack.contains(node)) {
            return;
        }
        recursionSet.add(node);
        int N = matrix[node].length;
        // add dependencies of this node recursively to stack first
        for (int j = 0; j < N; j++) {
            if (matrix[node][j] == 1) {
                this.addNodeToStack(j, orderedStack, recursionSet, matrix);
            }
        }
        // add node to stack
        orderedStack.add(node);

        recursionSet.remove(node);
    }

    private int[][] createDependencyMatrix(int[][] prerequisites, int N) {
        int[][] matrix = new int[N][N];
        for (int[] prereq : prerequisites) {
            int prereqNode = prereq[1];
            int depNode = prereq[0];
            matrix[prereqNode][depNode] = 1;
        }
        return matrix;
    }
}
