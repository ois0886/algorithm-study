import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        // 1. 시간을 분 단위로 변환하여 배열에 저장
        int[][] times = new int[book_time.length][2];
        for (int i = 0; i < book_time.length; i++) {
            times[i][0] = timeToMinute(book_time[i][0]); // 입실 시간
            times[i][1] = timeToMinute(book_time[i][1]) + 10; // 퇴실 시간 + 청소 10분
        }
        
        // 2. 입실 시간 기준으로 정렬
        Arrays.sort(times, (a, b) -> a[0] - b[0]);
        
        // 3. 우선순위 큐로 각 객실의 퇴실 시간(청소 완료 시간) 관리
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        
        for (int[] time : times) {
            // 가장 빨리 비는 객실이 현재 입실 시간보다 이전이면 재사용
            if (!rooms.isEmpty() && rooms.peek() <= time[0]) {
                rooms.poll(); // 해당 객실 제거
            }
            // 현재 예약의 퇴실 시간(청소 완료 시간) 추가
            rooms.offer(time[1]);
        }
        
        // 4. 큐의 크기가 필요한 최소 객실 수
        return rooms.size();
    }
    
    private int timeToMinute(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}
