import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int min;
    static Point[] customers;
    static boolean[] visited;
    static Point company, home;

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int dist(Point p) {
            return Math.abs(x - p.x) + Math.abs(y - p.y);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            sb.append("#").append(test_case).append(" ");

            home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));


            customers = new Point[N];
            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                customers[i] = new Point(x, y);
            }

            visited = new boolean[N];
            min = Integer.MAX_VALUE;

            dfs(0, 0, company);

            sb.append(min).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int depth, int distSum, Point current) {
        //if (distSum >= min) return; // 가지치기

        if (depth == N) {
            distSum += current.dist(home);
            min = Math.min(min, distSum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, distSum + current.dist(customers[i]), customers[i]);
                visited[i] = false;
            }
        }
    }
}


