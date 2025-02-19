import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int score_num = Integer.parseInt(st.nextToken());
			int[] arr = new int[score_num];

			int sum = 0;
			for (int j = 0; j < score_num; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
				sum += arr[j];
			}

			double average = (double) sum / score_num; // 평균 계산
			int count = 0;

			for (int score : arr) {
				if (score > average) {
					count++;
				}
			}

			double percentage = ((double) count / score_num) * 100;
			System.out.printf("%.3f%%\n", percentage);

		}

	}

}