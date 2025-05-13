import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] S;
    static boolean[] visited;
    static int minDiff;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            sb.append("#").append(test_case).append(" ");
            S = new int[N][N];
            visited = new boolean[N];
            minDiff = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            comb(0, 0);
            sb.append(minDiff).append("\n");
        }
        System.out.println(sb);
    }

    // N개 중 N/2개를 선택하는 조합
    static void comb(int idx, int count) {
        if (count == N / 2) {
            List<Integer> groupA = new ArrayList<>();
            List<Integer> groupB = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (visited[i]) groupA.add(i);
                else groupB.add(i);
            }

            int sumA = getSynergy(groupA);
            int sumB = getSynergy(groupB);
            minDiff = Math.min(minDiff, Math.abs(sumA - sumB));
            return;
        }

        for (int i = idx; i < N; i++) {
            visited[i] = true;
            comb(i + 1, count + 1);
            visited[i] = false;
        }
    }

    // 그룹 내 모든 시너지 합
    static int getSynergy(List<Integer> group) {
        int sum = 0;
        for (int i = 0; i < group.size(); i++) {
            for (int j = i + 1; j < group.size(); j++) {
                int a = group.get(i);
                int b = group.get(j);
                sum += S[a][b] + S[b][a];
            }
        }
        return sum;
    }
}

