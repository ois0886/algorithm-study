import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 백준 알고리즘 11286번 절댓값 힙
public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2);

            if (abs1 == abs2) return o1 > o2 ? 1 : -1;
            return abs1 - abs2;
        });

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                if (queue.isEmpty()) sb.append("0").append('\n');
                else sb.append(queue.poll()).append('\n');
            } else {
                queue.add(num);
            }
        }
        System.out.println(sb);
    }
}
