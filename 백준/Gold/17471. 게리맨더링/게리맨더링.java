import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] population, area;
	static ArrayList<Integer>[] list;
	static int min = Integer.MAX_VALUE;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		population = new int[N + 1];
		list = new ArrayList[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
			population[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		area = new int[N + 1]; // 각 지역구가 속한 선거구 저장. 1 또는 2
		dfs(1); // 뽑을 수 있는 모든 지역구를 뽑는 dfs탐색

		if (min == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(min);
	}

	public static void dfs(int k) {
		if (k == N + 1) { // 모든 지역 다 뽑았다면
			int area1 = 0;
			int area2 = 0;
			for (int i = 1; i <= N; i++) {
				if (area[i] == 1)
					area1 += population[i];
				else
					area2 += population[i];
			}

			visited = new boolean[N + 1];
			int link = 0; // 연결된 지역들의 개수를 셈.
			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					bfs(i, area[i]); // 연결된 지역들을 모두 방문표시하는 bfs탐색
					link++;
				}
			}
			// 지역이 2개뤄 나눠졌고, 2지역안에서 서로 연결되어 있다면 최소값 계산
			if (link == 2)
				min = Math.min(min, Math.abs(area1 - area2));

			return;
		}

		area[k] = 1; // k지역 1번 선거구에 할당.
		dfs(k + 1);

		area[k] = 2; // k지역 2번 선거구에 할당.
		dfs(k + 1);
	}

	public static void bfs(int idx, int areaNum) {
		Queue<Integer> queue = new LinkedList<>();
		visited[idx] = true;
		queue.offer(idx);

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for (int i = 0; i < list[current].size(); i++) {
				int next = list[current].get(i);
				if (area[next] == areaNum && !visited[next]) {
					queue.offer(next);
					visited[next] = true;
				}
			}
		}
	}
}