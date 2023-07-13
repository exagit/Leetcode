package Topics.Strings;

import java.util.regex.Pattern;
import org.junit.Test;

public class ValidIpAddress {
    public String validIPAddress(String queryIP) {
        if (IPV4Adress.validate(queryIP)) {
            return "IPv4";
        } else if (IPV6Adress.validate(queryIP)) {
            return "IPv6";
        } else {
            return "Neither";
        }
    }

    class IPV4Adress {
        static boolean validate(String str) {
            String[] parts = str.split(Pattern.quote("."), 5);
            if (parts.length != 4) {
                return false;
            }

            for (String part : parts) {
                try {
                    int partInt = Integer.parseInt(part);
                    if (!part.equals("0") && part.startsWith("0")) {
                        return false;
                    }
                    if (partInt < 0 || partInt > 255) {
                        return false;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            return true;
        }
    }

    class IPV6Adress {
        static String PART_REGEX = "[0-9a-fA-F]{1,4}";
        static String IPV6_REGEX = "(((" + PART_REGEX + "):){7})(" + PART_REGEX + ")";

        static boolean validate(String str) {
            return Pattern.matches(IPV6_REGEX, str);
        }
    }

    @Test
    public void testIPV4() {
        String ipStr = "192.168.0.1";
        ValidIpAddress sol = new ValidIpAddress();
        System.out.println(sol.validIPAddress(ipStr));
    }

    @Test
    public void testWrongIPV4() {
        String ipStr = "1.1.1.1";
        ValidIpAddress sol = new ValidIpAddress();
        System.out.println(sol.validIPAddress(ipStr));
    }

    @Test
    public void testIPV6() {
        String ipStr = "2001:0db8:85a3:0:0:8A2E:0370:7334";
        ValidIpAddress sol = new ValidIpAddress();
        System.out.println(sol.validIPAddress(ipStr));
    }
}
