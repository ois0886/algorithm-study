import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, R, C, ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int size = (1 << N); // 2^N
        findPosition(R, C, size);
        System.out.println(ans);
    }

    private static void findPosition(int r, int c, int size) {
        while (size > 1) {
            size /= 2; // 현재 탐색할 사분면 크기를 줄임
            
            // 현재 좌표가 속한 사분면을 판별하고, 이전 사분면 개수만큼 ans를 증가시킴
            if (r < size && c < size) { // 1사분면
                ans += 0;
            } else if (r < size && c >= size) { // 2사분면
                ans += size * size;
                c -= size;
            } else if (r >= size && c < size) { // 3사분면
                ans += 2 * size * size;
                r -= size;
            } else { // 4사분면
                ans += 3 * size * size;
                r -= size;
                c -= size;
            }
        }
    }
}