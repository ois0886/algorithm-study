import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 백준알고리즘 16173번 점프왕 쩰리(small)
public class Main {

    static int N;
    static int[][] map;
    static boolean[][] visit;
    static String result = "Hing";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0, 0);

        System.out.println(result);
    }

    static void DFS(int x, int y) {
        if (map[x][y] == -1) {
            result = "HaruHaru";
            return;
        }

        visit[x][y] = true;

        for (int i = 0; i < 2; i++) {
            int ndx = x + map[x][y];
            int ndy = y + map[x][y];
            if (range(ndx, y)) {
                if (!visit[ndx][y]) DFS(ndx, y);
            }
            if (range(x, ndy)) {
                if (!visit[x][ndy]) DFS(x, ndy);
            }
        }
    }

    static boolean range(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}