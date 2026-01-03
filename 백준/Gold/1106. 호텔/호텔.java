import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken()); // 목표 고객 수
        int N = Integer.parseInt(st.nextToken()); // 도시 개수

        // dp[i] = i명의 고객을 얻는데 필요한 최소 비용
        int[] dp = new int[C + 101];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        int[][] cities = new int[N][2]; // [비용, 고객 수]
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cities[i][0] = Integer.parseInt(st.nextToken()); // 비용
            cities[i][1] = Integer.parseInt(st.nextToken()); // 고객 수
        }

        // 각 도시별로 홍보 진행
        for (int i = 0; i < N; i++) {
            int cost = cities[i][0];
            int customer = cities[i][1];

            // customer명부터 C+100명까지 갱신
            for (int j = customer; j < C + 101; j++) {
                if (dp[j - customer] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - customer] + cost);
                }
            }
        }

        // C명 이상 중에서 최소 비용 찾기
        int answer = Integer.MAX_VALUE;
        for (int i = C; i < C + 101; i++) {
            answer = Math.min(answer, dp[i]);
        }

        System.out.println(answer);
    }
}