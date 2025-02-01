import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준알고리즘 9663번 N-Queen

/**
 * 완전 탐색과 백트래킹으로 푸는 문제이다.
 * 크기가 N X N인 체스판 위에 퀸 N개를 서로 공격할 수 없도록 놓은 경우의 수를 구하는 것
 * 퀸이 놓였을 때 퀸 자신을 기준으로 일직선상(가로 및 세로)과 대각선 방향에는 아무것도 놓여있으면 안된다.
 */

public class Main {

    static int N;
    static int[] arr = new int[15];
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nQueen(0);
        System.out.println(count);
    }

    private static boolean promising(int cdx) {
        for (int i = 0; i < cdx; i++) {
            if (arr[cdx] == arr[i] || cdx - i == Math.abs(arr[cdx] - arr[i])){
                return false;
            }
        } return true;
    }

    private static void nQueen(int cdx) {
        if (cdx == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[cdx] = i;
            if (promising(cdx)) {
                nQueen(cdx + 1);
            }
        }
    }
}
