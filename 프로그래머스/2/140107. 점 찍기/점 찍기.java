class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        long dSquared = (long) d * d;  // 오버플로우 방지
        
        // x좌표를 0부터 d까지 k씩 증가
        for (long x = 0; x <= d; x += k) {
            // 피타고라스 정리: y^2 = d^2 - x^2
            long maxYSquared = dSquared - (x * x);
            long maxY = (long) Math.sqrt(maxYSquared);
            
            // y도 k의 배수만 가능, y=0도 포함하므로 +1
            answer += (maxY / k) + 1;
        }
        
        return answer;
    }
}
