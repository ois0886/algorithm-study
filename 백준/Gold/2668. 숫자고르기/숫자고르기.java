import java.util.*;
import java.io.*;

class Main {

    static ArrayList<Integer> list;
    static boolean[] visited;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        num = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        list = new ArrayList<>();
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        Collections.sort(list);
        System.out.println(list.size());
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    public static void dfs(int start, int target) {
        if (!visited[num[start]]) {
            visited[num[start]] = true;
            dfs(num[start], target);
            visited[num[start]] = false;
        }
        if (num[start] == target) list.add(target);
    }
}