import java.util.*;

public class Main {
    static int n, m, k, x;
    static List<List<Integer>> graph;
    static int[] distance;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        x = sc.nextInt();

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
        }

        List<Integer> result = bfs(x);

        printResult(result);
    }

    // BFS 실행 후 거리가 k인 노드 리스트 반환
    private static List<Integer> bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        distance = new int[n + 1];
        Arrays.fill(distance, -1);

        queue.add(start);
        distance[start] = 0;

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph.get(now)) {
                if (distance[next] == -1) { // 방문하지 않은 경우
                    distance[next] = distance[now] + 1;
                    queue.add(next);
                    if (distance[next] == k) {
                        result.add(next);
                    }
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    // 결과 출력 함수
    private static void printResult(List<Integer> result) {
        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int city : result) {
                System.out.println(city);
            }
        }
    }
}