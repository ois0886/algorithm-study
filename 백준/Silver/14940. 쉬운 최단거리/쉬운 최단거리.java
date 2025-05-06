import java.io.*;
import java.util.*;

// 백준 알고리즘 14940번 쉬운 최단거리
public class Main {
    static int N, M;                     // 지도 크기 (N: 세로, M: 가로)
    static int[][] arr;                 // 입력 지도 배열 (0: 못 감, 1: 갈 수 있음, 2: 시작점)
    static boolean[][] visit;          // 방문 여부 체크 배열
    static int[][] result;             // 최단 거리 결과 저장 배열
    static int[] dx = {1, 0, -1, 0};    // 방향 배열 (하, 좌, 상, 우)
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        // 입력을 위한 BufferedReader와 StringTokenizer 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());  // 세로 크기
        M = Integer.parseInt(st.nextToken());  // 가로 크기

        arr = new int[N][M];
        visit = new boolean[N][M];
        result = new int[N][M];

        int start_i = 0, start_j = 0;  // 시작점 좌표 초기화

        // 입력 처리 및 시작점(2) 위치 찾기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                result[i][j] = 0;  // 초기 거리 0으로 설정
                if (arr[i][j] == 2) {
                    start_i = i;
                    start_j = j;     // 시작점 좌표 저장
                }
            }
        }

        // BFS 실행
        BFS(start_i, start_j);

        // 출력 생성
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 방문하지 못했으면서 갈 수 있는 곳(1)이라면 -1 출력
                if (!visit[i][j] && arr[i][j] == 1) {
                    sb.append(-1 + " ");
                } else {
                    sb.append(result[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);  // 최종 출력
    }

    // BFS를 이용해 최단 거리 탐색
    public static void BFS(int x, int y) {
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(x, y));      // 시작점 큐에 삽입
        visit[x][y] = true;           // 방문 처리

        while (!que.isEmpty()) {
            Node current = que.poll();  // 현재 위치 꺼내기

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                // 범위 밖이면 스킵
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                // 벽(0)이거나 이미 방문했으면 스킵
                if (arr[nx][ny] == 0 || visit[nx][ny]) continue;

                que.add(new Node(nx, ny));                        // 다음 위치 큐에 삽입
                result[nx][ny] = result[current.x][current.y] + 1; // 거리 갱신
                visit[nx][ny] = true;                              // 방문 처리
            }
        }
    }
}

// 좌표를 저장할 Node 클래스
class Node {
    public int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
