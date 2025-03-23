import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준알고리즘 2156번 포도주 시식
public class Main {
    static int N;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = arr[1];
        if (N > 1) {
            dp[2] = arr[1] + arr[2];
        }

        System.out.println(DP(N));
    }

    public static int DP(int idx) {
        if (dp[idx] == -1) {
            dp[idx] = Math.max(Math.max(DP(idx - 2), DP(idx - 3) + arr[idx - 1]) + arr[idx], DP(idx - 1));
        }
        return dp[idx];
    }

}