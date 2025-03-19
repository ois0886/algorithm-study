import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Deque<int[]> deque = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			// 1. 덱에서 현재 윈도우 범위를 벗어난 값 제거
			if (!deque.isEmpty() && deque.peekFirst()[1] <= i - L) {
				deque.pollFirst();
			}

			// 2. 새 값이 들어올 때 덱 뒤에서 큰 값들은 제거
			while (!deque.isEmpty() && deque.peekLast()[0] > arr[i]) {
				deque.pollLast();
			}

			// 3. 현재 값 삽입 (값, 인덱스)
			deque.offerLast(new int[] { arr[i], i });

			// 4. 최솟값 추가 (덱의 앞이 항상 최소)
			sb.append(deque.peekFirst()[0]).append(" ");
		}

		System.out.println(sb.toString());
	}
}