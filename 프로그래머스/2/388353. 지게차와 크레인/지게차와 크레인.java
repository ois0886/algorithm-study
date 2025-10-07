class Solution {
    final int[] DX = {-1, 1, 0, 0};
    final int[] DY = {0, 0, -1, 1};
    int n, m, answer;
    char[][] map;
    boolean[][] visited;

    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        answer = n * m;
        map = new char[n][m];

        // 2차원 배열로 변환
        for(int i = 0; i < n; i++) {
            map[i] = storage[i].toCharArray();
        }

        // 요청 순차 처리
        for(String request : requests) {
            if(request.length() == 1) {
                useCar(request.charAt(0));  // 지게차
            } else {
                useCrane(request.charAt(0)); // 크레인
            }
        }

        return answer;
    }

    // 크레인: 모든 해당 컨테이너 제거
    public void useCrane(char request) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == request) {
                    answer--;
                    map[i][j] = 0; // 빈 공간으로 변경
                }
            }
        }
    }

    // 지게차: 테두리에서 DFS 시작
    public void useCar(char request) {
        visited = new boolean[n][m];

        // 좌우 테두리
        for(int i = 0; i < n; i++) {
            if(!visited[i][0]) dfs(i, 0, request);
            if(!visited[i][m-1]) dfs(i, m-1, request);
        }

        // 상하 테두리
        for(int i = 0; i < m; i++) {
            if(!visited[0][i]) dfs(0, i, request);
            if(!visited[n-1][i]) dfs(n-1, i, request);
        }
    }

    public void dfs(int x, int y, char request) {
        visited[x][y] = true;

        // 빈 공간이면 상하좌우 계속 탐색
        if(map[x][y] == 0) {
            for(int i = 0; i < DX.length; i++) {
                int nx = x + DX[i];
                int ny = y + DY[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(!visited[nx][ny]) dfs(nx, ny, request);
            }
        }

        // 요청한 컨테이너면 제거
        if(map[x][y] == request) {
            answer--;
            map[x][y] = 0;
        }
    }
}
