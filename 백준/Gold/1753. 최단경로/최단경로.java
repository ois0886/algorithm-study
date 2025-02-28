import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int v, w;

    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Node n) {
        return this.w - n.w;
    }

}


public class Main {
    static int V, E, start, w;
    static ArrayList[] list;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine()) - 1;
        list = new ArrayList[V];
        dist = new int[V];
        for (int i = 0; i < V; i++) {
            list[i] = new ArrayList<Node>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            list[u].add(new Node(v, w));

        }

        dijkstra(start);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                sb.append("INF").append("\n");
            else
                sb.append(dist[i]).append("\n");
        }

        System.out.print(sb);
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        dist[start] = 0;
        priorityQueue.add(new Node(start, 0));

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();

            int len = list[node.v].size();
            for (int i = 0; i < len; i++) {
                Node next = (Node) list[node.v].get(i);

                if (dist[next.v] > node.w + next.w) {
                    dist[next.v] = node.w + next.w;
                    priorityQueue.add(new Node(next.v, dist[next.v]));
                }
            }
        }
    }

}
