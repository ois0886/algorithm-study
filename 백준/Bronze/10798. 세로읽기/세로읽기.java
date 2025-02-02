import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
	// 백준알고리즘 10798번 세로 읽기
	static String[][] arr = new String[5][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int max = 0;

		for (int i = 0; i < 5; i++) {

			String str = br.readLine();
			arr[i] = new String[str.length()];
			arr[i] = str.split("");

			if (arr[i].length >= max) {
				max = arr[i].length;
			}
		}


		for (int j = 0; j < max; j++) {
			for (int i = 0; i < 5; i++) {
				if (j > arr[i].length - 1) {
					continue;
				} else {
					sb.append(arr[i][j]);
				}
			}
		}
		System.out.println(sb);

	}

}
