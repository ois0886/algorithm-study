
import java.io.*;
import java.util.*;

public class Main {
    private static final Map<Long, Long> memoization = new HashMap<>();
    private static int p, q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        
        System.out.println(solve(n));
    }

    private static long solve(long num) {
        if (num == 0) {
            return 1;
        }
        
        if (memoization.containsKey(num)) {
            return memoization.get(num);
        }
        
        long result = solve(num / p) + solve(num / q);
        memoization.put(num, result);
        return result;
    }
}