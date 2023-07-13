package UberCards.Backtracking;

import java.util.Arrays;
import org.junit.Test;

public class CountRoutes {
    @Test
    public void test1() {
        int[] locations = { 2, 3, 6, 8, 4 };
        int start = 1, finish = 3, fuel = 5;
        CountRoutesSolution sol = new CountRoutesSolution();
        int res = sol.countRoutes(locations, start, finish, fuel);
        System.out.println(res);
    }

    @Test
    public void test2() {
        int[] locations = { 4, 3, 1 };
        int start = 1, finish = 0, fuel = 6;
        CountRoutesSolution sol = new CountRoutesSolution();
        int res = sol.countRoutes(locations, start, finish, fuel);
        System.out.println(res);
    }

    @Test
    public void test3() {
        int[] locations = { 5, 2, 1 };
        int start = 0, finish = 2, fuel = 3;
        CountRoutesSolution sol = new CountRoutesSolution();
        int res = sol.countRoutes(locations, start, finish, fuel);
        System.out.println(res);
    }
}


class CountRoutesSolution {
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int dp[][][] = new int[locations.length][locations.length][fuel];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations.length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return this.countRoutesInternal(locations, start, finish, fuel, dp);
    }

    public int countRoutesInternal(int[] locations, int start, int finish, int fuel, int[][][] dp) {
        if (fuel == 0) {
            if (start == finish) {
                return 1;
            } else {
                return 0;
            }
        }
        if (dp[start][finish][fuel - 1] == -1) {
            int routes = 0;
            if (start == finish) {
                routes = 1;
            }
            for (int i = 0; i < locations.length; i++) {
                if (i != start) {
                    int distance = Math.abs(locations[i] - locations[start]);
                    if (fuel - distance >= 0) {
                        routes +=
                                this.countRoutesInternal(locations, i, finish, fuel - distance, dp);
                    }
                }
                routes = routes % (((int) Math.pow(10, 9)) + 7);
            }
            dp[start][finish][fuel - 1] = routes;
        }
        return dp[start][finish][fuel - 1];
    }
}
