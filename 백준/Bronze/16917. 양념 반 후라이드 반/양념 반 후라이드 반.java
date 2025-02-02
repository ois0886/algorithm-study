import java.util.*;
import java.io.*;

class Main {

    static int A, B, C, X, Y;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken()); // 양념 가격
        B = Integer.parseInt(st.nextToken()); // 후라이드 가격
        C = Integer.parseInt(st.nextToken()); // 반반 가격
        X = Integer.parseInt(st.nextToken()); // 구매 양념 치킨
        Y = Integer.parseInt(st.nextToken()); // 구매 후라이드 치킨
        long result = 0;

        // 반반 2개는 -> 양념1개, 후라이드 1개 가능
        // 양념과 후라이드 공통 처리
        int min = Math.min(X, Y);

        if (A + B > C * 2) {
            result += (2L * min * C);
        } else {
            result += ((long) A + B) * min;
        }

        // 양념 남은거 처리
        if (X > min) {
            X = X - min;
            result += Math.min(X * A, X * C * 2);
        } else {// 후라이드 남은거 처리
            Y = Y - min;
            result += Math.min(Y * B, Y * C * 2);
        }

        System.out.println(result);

    }


}