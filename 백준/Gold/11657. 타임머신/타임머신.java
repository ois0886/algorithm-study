import java.io.*;
import java.util.*;

class Edge {
	int end, weight;

	public Edge(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
}

public class Main {
	static int N, M;
	static ArrayList<ArrayList<Edge>> graph;
	static long[] dist;
	static final long INF = 10000000000L; // 오버플로우 방지

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 도시 개수
		M = Integer.parseInt(st.nextToken()); // 버스 노선 개수

		graph = new ArrayList<>();
		dist = new long[N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			dist[i] = INF;
		}

		// 단방향 인접 리스트 생성
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			graph.get(A).add(new Edge(B, C));
		}

		if (bellmanFord()) {
			System.out.println("-1"); // 음수 사이클 존재
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 2; i <= N; i++) {
				if (dist[i] == INF) {
					sb.append("-1\n");
				} else {
					sb.append(dist[i]).append("\n");
				}
			}
			System.out.print(sb.toString());
		}
	}

	public static boolean bellmanFord() {
		dist[1] = 0; // 시작 노드 초기화

		// (N-1)번 반복하며 최단 거리 갱신
		for (int i = 1; i < N; i++) {
			boolean update = false;

			for (int j = 1; j <= N; j++) {
				for (Edge city : graph.get(j)) {
					if (dist[j] != INF && dist[city.end] > dist[j] + city.weight) {
						dist[city.end] = dist[j] + city.weight;
						update = true;
					}
				}
			}

			// 더 이상 갱신이 발생하지 않으면 조기 종료
			if (!update)
				break;
		}

		// 음수 사이클 검사
		for (int i = 1; i <= N; i++) {
			for (Edge city : graph.get(i)) {
				if (dist[i] != INF && dist[city.end] > dist[i] + city.weight) {
					return true; // 음수 사이클 존재
				}
			}
		}

		return false;
	}
}
