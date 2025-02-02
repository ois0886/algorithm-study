import java.io.*;
import java.util.*;

// 백준알고리즘 16928번 뱀과 사다리 게임

class Main {
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 사다리
        int M = Integer.parseInt(st.nextToken()); // 뱀

        arr = new int[101];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = i;
        }

        int num = N + M;
        while (num-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x] = y;
        }

        int result = bfs();
        System.out.println(result);
    }

    private static int bfs() {
        int start = 1;
        int[] check = new int[101];
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        check[start] = 0;

        while (true) {
            int visit = q.poll();

            for (int i = 1; i <= 6; i++) { //주사위
                int newNum = visit + i;

                if (newNum > 100) {
                    continue;
                }

                if (check[arr[newNum]] == 0) {
                    q.offer(arr[newNum]);
                    check[arr[newNum]] = check[visit] + 1;
                }
                if (arr[newNum] == 100) {
                    return check[100];
                }
            }
        }
    }
}
