import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		Node[] arr = new Node[n];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			arr[i] = new Node(start, end);
		}

		Arrays.sort(arr);
		int end = 0;
		int answer = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i].start >= end) {
				answer++;
				end = arr[i].end;
			}
		}
		System.out.println(answer);
	}

}

class Node implements Comparable<Node> {

	int start;
	int end;

	Node(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Node o) {
		if (this.end == o.end) {
			return this.start - o.start;
		}
		return this.end - o.end;
	}

}