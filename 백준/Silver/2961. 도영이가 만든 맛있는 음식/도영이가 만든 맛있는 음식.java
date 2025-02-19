import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Food {
	int sour; // 신맛
	int bitter; // 쓴맛

	Food(int sour, int bitter) {
		this.sour = sour;
		this.bitter = bitter;
	}

}

public class Main {

	static int N;

	static int min;

	static Food[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new Food[N];

		min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int sour = Integer.parseInt(st.nextToken()); // 신맛
			int bitter = Integer.parseInt(st.nextToken()); // 쓴맛
			arr[i] = new Food(sour, bitter);
		}

		for (int i = 1; i <= N; i++) {
			int[] numbers = new int[i];
			combination(0, 0, i, numbers);
		}

		System.out.println(min);

	}

	private static void combination(int cnt, int start, int R, int[] numbers) {
		if (cnt == R) {
			int sour_sum = 1;
			int bitter_sum = 0;
			for (int i = 0; i < numbers.length; i++) {
				sour_sum *= arr[numbers[i]].sour;
				bitter_sum += arr[numbers[i]].bitter;
			}

			int result = 0;
			if (sour_sum - bitter_sum > 0) {
				result = sour_sum - bitter_sum;
			} else {
				result = bitter_sum - sour_sum;
			}

			min = Math.min(min, result);
			return;
		}

		// 유도부분
		for (int i = start; i < N; i++) { // 가능한 모든 수 시도
			numbers[cnt] = i; // 숫자뽑기
			combination(cnt + 1, i + 1, R, numbers);
		}
	}

}