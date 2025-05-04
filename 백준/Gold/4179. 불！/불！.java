import java.util.*;
import java.io.*;

class Main {
    public static int n, m, ans; // n: 행, m: 열, ans: 탈출 시간
    public static int[] dx = {-1, 1, 0, 0}; // 상하좌우 x 이동
    public static int[] dy = {0, 0, -1, 1}; // 상하좌우 y 이동
    public static char[][] map; // 맵 정보 저장

    // 위치와 시간 정보를 저장할 Node 클래스
    public static class Node {
        int x;
        int y;
        int time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    // 지훈이(J)와 불(F)을 각각 저장할 큐
    public static Queue<Node> jh, fire;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // n: 행 수, m: 열 수
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 큐 초기화
        jh = new LinkedList<>();
        fire = new LinkedList<>();

        // 맵 정보 입력
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'J') {
                    // 지훈이 시작 위치 저장
                    jh.add(new Node(i, j, 0));
                }

                if (map[i][j] == 'F') {
                    // 불 시작 위치 저장
                    fire.add(new Node(i, j, 0));
                }
            }
        }

        ans = 0;

        // bfs()가 true를 리턴하면 탈출 불가능
        if (bfs()) {
            System.out.println("IMPOSSIBLE");
        } else {
            // 탈출 시간 출력
            System.out.println(ans);
        }
    }

    // BFS로 불과 지훈이 이동 처리
    public static boolean bfs() {
        while (!jh.isEmpty()) {
            // 1. 먼저 현재 불 위치에서 퍼뜨림 (1초 단위)
            int f_size = fire.size();
            for (int i = 0; i < f_size; i++) {
                Node node = fire.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = node.x + dx[d];
                    int ny = node.y + dy[d];

                    // 범위 안에서 벽이 아니고, 이미 불이 아닌 곳만 확산
                    if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                        if (map[nx][ny] != '#' && map[nx][ny] != 'F') {
                            map[nx][ny] = 'F'; // 해당 칸에 불을 붙임
                            fire.add(new Node(nx, ny, node.time + 1));
                        }
                    }
                }
            }

            // 2. 지훈이 이동 처리
            int j_size = jh.size();
            for (int i = 0; i < j_size; i++) {
                Node node = jh.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = node.x + dx[d];
                    int ny = node.y + dy[d];

                    // 맵을 벗어나면 탈출 성공
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        ans = node.time + 1; // 현재 시간 + 1초가 탈출 시간
                        return false; // 탈출했으므로 false 리턴
                    }

                    // 이동 가능한 곳: 벽, 불, 방문했던 곳 제외
                    if (map[nx][ny] != '#' && map[nx][ny] != 'F' && map[nx][ny] != 'J') {
                        jh.add(new Node(nx, ny, node.time + 1));
                        map[nx][ny] = 'J'; // 방문 표시
                    }
                }
            }
        }

        // 지훈이가 더 이상 움직일 수 없는 경우 (탈출 실패)
        return true;
    }
}
