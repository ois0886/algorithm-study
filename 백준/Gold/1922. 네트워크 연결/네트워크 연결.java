import java.io.*;
import java.util.*;

class Computer implements Comparable<Computer> {
    int start, end, cost;

    public Computer(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Computer other) {
        return this.cost - other.cost;
    }
}

public class Main {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        PriorityQueue<Computer> que = new PriorityQueue<>();
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            que.offer(new Computer(start, end, cost));
        }

        int result = 0;
        while (!que.isEmpty()) {
            Computer computer = que.poll();
            if (find(computer.start) != find(computer.end)) {
                union(computer.start, computer.end);
                result += computer.cost;
            }
        }

        System.out.println(result);
    }

    private static void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 < p2) {
            parent[p2] = p1;
        } else {
            parent[p1] = p2;
        }
    }

    private static int find(int v) {
        if (parent[v] == v) {
            return v;
        } else {
            return parent[v] = find(parent[v]);  // 경로 압축 (Path Compression)
        }
    }
}
