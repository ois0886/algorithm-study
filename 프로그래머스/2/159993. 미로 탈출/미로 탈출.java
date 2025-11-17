import java.util.*;

class Solution {
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {1, -1, 0, 0};
    
    public int solution(String[] maps) {
        int n = maps.length;
        int m = maps[0].length();
        
        int[] start = null, lever = null, exit = null;
        
        // 시작점, 레버, 출구 위치 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maps[i].charAt(j) == 'S') start = new int[]{i, j};
                else if (maps[i].charAt(j) == 'L') lever = new int[]{i, j};
                else if (maps[i].charAt(j) == 'E') exit = new int[]{i, j};
            }
        }
        
        // 시작점 -> 레버까지의 최단거리
        int toLever = bfs(maps, start, lever);
        if (toLever == -1) return -1;
        
        // 레버 -> 출구까지의 최단거리
        int toExit = bfs(maps, lever, exit);
        if (toExit == -1) return -1;
        
        return toLever + toExit;
    }
    
    private int bfs(String[] maps, int[] start, int[] target) {
        int n = maps.length;
        int m = maps[0].length();
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], dist = cur[2];
            
            if (x == target[0] && y == target[1]) {
                return dist;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx >= 0 && nx < n && ny >= 0 && ny < m 
                    && !visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, dist + 1});
                }
            }
        }
        
        return -1;
    }
}
