import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		char[] T = in.readLine().toCharArray(); // 본문
		char[] P = in.readLine().toCharArray(); // 패턴

		int tLength = T.length;
		int pLength = P.length;

		// 1단계: 부분일치 테이블 만들기 (pi 배열)
		int[] next = new int[pLength];

		for (int i = 1, j = 0; i < pLength; i++) {
			while (j > 0 && P[i] != P[j]) {
				j = next[j - 1];
			}
			if (P[i] == P[j]) {
				next[i] = ++j;
			}
		}

		// 2단계: KMP 탐색
		int cnt = 0;
		List<Integer> list = new ArrayList<>();

		for (int i = 0, j = 0; i < tLength; i++) {
			while (j > 0 && T[i] != P[j]) {
				j = next[j - 1];
			}
			if (T[i] == P[j]) {
				if (j == pLength - 1) { // 패턴 발견
					cnt++;
					list.add(i - pLength + 2); // +2: 0-based -> 1-based 변환

					j = next[j]; // 패턴 매칭이 끝났으므로 j를 이동
				} else {
					j++;
				}
			}
		}

		// 결과 출력
		System.out.println(cnt);
		if (cnt > 0) {
			for (int index : list) {
				System.out.print(index + " ");
			}
			System.out.println();
		}
	}
}