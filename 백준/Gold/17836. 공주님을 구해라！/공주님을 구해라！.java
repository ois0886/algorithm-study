import java.util.*;
import java.io.*;

class Main {

    static int N, M, T;
    static int[][] map;
    static boolean[][][] visited;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = BFS();
        if (result == -1) {
            System.out.println("Fail");
            return;
        }
        System.out.println(result);

    }

    static int BFS() {
        int result = -1;
        Queue<BraveMan> que = new LinkedList<>();
        que.offer(new BraveMan(0, 0, 0, false));
        visited[0][0][0] = true;

        while (!que.isEmpty()) {
            BraveMan cur = que.poll();
            int cx = cur.x;
            int cy = cur.y;

            if (cx == N - 1 && cy == M - 1) {
                result = cur.time;
                break;
            }
            if (cur.time > T) break;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (range(nx, ny)) {
                    if (cur.sword) {
                        if (!visited[nx][ny][1]) {
                            que.offer(new BraveMan(nx, ny, cur.time + 1, cur.sword));
                            visited[nx][ny][1] = true;
                        }
                    } else {
                        if (!visited[nx][ny][0] && map[nx][ny] == 0) {
                            que.offer(new BraveMan(nx, ny, cur.time + 1, cur.sword));
                            visited[nx][ny][0] = true;
                        }
                        if (!visited[nx][ny][0] && map[nx][ny] == 2) {
                            que.offer(new BraveMan(nx, ny, cur.time + 1, true));
                            visited[nx][ny][0] = true;
                        }
                    }
                }
            }
        }
        return result;
    }

    static boolean range(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}

class BraveMan {
    int x;
    int y;
    int time;
    boolean sword;

    BraveMan(int x, int y, int time, boolean sword) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.sword = sword;
    }
}