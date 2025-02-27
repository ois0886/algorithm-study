import java.io.*;
import java.util.*;

public class Main {
	
	private static int N, M;
	private static int[][] map;
	private static int[][] copyMap;
	private static int[] output;
	private static ArrayList<CCTV> cctvList;
	private static int[] dx = { -1, 0, 1, 0 }; // 상 우 하 좌 시계방향 순서
	private static int[] dy = { 0, 1, 0, -1 };
	private static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctvList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] != 0 && map[i][j] != 6) {
					cctvList.add(new CCTV(map[i][j], i, j));
				}
			}
		}

		output = new int[cctvList.size()]; // 순열을 담을 배열
		permutation(0, cctvList.size());

		System.out.println(answer);
	}
	
	// DFS 
	public static void permutation(int depth, int r) {
		if (depth == r) {

			copyMap = new int[N][M];
			for (int i = 0; i < map.length; i++) {
				System.arraycopy(map[i], 0, copyMap[i], 0, map[i].length);
			}

			for (int i = 0; i < cctvList.size(); i++) {
				direction(cctvList.get(i), output[i]);
			}

			getBlindSpot();

			return;
		}

		for (int i = 0; i < 4; i++) {
			output[depth] = i;
			permutation(depth + 1, r);
		}
	}

	private static void direction(CCTV cctv, int d) {
		int cctvNum = cctv.num;

		if (cctvNum == 1) {
			if (d == 0)
				watch(cctv, 0);
			else if (d == 1)
				watch(cctv, 1);
			else if (d == 2)
				watch(cctv, 2);
			else if (d == 3)
				watch(cctv, 3);
		} else if (cctvNum == 2) {
			if (d == 0 || d == 2) {
				watch(cctv, 0);
				watch(cctv, 2);
			} else {
				watch(cctv, 1);
				watch(cctv, 3);
			}
		} else if (cctvNum == 3) {
			if (d == 0) {
				watch(cctv, 0);
				watch(cctv, 1);
			} else if (d == 1) {
				watch(cctv, 1);
				watch(cctv, 2);
			} else if (d == 2) {
				watch(cctv, 2);
				watch(cctv, 3);
			} else if (d == 3) {
				watch(cctv, 0);
				watch(cctv, 3);
			}
		} else if (cctvNum == 4) {
			if (d == 0) {
				watch(cctv, 0);
				watch(cctv, 1);
				watch(cctv, 3);
			} else if (d == 1) {
				watch(cctv, 0);
				watch(cctv, 1);
				watch(cctv, 2);
			} else if (d == 2) {
				watch(cctv, 1);
				watch(cctv, 2);
				watch(cctv, 3);
			} else if (d == 3) {
				watch(cctv, 0);
				watch(cctv, 2);
				watch(cctv, 3);
			}
		} else if (cctvNum == 5) {
			watch(cctv, 0);
			watch(cctv, 1);
			watch(cctv, 2);
			watch(cctv, 3);
		}
	}

	// BFS로 방향에 맞게 감시
	private static void watch(CCTV cctv, int d) {
		Queue<CCTV> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];

		queue.add(cctv);
		visited[cctv.x][cctv.y] = true;

		while (!queue.isEmpty()) {
			int nx = queue.peek().x + dx[d];
			int ny = queue.poll().y + dy[d];

			if (!range(nx, ny) || copyMap[nx][ny] == 6) {
				break;
			}

			if (copyMap[nx][ny] == 0) {
				copyMap[nx][ny] = -1;
				queue.add(new CCTV(cctv.num, nx, ny));
			} else {
				queue.add(new CCTV(cctv.num, nx, ny));
			}
		}
	}

	private static boolean range(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < M;
	}

	// 사각 지대 구하기
	private static void getBlindSpot() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == 0) {
					cnt++;
				}
			}
		}
		answer = Math.min(answer, cnt);
	}

}

class CCTV {
	int num;
	int x;
	int y;

	CCTV(int num, int x, int y) {
		this.num = num;
		this.x = x;
		this.y = y;
	}
}