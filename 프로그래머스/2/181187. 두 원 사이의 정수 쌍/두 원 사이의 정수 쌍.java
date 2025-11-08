class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        // y를 1부터 r2까지 순회 (0은 나중에 따로 처리하거나 중복 제거)
        for (int y = 1; y <= r2; y++) {
            // 큰 원에서 x의 최댓값 (소수점 내림)
            int x2 = (int) Math.sqrt((long) r2 * r2 - (long) y * y);
            
            // 작은 원에서 x의 최솟값 (소수점 올림)
            int x1 = 0;
            if (y < r1) {
                // y < r1일 때만 작은 원과 교점이 존재
                x1 = (int) Math.ceil(Math.sqrt((long) r1 * r1 - (long) y * y));
            }
            
            // 해당 y좌표에서 두 원 사이의 정수 x 개수
            answer += x2 - x1 + 1;
        }
        
        // 4사분면 대칭이므로 4배
        return answer * 4;
    }
}
