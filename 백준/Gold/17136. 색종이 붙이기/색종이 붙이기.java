import java.util.*;
import java.io.*;

public class Main {
	static int[][] req = new int[10][10];
	static int min = -1;

	static int[] squareCnt = { 0, 5, 5, 5, 5, 5 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				req[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		backtracking(0, 0, 0);
		System.out.println(min);
	}

	static boolean isSquare(int y, int x, int s) {
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				if (req[y + i][x + j] == 0)
					return false;
			}
		}
		return true;
	}

	static void checkSquare(int y, int x, int s, int useSquare) {
		if (useSquare == 1)
			squareCnt[s]++;
		else
			squareCnt[s]--;

		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				req[y + i][x + j] = useSquare;
			}
		}
	}

	static void backtracking(int y, int x, int cnt) {
		if (x > 9) {
			backtracking(y + 1, 0, cnt);
			return;
		}

		if (y > 9) {
			if (min == -1)
				min = cnt;
			else if (min > cnt)
				min = cnt;
			return;
		}
		if (req[y][x] == 0) {
			backtracking(y, x + 1, cnt);
			return;
		}

		for (int s = 5; s >= 1; s--) {
			if (squareCnt[s] != 0 && y + s <= 10 && x + s <= 10) {
				if (isSquare(y, x, s)) {
					checkSquare(y, x, s, 0);
					backtracking(y, x + s, cnt + 1);
					checkSquare(y, x, s, 1);
				}
			}
		}
	}

}