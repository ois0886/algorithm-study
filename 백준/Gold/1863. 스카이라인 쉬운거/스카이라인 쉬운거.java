import java.util.*;
import java.io.*;

// 백준알고리즘
 /*
 생각의 흐름
y 값이 증가하면 새 건물이 시작된 것이고,
y 값이 감소하면 이전 높이의 건물이 끝났음을 의미
y 값이 변하지 않으면 무시
스택에 현재 건물의 높이를 저장하면서, 새로운 y 값이 기존보다 낮아지면, 해당 높이의 건물이 종료되었음을 의미하므로 pop하고 count를 증가
  */

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        int count = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 사용 안 함
            int y = Integer.parseInt(st.nextToken());

            // 현재 높이보다 높은 건물이 끝나야 하므로 스택에서 제거
            while (!stack.isEmpty() && stack.peek() > y) {
                stack.pop();
                count++;
            }

            // 같은 높이이면 중복이므로 추가 안 함
            if (stack.isEmpty() || stack.peek() < y) {
                if (y != 0) stack.push(y);
            }
        }

        // 스택에 남아있는 건물도 모두 count
        count += stack.size();

        System.out.println(count);
    }
}
