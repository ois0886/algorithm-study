import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int[][] map;
	private static int[][] weight;
	private static boolean[][] visited;
	private static int[] dx = { 0, 0, -1, 1 };
	private static int[] dy = { 1, -1, 0, 0 };

	private static final int INF = 999999;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int index = 1;

		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;

			map = new int[N][N];
			visited = new boolean[N][N];
			weight = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					weight[i][j] = INF;
				}
			}
			Dijkstra(0, 0, map[0][0]);

			sb.append("Problem ").append(index++).append(": ").append(weight[N - 1][N - 1]).append("\n");
		}
		System.out.println(sb);

	}

	private static void Dijkstra(int x, int y, int money) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		visited[x][y] = true;
		pq.offer(new Node(x, y, money));

		while (!pq.isEmpty()) {
			Node node = pq.poll();

			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];

				if (range(nx, ny) && !visited[nx][ny] && weight[nx][ny] > (map[nx][ny] + node.weight)) {
					weight[nx][ny] = map[nx][ny] + node.weight;
					visited[nx][ny] = true;
					pq.offer(new Node(nx, ny, weight[nx][ny]));
				}
			}
		}

	}

	private static boolean range(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

	static class Node implements Comparable<Node> {
		int x, y;
		int weight;

		public Node(int x, int y, int weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node n) {
			return this.weight - n.weight;
		}

	}
}