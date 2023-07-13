package Topics.Arrays;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        Integer i = 0;
        while (i < words.length) {
            List<String> line = new ArrayList<>();
            int arr[] = this.packMaxWordsIntoLine(line, words, i, maxWidth);
            i = arr[0];
            int lineLength = arr[1];
            boolean leftJustified = false;
            if (i >= words.length) {
                leftJustified = true;
            }
            line = this.padSpaces(line, lineLength, maxWidth, leftJustified);
            result.add(String.join("", line));
        }
        return result;
    }

    private List<String> padSpaces(List<String> line, int lineLength, int maxWidth,
            boolean leftJustified) {
        int paddingLength = maxWidth - lineLength;
        List<String> newLine = new ArrayList<>();
        if (paddingLength > 0) {
            if (line.size() == 1 || leftJustified) {
                newLine = line;
                newLine.add(" ".repeat(paddingLength));
            } else {
                int toPadWordCount = line.size() - 1;
                int spaceBetweenWords = paddingLength / toPadWordCount;
                int remSpace = paddingLength % toPadWordCount;
                for (int i = 0; i < toPadWordCount; i++) {
                    newLine.add(line.get(i));
                    newLine.add(" ".repeat(spaceBetweenWords));
                    if (i + 1 <= remSpace) {
                        newLine.add(" ");
                    }
                }
                newLine.add(line.get(toPadWordCount));
            }
        } else {
            newLine = line;
        }
        return newLine;
    }

    private int[] packMaxWordsIntoLine(List<String> line, String[] words, Integer i, int maxWidth) {
        String toAdd = words[i];
        int totalLineLength = 0;
        while (totalLineLength + toAdd.length() <= maxWidth) {
            line.add(toAdd);
            totalLineLength += toAdd.length();
            i++;
            if (i < words.length) {
                toAdd = " " + words[i];
            } else {
                break;
            }
        }
        return new int[] { i, totalLineLength };
    }

    @Test
    public void basicTest() {
        TextJustification sol = new TextJustification();
        String[] words = { "This", "is", "an", "example", "of", "text", "justification." };
        List<String> strings = sol.fullJustify(words, 16);
        System.out.println(strings);
    }

    @Test
    public void basicTest2() {
        TextJustification sol = new TextJustification();
        String[] words = { "What", "must", "be", "acknowledgment", "shall", "be" };
        List<String> strings = sol.fullJustify(words, 16);
        System.out.println(strings);
    }

    @Test
    public void basicTest3() {
        TextJustification sol = new TextJustification();
        String[] words = { "Science", "is", "what", "we", "understand", "well", "enough", "to",
                "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do" };
        List<String> strings = sol.fullJustify(words, 20);
        System.out.println(strings);
    }
}
