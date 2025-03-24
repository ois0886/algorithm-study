import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준알고리즘 1068번 트리

public class Main {
    static int N, deleteNum;

    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        int root = -1;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == -1) {
                root = i;
                continue;
            }
            list[num].add(i);
        }

        int deleteNum = Integer.parseInt(br.readLine());

        DFS(deleteNum);
        if (deleteNum == root) {
            System.out.println(0);
        } else {
            System.out.println(BFS(root));
        }

    }

    static void DFS(int node) {
        if (list[node].size() > 0) {
            int size = list[node].size();
            while (size > 0) {
                int child = list[node].get(--size);
                DFS(child);
            }
        }

        for (int i = 0; i < N; i++) {
            if (list[i].contains(node)) {
                for (int j = 0; j < list[i].size(); j++) {
                    if (list[i].get(j) == node) {
                        list[i].remove(j);
                    }
                }
            }
        }
    }

    static int BFS(int node) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        int cnt = 0;

        while (!q.isEmpty()) {
            int pos = q.poll();
            if (list[pos].size() == 0) {
                cnt++;
                continue;
            }
            for (int next : list[pos]) {
                q.add(next);
            }
        }
        return cnt;
    }
}
