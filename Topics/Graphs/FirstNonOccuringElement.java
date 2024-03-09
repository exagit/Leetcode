package Topics.Graphs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class FirstNonOccuringElement {

    @Test
    public void test1() {
        int[] A = { 1, 2, 3, 5 };
        assertEquals(new FirstNonOccuringElement().solution(A), 4);
    }

    public int solution(int[] A) {
        // Implement your solution here
        DisjointSet dset = new DisjointSet();

        dset.createSubset(0);
        for (int a : A) {
            dset.createSubset(a);
            dset.union(a, a - 1);
            dset.union(a, a + 1);
        }
        int minEnd = Integer.MAX_VALUE;
        for (Subset parent : dset.getAllParents()) {
            minEnd = Math.min(minEnd, parent.end);
        }
        return minEnd + 1;
    }

    class Subset {
        Subset parent;
        int start;
        int end;

        public Subset(int start, int end) {
            this.parent = this;
            this.start = start;
            this.end = end;
        }

        int compareTo(Subset s) {
            if (this.start < s.start) {
                return -1;
            } else if (this.start > s.start) {
                return 1;
            } else
                return 0;
        }
    }

    class DisjointSet {

        Map<Integer, Subset> map;

        public DisjointSet() {
            this.map = new HashMap<>();
        }

        Subset getSubset(int element) {
            return this.map.get(element);
        }

        public void createSubset(int element) {
            if (element >= 0) {
                if (!this.map.containsKey(element)) {
                    this.map.put(element, new Subset(element, element));
                }
            }
        }

        public void union(int element1, int element2) {
            if (element1 < 0 || element2 < 0) {
                return;
            }
            Subset subset1 = this.getSubset(element1);
            Subset subset2 = this.getSubset(element2);
            if (subset1 == null || subset2 == null) {
                return;
            }
            Subset parent1 = this.find(subset1);
            Subset parent2 = this.find(subset2);

            Subset newparent;
            if (subset1.compareTo(subset2) < 0) {
                newparent = new Subset(parent1.start, parent2.end);
            } else {
                newparent = new Subset(parent2.start, parent1.end);
            }
            parent1.parent = newparent;
            parent2.parent = newparent;
        }

        public Subset find(Subset s) {
            if (s.parent != s) {
                s.parent = this.find(s.parent);
            }
            return s.parent;
        }

        public List<Subset> getAllParents() {
            List<Subset> parents = new ArrayList<>();
            for (int element : this.map.keySet()) {
                Subset s = this.map.get(element);
                parents.add(this.find(s));
            }
            return parents;
        }

    }
}