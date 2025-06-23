class Solution {
    public int solution(int n, int w, int num) {
        int answer = 1;
        int row = (num - 1) / w;
        int col;
        if (row % 2 == 0) {
            col = (num - 1) % w;
        } else {
            col = w - 1 - ((num - 1) % w);
        }
        
        int curRow = row + 1;
        while (true) {
            int startNum = curRow * w + 1;
            if (startNum > n) break;

            int upperNum;
            if (curRow % 2 == 0) {
                upperNum = curRow * w + col + 1;
            } else {
                upperNum = (curRow + 1) * w - col;
            }
            if (upperNum > n) break; 

            answer++;
            curRow++;
        }

        return answer;
    }
}