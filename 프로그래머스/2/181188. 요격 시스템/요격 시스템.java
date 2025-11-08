import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, Comparator.comparingInt(a -> a[1]));
        int answer = 0;
        int end = 0;
        for (int[] target : targets) {
            int s = target[0];
            int e = target[1];

            if (s >= end) {
                answer++;
                end = e;
            }
        }

        return answer;
    }
}