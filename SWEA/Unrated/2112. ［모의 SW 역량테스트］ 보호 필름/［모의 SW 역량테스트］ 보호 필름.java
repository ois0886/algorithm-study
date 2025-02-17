import java.util.*;
import java.io.*;

class Solution {

	static int D, W, K;
	static int[][] arr;
	static boolean[] visited;
	static int[][] tmp;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			st = new StringTokenizer(br.readLine());

			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[D][W];
			tmp = new int[D][W];
			visited = new boolean[D];
			result = Integer.MAX_VALUE;

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken()); // 0이 A, 1이 B
					tmp[i][j] = arr[i][j];
				}
			}

			if (checking()) {
				sb.append(0).append("\n");
			} else {
				dfs(0, 0);
				sb.append(result).append("\n");
			}
		}

		System.out.print(sb);
	}

	static void dfs(int floor, int cnt) { // cnt:줄 색깔 바꾼 횟수
		if (cnt >= result)
			return;

		if (floor == D) {
			if (checking()) {
				result = Math.min(result, cnt); // 제일 적게 바꿔준 횟수
			}
			return;
		}

		dfs(floor + 1, cnt); // 색칠 안함

		for (int i = 0; i < W; i++) {
			tmp[floor][i] = 0; // A로 색칠
		}
		dfs(floor + 1, cnt + 1);

		for (int i = 0; i < W; i++) {
			tmp[floor][i] = 1; // B로 색칠
		}
		dfs(floor + 1, cnt + 1);

		for (int i = 0; i < W; i++) {
			tmp[floor][i] = arr[floor][i]; // 원복
		}
	}

	static boolean checking() {
		for (int x = 0; x < W; x++) {
			boolean isOk = false;
			int cnt = 1;
			for (int y = 1; y < D; y++) {
				if (tmp[y][x] == tmp[y - 1][x])
					cnt++;
				else
					cnt = 1;
				if (cnt == K) {
					isOk = true;
					break;
				}
			}
			if (!isOk)
				return false;
		}
		return true;
	}
}
