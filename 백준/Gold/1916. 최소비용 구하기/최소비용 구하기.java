import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 백준알고리즘 1916번 최소비용 구하기

public class Main {
    static int N, M;
    static int start, end;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[v].add(new Node(w, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        Dijkstra();
    }

    public static void Dijkstra() {
        boolean[] check = new boolean[N + 1];
        int[] dist = new int[N + 1];
        int INF = Integer.MAX_VALUE;

        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            int nowVertex = pq.poll().index;

            if (check[nowVertex]) continue;
            check[nowVertex] = true;

            //index의 연결된 정점 비교
            for (Node next : graph[nowVertex]) {
                if (dist[next.index] > dist[nowVertex] + next.cost) {
                    dist[next.index] = dist[nowVertex] + next.cost;

                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }

        System.out.print(dist[end]);

    }
}

class Node implements Comparable<Node> {
    int index;
    int cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}