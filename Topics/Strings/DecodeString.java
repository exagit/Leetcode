package Topics.Strings;

import java.util.Stack;
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
        String str = "2[abc]3[cd]ef";
        System.out.println(DecodeSol.decodeString(str));
    }

    @Test
    public void test4() {
        String str = "11[abc]";
        System.out.println(DecodeSol.decodeString(str));
    }
}


class DecodeSol {
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
