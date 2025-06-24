
class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int n = schedules.length;
        int answer = 0;
        
        int[] weekday = new int[7];
        for (int i = 0; i < 7; i++) {
            weekday[i] = (startday - 1 + i) % 7 + 1;
        }

        for (int i = 0; i < n; i++) {
            boolean allOnTime = true;
            for (int j = 0; j < 7; j++) {
                if (weekday[j] >= 6) continue;

                int wish = schedules[i];
                int wishH = wish / 100;
                int wishM = wish % 100;
                wishM += 10;
                if (wishM >= 60) {
                    wishH += 1;
                    wishM -= 60;
                }
                int maxTime = wishH * 100 + wishM;

                if (timelogs[i][j] > maxTime) {
                    allOnTime = false;
                    break;
                }
            }
            if (allOnTime) answer++;
        }
        return answer;
    }
}
