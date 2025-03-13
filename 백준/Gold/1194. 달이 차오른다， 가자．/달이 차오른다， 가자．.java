
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static char[][] map;
	static Node start;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {

		// 입력
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String str = bf.readLine();
		StringTokenizer st = new StringTokenizer(str);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			str = bf.readLine();
			for (int j = 0; j < M; j++) {
				char c = str.charAt(j);
				map[i][j] = c;
				if (c == '0')
					start = new Node(i, j, 0, 0);
			}
		}
		// 입력 끝
		System.out.println(bfs());
	}

	public static int bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[N][M][64]; // 열쇠 가지고 방문 여부 체크.
		q.offer(start);
		visited[start.x][start.y][0] = true;

		while (!q.isEmpty()) {
			Node current = q.poll();
			if (map[current.x][current.y] == '1')
				return current.cost;

			for (int i = 0; i < 4; i++) {
				int nx = current.x + dx[i];
				int ny = current.y + dy[i];

				if (range(nx, ny) && !visited[nx][ny][current.key] && map[nx][ny] != '#') {
					if (isKey(nx, ny)) { // 열쇠
						int next_key = 1 << (map[nx][ny] - 'a');
						next_key = current.key | next_key;
						visited[nx][ny][next_key] = true;
						q.offer(new Node(nx, ny, current.cost + 1, next_key));
					} else if (isDoor(nx, ny)) { // 문
						if ((current.key & 1 << (map[nx][ny] - 'A')) == (int) Math.pow(2, map[nx][ny] - 'A')) { // 해당
																												// 비트가
																												// 켜져있는지
																												// // 확인
							visited[nx][ny][current.key] = true;
							q.offer(new Node(nx, ny, current.cost + 1, current.key));
						}
					} else {
						visited[nx][ny][current.key] = true;
						q.offer(new Node(nx, ny, current.cost + 1, current.key));
					}
				}
			}
		}
		return -1;
	}

	private static boolean isKey(int x, int y) {
		return map[x][y] >= 'a' && map[x][y] <= 'f';
	}

	private static boolean isDoor(int x, int y) {
		return map[x][y] >= 'A' && map[x][y] <= 'F';
	}

	private static boolean range(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

	private static class Node {
		int x, y, cost, key;

		public Node(int x, int y, int cost, int key) {
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.key = key;
		}
	}
}
