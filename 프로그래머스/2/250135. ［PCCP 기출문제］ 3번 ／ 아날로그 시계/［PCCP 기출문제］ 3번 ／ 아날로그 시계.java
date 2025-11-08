class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        
        // 시작 시간과 끝 시간을 초 단위로 변환
        int startTime = h1 * 3600 + m1 * 60 + s1;
        int endTime = h2 * 3600 + m2 * 60 + s2;
        
        // 시작 시간이 0시 또는 12시 정각인 경우 미리 카운트
        if (startTime == 0 || startTime == 12 * 3600) {
            answer++;
        }
        
        // 1초씩 증가하며 겹침 확인
        while (startTime < endTime) {
            // 각 바늘의 현재 각도 (초 기준)
            double hCurAngle = (startTime / 120.0) % 360;    // 시침
            double mCurAngle = (startTime / 10.0) % 360;     // 분침
            double sCurAngle = (startTime * 6.0) % 360;      // 초침
            
            // 1초 후 각 바늘의 각도
            double hNextAngle = ((startTime + 1) / 120.0) % 360;
            double mNextAngle = ((startTime + 1) / 10.0) % 360;
            double sNextAngle = ((startTime + 1) * 6.0) % 360;
            
            // 360도일 때 0이 아닌 360으로 처리
            if (hNextAngle == 0) hNextAngle = 360;
            if (mNextAngle == 0) mNextAngle = 360;
            if (sNextAngle == 0) sNextAngle = 360;
            
            // 초침이 시침을 지나쳤는지 확인
            if (sCurAngle < hCurAngle && sNextAngle >= hNextAngle) {
                answer++;
            }
            
            // 초침이 분침을 지나쳤는지 확인
            if (sCurAngle < mCurAngle && sNextAngle >= mNextAngle) {
                answer++;
            }
            
            // 시침, 분침, 초침이 모두 겹친 경우 중복 제거
            if (sNextAngle == hNextAngle && hNextAngle == mNextAngle) {
                answer--;
            }
            
            startTime++;
        }
        
        return answer;
    }
}
