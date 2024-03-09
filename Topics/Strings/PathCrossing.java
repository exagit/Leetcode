package Topics.Strings;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class PathCrossing {

    @Test
    public void test1() {
        String path = "NES";
        assertEquals(this.isPathCrossing(path), false);
    }

    @Test
    public void test2() {
        String path = "NESW";
        assertEquals(this.isPathCrossing(path), true);
    }

    @Test
    public void test3() {
        String path = "NESWW";
        assertEquals(this.isPathCrossing(path), true);
    }

    @Test
    public void test4() {
        String path = "";
        assertEquals(this.isPathCrossing(path), false);
    }

    /**
     * Keep track of the displacement from origin using a set. Use a string based
     * representation of the cell visited.
     * If the current displacement is found in the set at any point while
     * traversing, return true.
     * Return false at the end of entire string traversal.
     * 
     * @param path
     * @return
     */
    public boolean isPathCrossing(String path) {
        int displacementX = 0, displacementY = 0;
        Set<String> displacementRepresentationSet = new HashSet<String>();
        displacementRepresentationSet.add(this.getXYRepresentation(0, 0));
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (c == 'N') {
                displacementY += 1;
            }
            if (c == 'S') {
                displacementY -= 1;
            }
            if (c == 'E') {
                displacementX += 1;
            }
            if (c == 'W') {
                displacementX -= 1;
            }
            String repr = this.getXYRepresentation(displacementX, displacementY);
            if (displacementRepresentationSet.contains(repr)) {
                return true;
            }
            displacementRepresentationSet.add(repr);
        }
        return false;
    }

    private String getXYRepresentation(int displacementX, int displacementY) {
        return displacementX + "X," + displacementY + "Y";
    }
}
