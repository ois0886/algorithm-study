import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] DP;
    static int N;
    static int result1 = 0, result2 = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        DP = new int[N + 1];

        fibonacci(N);
        fib(N);

        System.out.println(result1);
        System.out.println(result2);
    }

    public static int fib(int n) {
        if (n == 1 || n == 2) {
            result1++;
            return 1;
        } else return (fib(n - 1) + fib(n - 2));
    }

    public static int fibonacci(int n) {
        DP[1] = DP[2] = 1;
        for (int i = 3; i <= n; i++) {
            DP[i] = DP[i - 1] + DP[i - 2];
            result2++;
        }
        return DP[n];
    }
}
