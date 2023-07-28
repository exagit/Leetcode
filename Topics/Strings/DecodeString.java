package Topics.Strings;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

public class DecodeString {
    @Test
    public void test1() {
        String str = "3[a]2[bc]";
        System.out.println(DecodeSol.decodeString(str));
    }

    @Test
    public void test2() {
        String str = "3[a2[c]]";
        System.out.println(DecodeSol.decodeString(str));
    }

    @Test
    public void test3() {
        // String str = "2[abc]3[cd]ef";
        // System.out.println(DecodeSol.decodeStringChatGpt(str));

        String pattern = "i18n", input = "internationalization";
        System.out.println(DecodeSol.matchPattern(pattern, input));
    }

    @Test
    public void test4() {
        String str = "11[abc]";
        System.out.println(DecodeSol.decodeString(str));
    }
}


class DecodeSol {

    public static boolean matchPattern(String pattern, String inputString) {
        // Escape special characters in the pattern
        String escapedPattern = Pattern.quote(pattern);

        // Replace number placeholders in the pattern with appropriate regex patterns
        String regexPattern = escapedPattern.replaceAll("\\d+", "\\\\w");

        // Use regex to check if the input string matches the pattern
        Pattern compiledPattern = Pattern.compile(regexPattern);
        Matcher matcher = compiledPattern.matcher(inputString);

        return matcher.matches();
    }

    public static String decodeStringChatGpt(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int currentNum = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                currentNum = currentNum * 10 + (c - '0');
            } else if (c == '[') {
                numStack.push(currentNum);
                strStack.push(currentString);
                currentNum = 0;
                currentString = new StringBuilder();
            } else if (c == ']') {
                StringBuilder previousString = strStack.pop();
                int repeatTimes = numStack.pop();
                currentString = previousString.append(currentString.toString().repeat(repeatTimes));
            } else {
                currentString.append(c);
            }
        }

        return currentString.toString();
    }

    public static String decodeString(String str) {
        Stack<String> ss = new Stack<>();
        Stack<Integer> ns = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            String noStr = "";
            int ascii = c - '0';
            while (ascii >= 0 && ascii <= 9) {
                noStr += c + "";
                c = str.charAt(++i);
                ascii = c - '0';
            }
            if (!noStr.isEmpty()) {
                ns.add(Integer.parseInt(noStr));
                i--;
            } else if (c == ']') {
                String innerStr = "";
                while (!ss.isEmpty() && !ss.peek().equals("[")) {
                    innerStr = ss.pop() + innerStr;
                }
                ss.pop();
                ss.add(repeat(innerStr, ns.pop()));
            } else {
                ss.add(c + "");
            }
        }

        String finalRes = "";
        while (!ss.isEmpty()) {
            finalRes = ss.pop() + finalRes;
        }
        return finalRes;
    }

    public static String repeat(String str, int times) {
        String finalStr = "";
        for (int i = 0; i < times; i++) {
            finalStr += str;
        }
        return finalStr;
    }
}
