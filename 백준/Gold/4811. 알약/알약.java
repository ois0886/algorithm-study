import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		long[] dp = new long[31];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i <= 30; i++) {
			long cnt = 0;

			for (int j = 0; j < i; j++) {
				cnt += dp[j] * dp[i - 1 - j];
			}

			dp[i] = cnt;
		}

		StringBuilder sb = new StringBuilder();
		while (true) {
			int N = Integer.parseInt(br.readLine());

			if (N == 0) {
				break;
			}

			sb.append(dp[N] + "\n");
		}
		System.out.println(sb);
	}

}