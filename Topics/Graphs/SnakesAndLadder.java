package UberCards.Graphs;

import static java.util.stream.Collectors.toList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Result {

    /*
     * Complete the 'quickestWayUp' function below.
     *
     * The function is expected to return an INTEGER. The function accepts following parameters: 1.
     * 2D_INTEGER_ARRAY ladders 2. 2D_INTEGER_ARRAY snakes
     */

    public static int quickestWayUp(List<List<Integer>> ladders, List<List<Integer>> snakes) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { 1, 0 });
        Set<Integer> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            int arr[] = queue.poll();
            int start = arr[0], moves = arr[1];
            if (start == 100) {
                return moves;
            }
            int max = start;
            for (int i = start + 1; i <= start + 6; i++) {
                if (i > 100) {
                    continue;
                }
                int finalLanding = Result.jump(i, ladders, snakes);
                if (finalLanding != i) {
                    if (!visited.contains(finalLanding)) {
                        visited.add(finalLanding);
                        queue.add(new int[] { finalLanding, moves + 1 });
                    }
                } else {
                    max = Math.max(i, max);
                }
            }
            if (max != start) {
                if (!visited.contains(max)) {
                    visited.add(max);
                    queue.add(new int[] { max, moves + 1 });
                }
            }
        }
        return -1;
    }

    public static int jump(int start, List<List<Integer>> ladders, List<List<Integer>> snakes) {
        int current = start, last = -1;
        while (current != last) {
            last = current;
            current = Result.findAndJump(current, ladders);
            current = Result.findAndJump(current, snakes);
        }
        return current;
    }

    public static int findAndJump(int start, List<List<Integer>> snakesOrLadders) {
        for (List<Integer> snakeLadder : snakesOrLadders) {
            if (snakeLadder.get(0) == start) {
                return snakeLadder.get(1);
            }
        }
        return start;
    }
}


public class SnakesAndLadder {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter =
                new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<List<Integer>> ladders = new ArrayList<>();

                IntStream.range(0, n).forEach(i -> {
                    try {
                        ladders.add(
                                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "")
                                        .split(" "))
                                        .map(Integer::parseInt)
                                        .collect(toList()));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int m = Integer.parseInt(bufferedReader.readLine().trim());

                List<List<Integer>> snakes = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        snakes.add(
                                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "")
                                        .split(" "))
                                        .map(Integer::parseInt)
                                        .collect(toList()));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int result = Result.quickestWayUp(ladders, snakes);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
