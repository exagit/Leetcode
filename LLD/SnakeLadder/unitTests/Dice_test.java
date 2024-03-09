package LLD.SnakeLadder.unitTests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import LLD.SnakeLadder.lib.Dice;

public class Dice_test {

    @Test
    public void testRoll() {
        Dice d = new Dice();
        for (int i = 0; i < 10000000; i++) {
            d.roll();
            int currentDiceNumber = d.getCurrent();
            assertNotEquals(currentDiceNumber, 0);
            assertTrue(currentDiceNumber >= 1 && currentDiceNumber <= 6);
        }
    }
}
