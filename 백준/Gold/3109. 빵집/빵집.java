import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준알고리즘 3109번 빵집
public class Main {
    static int N, M;
    static char[][] arr;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j);
            }
        }
        result = 0;
        for (int i = 0; i < N; i++)
            if (check(i, 0))
                result++;
        System.out.println(result);


    }

    public static boolean check(int x, int y) {
        arr[x][y] = '-';
        if (y == M - 1)
            return true;

        if (x > 0 && arr[x - 1][y + 1] == '.')
            if (check(x - 1, y + 1))
                return true;
        if (arr[x][y + 1] == '.')
            if (check(x, y + 1))
                return true;
        if (x + 1 < N && arr[x + 1][y + 1] == '.')
            if (check(x + 1, y + 1))
                return true;
        return false;
    }
}