import java.io.*;
import java.util.*;

public class Main {
	static char[][] map = new char[5][5];
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static List<int[]> students = new ArrayList<>();
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 5; i++) {
			String line = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = line.charAt(j);
				students.add(new int[] { i, j });
			}
		}


		combination(0, 0, new ArrayList<>());
		System.out.println(result);
	}

	static void combination(int start, int depth, List<int[]> selected) {
		if (depth == 7) {
			if (isValid(selected)) {
				result++;
			}
			return;
		}

		for (int i = start; i < 25; i++) {
			selected.add(students.get(i));
			combination(i + 1, depth + 1, selected);
			selected.remove(selected.size() - 1);
		}
	}


	static boolean isValid(List<int[]> selected) {
		
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[5][5];

		queue.add(selected.get(0));
		visited[selected.get(0)[0]][selected.get(0)[1]] = true;

		int connectedCount = 1;
		int sCount = map[selected.get(0)[0]][selected.get(0)[1]] == 'S' ? 1 : 0;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0], y = cur[1];

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (range(nx, ny) && !visited[nx][ny]) {
					for (int[] student : selected) {
						if (student[0] == nx && student[1] == ny) {
							queue.add(new int[] { nx, ny });
							visited[nx][ny] = true;
							connectedCount++;

							if (map[nx][ny] == 'S') {
								sCount++;
							}
						}
					}
				}
			}
		}

		return connectedCount == 7 && sCount >= 4;
	}

	static boolean range(int x, int y) {
		return x >= 0 && x < 5 && y >= 0 && y < 5;
	}
}