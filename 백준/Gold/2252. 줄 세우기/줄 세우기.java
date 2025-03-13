

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static int M;
	private static ArrayList<ArrayList<Integer>> graph;
	private static int[] edgeCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList();
		edgeCount = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			graph.get(first).add(second);
			graph.get(second).add(first);
			edgeCount[second]++;
		}

		Queue<Integer> que = new ArrayDeque<Integer>();

		for (int i = 1; i < edgeCount.length; i++) {
			if (edgeCount[i] == 0)
				que.offer(i);
		}

		while (!que.isEmpty()) {
			int nodeNo = que.poll();
			ArrayList<Integer> list = graph.get(nodeNo);
			sb.append(nodeNo).append(" ");
			for (int i = 0; i < list.size(); i++) {
				edgeCount[list.get(i)]--;
				if (edgeCount[list.get(i)] == 0) {
					que.offer(list.get(i));
				}
			}
		}

		System.out.println(sb);

	}

}
