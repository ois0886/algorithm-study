import java.util.*;

class Solution {
    static ArrayList<Integer>[] graph;

    public int solution(int n, int[][] wires) {
        int answer = n;

        // 그래프 초기화
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 양방향 간선 추가
        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        // 각 간선을 하나씩 제거해보며 탐색
        for (int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];

            // 간선 제거
            graph[v1].remove(Integer.valueOf(v2));
            graph[v2].remove(Integer.valueOf(v1));

            // 한쪽 서브트리의 노드 개수 계산
            boolean[] visited = new boolean[n + 1];
            int cnt = dfs(1, visited);

            // 두 서브트리의 차이 계산
            int diff = Math.abs(cnt - (n - cnt));
            answer = Math.min(answer, diff);

            // 간선 복원
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        return answer;
    }

    int dfs(int node, boolean[] visited) {
        visited[node] = true;
        int count = 1;

        for (int next : graph[node]) {
            if (!visited[next]) {
                count += dfs(next, visited);
            }
        }

        return count;
    }
}
