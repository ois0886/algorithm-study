import java.util.*;
import java.io.*;

class Main {
    static int H; // 세로(높이)
    static int W; // 가로(너비)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력: 높이(H), 너비(W)
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        int[] Harr = new int[W]; // 각 열에 해당하는 블록의 높이 저장

        // 입력: 블록의 높이 배열
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            Harr[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0; // 고이는 빗물 총량

        // 첫 칸과 마지막 칸은 물이 고일 수 없으므로 1부터 W-2까지만 확인
        for (int i = 1; i < W - 1; i++) {
            int left = 0;   // 현재 칸 기준 왼쪽에서 가장 높은 블록
            int right = 0;  // 현재 칸 기준 오른쪽에서 가장 높은 블록

            // 왼쪽 최대 높이 계산
            for (int j = 0; j < i; j++) {
                left = Math.max(Harr[j], left);
            }

            // 오른쪽 최대 높이 계산
            for (int j = i + 1; j < W; j++) {
                right = Math.max(Harr[j], right);
            }

            // 현재 칸보다 양쪽 벽이 모두 높을 경우 고일 수 있는 물의 양을 계산
            if (Harr[i] < left && Harr[i] < right) {
                result += Math.min(left, right) - Harr[i];
            }
        }

        // 결과 출력: 전체 고인 물의 양
        System.out.println(result);
    }
}
