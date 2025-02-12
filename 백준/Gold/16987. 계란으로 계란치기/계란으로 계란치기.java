import java.util.*;
import java.io.*;

public class Main {

	static int N, S, W;

	static Egg[] arr;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new Egg[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			arr[i] = new Egg(S, W);
		}

		backTracking(0, 0);
		System.out.println(max);
	}

	private static void backTracking(int index, int count) {
		if (index == N) {
			max = Math.max(max, count);
			return;
		}
		if (arr[index].strength <= 0 || count == N - 1) {
			backTracking(index + 1, count);
			return;
		}

		int ncount = count;
		for (int i = 0; i < N; i++) {
			if (i == index) {
				continue;
			}
			if (arr[i].strength <= 0)
				continue;

			arr[i].strength -= arr[index].weight;
			arr[index].strength -= arr[i].weight;

			if (arr[index].strength <= 0)
				count++;
			if (arr[i].strength <= 0)
				count++;

			backTracking(index + 1, count);
			arr[i].strength += arr[index].weight;
			arr[index].strength += arr[i].weight;
			count = ncount;
		}
	}

}

class Egg {

	int weight; // 무게
	int strength; // 내구도

	Egg(int strength, int weight) {
		this.weight = weight;
		this.strength = strength;
	}
}