import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		int sum = 0;
		int result = 0;

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}

		while (sum > 0) {
			int num = 0;
			for (int i = 0; i < N; i++) {
				if (arr[i] % 2 == 1) {
					arr[i] -= 1;
					num++;
				}
			}
			if (num > 0) {
				sum -= num;
				result += num;
			} else {
				for (int i = 0; i < N; i++) {
					arr[i] /= 2;

				}
				sum /= 2;
				result++;
			}
		}
		System.out.println(result);
	}

}