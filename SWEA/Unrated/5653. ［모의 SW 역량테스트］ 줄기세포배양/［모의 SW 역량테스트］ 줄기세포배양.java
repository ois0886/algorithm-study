import java.io.*;
import java.util.*;
import java.util.List;

public class Solution {

    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int T, N, M, K;

    static List<Cell> cell;
    static PriorityQueue<Cell> pq;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            cell = new ArrayList<>();
            pq = new PriorityQueue<>((p1, p2) -> p2.power - p1.power);
            visited = new boolean[N + 2 * K][M + 2 * K];


            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int n = Integer.parseInt(st.nextToken());

                    if (n != 0) {
                        cell.add(new Cell(i + K, j + K, n, n, 2));
                        visited[i + K][j + K] = true;
                    }

                }
            }
            simulation();
            System.out.println("#" + tc + " " + count());

        }
    }

    static void simulation() {
        for (int k = 1; k <= K; k++) {

            // 직전에 INACTIVE -> ACTIVE 상태로 변경된 cell들
            while (!pq.isEmpty()) {
                Cell c = pq.poll();
                int y = c.y;
                int x = c.x;

                if (!visited[y][x]) {
                    visited[y][x] = true;
                    cell.add(c);
                }
            }

            for (Cell value : cell) {
                if (value.state == 0) continue;
                else if (value.state == 2 && value.time == k) {
                    value.state = 1;
                    value.time = k + value.power;


                    for (int d = 0; d < 4; d++) {
                        int ny = value.y + dy[d];
                        int nx = value.x + dx[d];

                        pq.add(new Cell(ny, nx, k + 1 + value.power, value.power, 2));
                    }

                } else if (value.state == 1 && value.time == k) {
                    value.state = 0;
                    value.time = 0;
                    value.power = 0;
                }

            }
        }
    }

    static int count() {
        int cnt = 0;
        for (Cell value : cell) {
            if (value.state == 1 || value.state == 2)
                cnt++;
        }
        return cnt;
    }
}

class Cell {
    int y, x, time, state, power;

    Cell(int y, int x, int time, int power, int state) {
        this.y = y;
        this.x = x;
        this.time = time;
        this.power = power;
        this.state = state;
    }
}