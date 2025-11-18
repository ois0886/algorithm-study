import java.util.*;

class Solution {

    static int[][] arr;
    static boolean[][] visited;

    static int N, M;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public int[] solution(String[] maps) {
        ArrayList<Integer> answer = new ArrayList<>();
        N = maps.length;
        M = maps[0].length();
        arr = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i].charAt(j) == 'X') {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = Integer.parseInt(String.valueOf(maps[i].charAt(j)));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && arr[i][j] != 0) {
                    answer.add(DFS(i, j));
                }
            }
        }
        Collections.sort(answer);
        if (answer.isEmpty()) return new int[]{-1};
        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static int DFS(int startX, int startY) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        int sum = arr[startX][startY];
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (range(nx, ny) && !visited[nx][ny] && arr[nx][ny] != 0) {
                    que.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    sum += arr[nx][ny];
                }
            }
        }
        return sum;
    }

    private static boolean range(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}