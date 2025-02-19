import java.io.*;
import java.util.*;

public class Main {
    static int R, C, maxStep;
    static char[][] board;
    static boolean[] visited = new boolean[26];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }
        
        maxStep = 0;
        dfs(0, 0, 1);
        System.out.println(maxStep);
    }

    static void dfs(int x, int y, int step) {
        int alpha = board[x][y] - 'A';
        visited[alpha] = true;
        maxStep = Math.max(maxStep, step);
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                int nextAlpha = board[nx][ny] - 'A';
                if (!visited[nextAlpha]) {
                    dfs(nx, ny, step + 1);
                }
            }
        }
        
        visited[alpha] = false;
    }
}