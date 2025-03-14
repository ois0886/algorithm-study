import java.util.*;
import java.io.*;

class Main {
    static int K;
    static int W, H;
    static int[][] map;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int[] horseX = {2, 1, -1, -2, 2, 1, -1, -2};
    static int[] horseY = {1, 2, 2, 1, -1, -2, -2, -1};

    static boolean[][][] visit;

    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visit = new boolean[H][W][K + 1];

        BFS(0, 0);
        System.out.println(result);

    }

    private static void BFS(int x, int y) {
        visit[x][y][K] = true;
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x, y, 0, K));

        while (!que.isEmpty()) {
            Node cur = que.poll();
            int k = cur.K;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int dir = cur.dir;

                if (range(nx, ny) && !visit[nx][ny][k] && map[nx][ny] != 1) {
                    visit[nx][ny][k] = true;
                    que.offer(new Node(nx, ny, dir + 1, k));
                }
            }

            if (k > 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = cur.x + horseX[i];
                    int ny = cur.y + horseY[i];
                    int dir = cur.dir;
                    if (range(nx, ny) && !visit[nx][ny][k-1] && map[nx][ny] != 1) {
                        visit[nx][ny][k-1] = true;
                        que.offer(new Node(nx, ny, dir + 1, k - 1));
                    }
                }
            }

            if (cur.x == H - 1 && cur.y == W - 1) {
                result = cur.dir;
                break;
            }
        }
    }

    private static boolean range(int x, int y) {
        return 0 <= x && x < H && 0 <= y && y < W;
    }

}

class Node {
    int x;
    int y;
    int dir;

    int K;

    Node(int x, int y, int dir, int K) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.K = K;
    }
}