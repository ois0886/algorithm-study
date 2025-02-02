import java.io.*;
import java.util.*;

public class Main {
	static String[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int[] alpha = new int[26];
		for (int i = 0; i < str.length(); i++) {
			int idx = str.charAt(i) - 'A'; // 주어진 문자의 알파뱃 갯수 체크
			alpha[idx]++;
		}

		int isOne = 0;
		for (int j : alpha) { // 알파뱃이 짝수가 아닐 경우, 문제가 됨.
			if (j % 2 != 0)
				isOne++;
		}

		String answer = "";
		StringBuilder sb = new StringBuilder();
		if (isOne > 1)
			answer += "I'm Sorry Hansoo";
		else {
			for (int i = 0; i < alpha.length; i++) {
				sb.append(String.valueOf((char) (i + 65)).repeat(Math.max(0, alpha[i] / 2)));
			}
			answer += sb.toString();
			String end = sb.reverse().toString();

			sb = new StringBuilder();
			for (int i = 0; i < alpha.length; i++) {
				if (alpha[i] % 2 == 1) {
					sb.append((char) (i + 65));
				}
			}
			answer += sb + end;
		}
		System.out.println(answer);

	}

}
