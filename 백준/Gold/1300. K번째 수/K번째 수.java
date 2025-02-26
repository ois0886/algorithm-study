import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long N;
    static long k;

    public static long calc(long x) {
        long ret = 0;
        for (int i = 1; i <= N; i++)
            ret += Math.min(N, x / i);
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        long left = 0, right = N * N;
        while (left <= right) {
            long mid = (left + right) >> 1;
            if (calc(mid) >= k) right = mid - 1;
            else left = mid + 1;
        }
        System.out.println(right + 1);
    }
}