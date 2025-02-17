import java.io.*;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= T; test_case++) {
			int K = Integer.parseInt(br.readLine());
			int[][] info = new int[4][8];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					info[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 명령을 K번 실행
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken());

				int[] rotation = new int[4]; // 회전할 방향을 저장
				rotation[num] = dir;

				// 오른쪽 자석들을 검사
				for (int j = num + 1; j < 4; j++) {
					if (info[j - 1][2] == info[j][6]) // 자성이 같으면 회전하지 않는다.
						break;
					else
						rotation[j] = -rotation[j - 1];
				}

				// 왼쪽 자석들을 검사
				for (int j = num - 1; j >= 0; j--) {
					if (info[j][2] == info[j + 1][6]) // 자성이 같으면 회전하지 않는다.
						break;
					else
						rotation[j] = -rotation[j + 1];
				}

				// 각 자석을 회전시킨다.
				for (int j = 0; j < 4; j++) {
					if (rotation[j] == 0)
						continue; // 회전하지 않는 경우
					else if (rotation[j] == 1) { // 시계방향 회전
						int tmp = info[j][7];
						for (int k = 7; k > 0; k--) {
							info[j][k] = info[j][k - 1];
						}
						info[j][0] = tmp;
					} else if (rotation[j] == -1) { // 반시계방향 회전
						int tmp = info[j][0];
						for (int k = 0; k < 7; k++) {
							info[j][k] = info[j][k + 1];
						}
						info[j][7] = tmp;
					}
				}

			}

			// 모든 실행이 끝난 후 총합을 계산
			int sum = 0;
			for (int i = 0; i < 4; i++) {
				if (info[i][0] == 1) {
					sum += 1 << i;
				}
			}

			sb.append("#").append(test_case).append(" ").append(sum).append("\n");
		}
		System.out.println(sb);
	}

}