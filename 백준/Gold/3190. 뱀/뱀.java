import java.util.*;
import java.io.*;


public class Main {

    static int N; // 보드 크기
    static int[][] board; // 보드
    static List<int[]> snake = new ArrayList<>(); // 뱀
    static HashMap<Integer, String> orders = new HashMap<>(); // 명령어
    static int[] dx = {0, 1, 0, -1}; // 동 남 서 북
    static int[] dy = {1, 0, -1, 0}; // 동 남 서 북

    static int result = 0; // 결과 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        int K = Integer.parseInt(br.readLine()); // 사과의 갯수

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            board[x][y] = 1; // 사과의 위치
        }

        int order = Integer.parseInt(br.readLine()); // 명령입력
        for (int i = 0; i < order; i++) {
            st = new StringTokenizer(br.readLine());
            orders.put(Integer.parseInt(st.nextToken()), st.nextToken());
        }

        moving();

        System.out.println(result);
    }

    private static void moving() {
        int cx = 0, cy = 0;
        int d = 0;
        snake.add(new int[]{0, 0});

        while (true) {
            // 1. 시간재기
            result++;

            // 2. 뱀 이동하기
            int nx = cx + dx[d];
            int ny = cy + dy[d];

            // 3. 범위를 벗어나거나, 뱀 몸통 만날 때 종료
            if (isFinish(nx, ny))
                break;

            // 4. 사과가 있을 때 없을 때 처리
            if (board[nx][ny] == 1) {
                board[nx][ny] = 0;
                snake.add(new int[]{nx, ny});
            } else {
                snake.add(new int[]{nx, ny});
                snake.remove(0);
            }

            // 5. 방향을 바꿔주는 시간을 만날 때 방향 변경
            if (orders.containsKey(result)) {
                if (orders.get(result).equals("D")) {
                    d += 1;
                    if (d == 4)
                        d = 0;
                } else {
                    d -= 1;
                    if (d == -1)
                        d = 3;
                }
            }

            cx = nx;
            cy = ny;
        }
    }

    public static boolean isFinish(int nx, int ny) {
        if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
            return true;
        }

        for (int[] t : snake) {
            if (nx == t[0] && ny == t[1])
                return true;
        }
        return false;
    }

}
