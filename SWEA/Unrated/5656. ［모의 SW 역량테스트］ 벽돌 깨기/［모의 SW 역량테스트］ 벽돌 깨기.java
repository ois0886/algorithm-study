import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    private static int N;
    private static int W;
    private static int H;
    private static int minCount;

    private static final int[] dr = {0, 1, -1, 0};
    private static final int[] dc = {1, 0, 0, -1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            String[] temp = br.readLine().split(" ");

            N = Integer.parseInt(temp[0]);
            W = Integer.parseInt(temp[1]);
            H = Integer.parseInt(temp[2]);
            minCount = Integer.MAX_VALUE;

            int[][] matrix = new int[H][W];

            StringTokenizer st;
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < W; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            DFS(0, matrix);

            sb.append("#").append(test_case).append(" ").append(minCount).append("\n");
        }

        System.out.print(sb);
    }

    /*
     * 구슬 던지기 : 중복 순열 리턴값 => 모든 벽돌이 제거되었는지 여부를 체크
     */
    private static boolean DFS(int depth, int[][] matrix) {
        int result = getRemain(matrix);
        if (result == 0) { // 구슬을 던지기 전에 잔여 벽돌 수 체크
            minCount = 0;
            return true;
        }

        if (depth == N) {
            // 모든 구슬을 다 던졌다면 잔여 벽돌 수로 최소값을 갱신
            minCount = Math.min(result, minCount);
            return false;
        }

        int[][] newMatrix = new int[H][W];
        for (int col = 0; col < W; col++) {
            // 해당 열에 떨어트릴 경우 제거되는 맨 윗 벽돌 찾기
            int row = 0;
            while (row < H && matrix[row][col] == 0) ++row;

            // 주의: 별도로 잔여 벽돌 수를 체크해주었기 때문에 다음 깊이를 탐색하지 않아도 됨.
            // 그렇지 않으면 벽돌이 없는 열도 방문한 후 다음 depth로 넘어가야 한다.
            // 깰 벽돌이 존재하지 않는다면 해당 열은 모두 빈칸임 => continue;
            if (row == H) continue;

            copyMatrix(matrix, newMatrix);

            // 벽돌이 존재한다면 연쇄적으로 주변 벽돌도 제거
            breakBlocks(newMatrix, row, col);
            resetBlocks(newMatrix);

            // 다음 구슬 던지러 가기 : 재귀호출 => 호출 결과가 true라면 종료
            if (DFS(depth + 1, newMatrix)) return true;
        }
        return false;
    }

    // 선정된 블록과 그 영향을 받는 블록을 모두 깨트린다 : Flood Fill (4방 BFS)
    private static void breakBlocks(int[][] matrix, int row, int col) {
        Queue<Node> queue = new ArrayDeque<>();

        if (matrix[row][col] > 1) {
            queue.add(new Node(row, col, matrix[row][col]));
        }
        matrix[row][col] = 0; // 방문 체크

        while (!queue.isEmpty()) {
            Node n = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nr = n.row;
                int nc = n.col;

                for (int i = 0; i < n.power - 1; i++) {
                    nr += dr[dir];
                    nc += dc[dir];

                    if (!isValid(nr, nc)) {
                        continue;
                    }

                    if (matrix[nr][nc] > 0) {
                        if (matrix[nr][nc] > 1) {
                            queue.add(new Node(nr, nc, matrix[nr][nc]));
                        }
                        matrix[nr][nc] = 0;
                    }
                }
            }
        }

        return;
    }

    // 벽돌 내리기 1 : 빈 자리를 찾아서 벽돌 내리기.
    // 벽돌 내리기 2 : 매 열마다 맨 윗행부터 벽돌을 모두 스택에 넣고, 빈칸으로 만든다. 그 다음 밑바닥부터 벽돌을 채운다.
    private static void resetBlocks(int[][] matrix) {
        for (int col = 0; col < W; col++) {
            int row = H - 1;
            int nr = -1;

            while (row > 0) {
                if (matrix[row][col] == 0) { //빈칸이라면 내릴 벽돌을 찾는다.
                    nr = row - 1;

                    while (nr > 0 && matrix[nr][col] == 0) --nr; //행을 위로 올린다.

                    matrix[row][col] = matrix[nr][col]; //새로 찾은 벽돌을 빈 칸에 넣는다.
                    matrix[nr][col] = 0;
                }

                if (nr == 0) break; //해당 행의 탐색 종료

                --row; //아직 해당 행을 다 못 봤다면 계속 탐색
            }
        }
    }

    // 배열 복사하기
    private static void copyMatrix(int[][] matrix, int[][] newMatrix) {
        for (int i = 0; i < H; i++) {
            if (W >= 0) System.arraycopy(matrix[i], 0, newMatrix[i], 0, W);
        }
    }

    // 남은 벽돌 개수 구하기 : 매번 구슬 던지기 전에 사용할 메소드
    private static int getRemain(int[][] matrix) {
        int count = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (matrix[i][j] != 0)
                    count++;
            }
        }
        return count;
    }

    private static boolean isValid(int nr, int nc) {
        return (nr > -1 && nc > -1 && nr < H && nc < W);
    }
}

class Node {
    int row, col, power;

    public Node(int row, int col, int count) {
        this.row = row;
        this.col = col;
        this.power = count;
    }
}