package Topics.Strings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExcelSheetTitle {
    @Test
    public void test1() {
        int colNo = 703;
        assertEquals(new ESTSol().convertToTitle(colNo), "AAA");
    }

    @Test
    public void test2() {
        int colNo = 0;
        assertEquals(new ESTSol().convertToTitle(colNo), "");
    }
}

class ESTSol {

    public String convertToTitle(int columnNumber) {
        if (columnNumber == 0)
            return "";
        String title = "";
        do {
            columnNumber -= 1;
            int rem = columnNumber % 26;
            title = ((char) (65 + rem)) + title;
        } while ((columnNumber /= 26) != 0);
        return title;
    }
}