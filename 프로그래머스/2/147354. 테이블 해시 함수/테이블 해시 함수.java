import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Arrays.sort(data, (a,b) ->{
            if(a[col -1] == b[col -1]){
                return b[0] - a[0];
            }
            return a[col - 1] - b[col - 1];
        });

        for(int i = row_begin; i<=row_end; i++){
            int s_i = 0;
            for(int j = 0; j < data[i-1].length;j++){
                s_i += data[i-1][j] % i;
            }
            answer^=s_i;
        }
        return answer;
    }
}