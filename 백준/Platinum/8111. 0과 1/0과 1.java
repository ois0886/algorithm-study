import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			sb.append(BFS(N));
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	public static String BFS(int num) {
		Queue<Pair> queue = new ArrayDeque<>();

		boolean[] visited = new boolean[20001];

		queue.add(new Pair("1", 1));

		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			int curNum = cur.num;
			String curStr = cur.str;
			if (curNum == 0) {
				return curStr;
			}
			if (curStr.length() > 100) {
				return "BRAK";
			}
			int plusZero = (curNum * 10) % num;
			if (!visited[plusZero]) {
				visited[plusZero] = true;
				queue.add(new Pair(curStr + "0", plusZero));
			}
			int plusOne = (curNum * 10 + 1) % num;
			if (!visited[plusOne]) {
				visited[plusOne] = true;
				queue.add(new Pair(curStr + "1", plusOne));
			}
		}
		return "BRAK";
	}
}

class Pair {
	String str;
	int num;

	public Pair(String str, int num) {
		this.str = str;
		this.num = num;
	}
}