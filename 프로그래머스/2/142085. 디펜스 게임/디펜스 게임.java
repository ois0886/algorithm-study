import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        // k가 enemy 길이보다 크거나 같으면 모든 라운드를 무적권으로 처리 가능
        if (k >= enemy.length) {
            return enemy.length;
        }

        // 최대 힙 (가장 큰 값을 꺼내기 위해)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int answer = 0;

        for (int i = 0; i < enemy.length; i++) {
            n -= enemy[i];  // 일단 병사로 막음
            maxHeap.offer(enemy[i]);  // 힙에 기록

            // 병사가 부족해진 경우
            if (n < 0) {
                if (k > 0) {
                    // 지금까지 중 가장 많은 적을 무적권으로 대체
                    n += maxHeap.poll();
                    k--;
                } else {
                    // 무적권도 없으면 종료
                    return i;
                }
            }

            answer++;
        }

        return answer;  // 모든 라운드를 통과한 경우
    }
}
