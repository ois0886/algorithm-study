class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
        // l, r은 1-base이므로 0-base로 변환
        for (long i = l - 1; i < r; i++) {
            if (isOne(i)) {
                answer++;
            }
        }
        return answer;
    }
    
    private boolean isOne(long i) {
        // 가운데(인덱스 2)는 항상 0
        if (i % 5 == 2) {
            return false;
        }
        // 기본 패턴 11011 범위
        if (i < 5) {
            return true;
        }
        // 재귀: 상위 단계로 축소
        return isOne(i / 5);
    }
}
