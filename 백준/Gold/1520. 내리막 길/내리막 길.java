import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[][] dp;
    static boolean[][] visited;
    static int n, m;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dp = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        System.out.println(DFS(0, 0));

    }

    private static int DFS(int x, int y) {
        visited[x][y] = true;
        if (x == n - 1 && y == m - 1) {
            return 1;
        }
        if (dp[x][y] == -1) {
            dp[x][y] = 0;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (Range(nx, ny)) {
                    if (map[nx][ny] < map[x][y]) {
                        dp[x][y] += DFS(nx, ny);
                    }
                }
            }
        }
        return dp[x][y];


    }

    private static boolean Range(int x, int y) {
        return ((0 <= x) && (x < n)) && ((0 <= y) && (y < m));
    }
}
