import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준알고리즘 1865번 웜홀
public class Main {

    static int TC;
    static int N, M, W;
    static int S, E, T;

    static ArrayList<ArrayList<Edge>> graph;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int test_case = 0; test_case < TC; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M + W; i++) {
                st = new StringTokenizer(br.readLine());
                S = Integer.parseInt(st.nextToken());
                E = Integer.parseInt(st.nextToken());
                T = Integer.parseInt(st.nextToken());

                if (i < M) {
                    graph.get(S).add(new Edge(E, T));
                    graph.get(E).add(new Edge(S, T));
                } else {
                    graph.get(S).add(new Edge(E, -T));
                }
            }

            /*
             예를 들어서 1에서 출발하여 5에 도착하고 그리고 5에서 출발하여 1까지 도착하였을때 시간이 더 줄어들었냐?

             */
            boolean isMinusCycle = false;
            for (int i = 1; i <= N; i++) {
                if (BellmanFord(i)) {
                    isMinusCycle = true;
                    sb.append("YES").append("\n");
                    break;
                }
            }
            if (!isMinusCycle) {
                sb.append("NO").append("\n");
            }
        }
        System.out.print(sb);
    }


    //정점의 개수, 간선의 개수, 출발지
    public static boolean BellmanFord(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        boolean update = false;

        for (int i = 1; i < N; i++) {
            update = false;
            //정점의 개수만큼 반복
            for (int j = 1; j <= N; j++) {
                //간선의 개수만큼 반복
                for (Edge edge : graph.get(j)) {
                    //현재 간선의 들어오는 정점에 대해 비교
                    if (dist[j] != INF && dist[edge.index] > dist[j] + edge.cost) {
                        dist[edge.index] = dist[j] + edge.cost;
                        update = true;
                    }
                }
            }
            if (!update) break;
        }

        //음수 가중치 확인
        if (update) {
            for (int i = 1; i <= N; i++) {
                for (Edge edge : graph.get(i)) {
                    //현재 간선의 들어오는 정점에 대해 비교 -> 더 작은 값 생기면 음수 사이클 존재
                    if (dist[i] != INF && dist[edge.index] > dist[i] + edge.cost) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

class Edge {
    int index;
    int cost;

    public Edge(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
}

