class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while (storey > 0) {
            int digit = storey % 10;  // 현재 자릿수
            int nextDigit = (storey / 10) % 10;  // 다음 자릿수
            
            if (digit < 5) {
                // 내림: 현재 자릿수만큼 빼기
                answer += digit;
                storey /= 10;
            } else if (digit > 5) {
                // 올림: (10 - digit)만큼 더해서 올림
                answer += (10 - digit);
                storey = storey / 10 + 1;  // 다음 자릿수에 1 추가
            } else {  // digit == 5
                answer += 5;
                if (nextDigit >= 5) {
                    // 다음 자릿수가 5 이상이면 올림이 유리
                    storey = storey / 10 + 1;
                } else {
                    // 다음 자릿수가 5 미만이면 내림이 유리
                    storey /= 10;
                }
            }
        }
        
        return answer;
    }
}
