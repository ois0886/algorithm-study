import java.util.*;
import java.io.*;

public class Main {
	static int N, M, D;
	static int[] selected = new int[3]; // 궁수 위치(열) 저장
	static List<Enemy> enemy_list;
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		enemy_list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					enemy_list.add(new Enemy(i, j));
				}
			}
		}

		combination(0, 0);
		System.out.println(result);
	}

	private static void combination(int start, int depth) {
		if (depth == 3) {
			simulation();
			return;
		}

		for (int i = start; i < M; i++) {
			selected[depth] = i;
			combination(i + 1, depth + 1);
		}
	}

	private static void simulation() {
		List<Enemy> enemies = new ArrayList<>();
		for (Enemy e : enemy_list) {
			enemies.add(new Enemy(e.x, e.y));
		}

		int killed = 0;

		while (!enemies.isEmpty()) {

			Set<Enemy> targets = new HashSet<>();

			for (int i = 0; i < 3; i++) {
				Enemy target = findTarget(enemies, selected[i]);
				if (target != null)
					targets.add(target);
			}

			killed += targets.size();
			enemies.removeAll(targets);

			Iterator<Enemy> it = enemies.iterator();
			while (it.hasNext()) {
				Enemy e = it.next();
				e.x++;
				if (e.x >= N)
					it.remove();
			}
		}

		result = Math.max(result, killed);
	}

	private static Enemy findTarget(List<Enemy> enemies, int archerY) {
		Enemy target = null;
		int minDist = Integer.MAX_VALUE;

		for (Enemy e : enemies) {
			int dist = Math.abs(N - e.x) + Math.abs(archerY - e.y); // 거리 계산
			if (dist > D)
				continue;

			if (dist < minDist || (dist == minDist && e.y < target.y)) {
				minDist = dist;
				target = e;
			}
		}
		return target;
	}
}

class Enemy {
	int x, y;

	Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Enemy) {
			Enemy other = (Enemy) obj;
			return this.x == other.x && this.y == other.y;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}