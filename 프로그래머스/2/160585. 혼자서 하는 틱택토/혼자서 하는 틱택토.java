// 1. O와 X의 갯수가 1 차이여야함
// 2. 가로, 세로, 대각선이 3개면 -> 나머지는 무조건 2개
// 3. 나머지가 둘다 0이면 이어야함

import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int solution(String[] board) {
        // 1. O와 X 개수 세기
        int oCount = 0, xCount = 0;
        for (String row : board) {
            for (char c : row.toCharArray()) {
                if (c == 'O') oCount++;
                else if (c == 'X') xCount++;
            }
        }

        // 2. 개수 검증: O는 X와 같거나 1개 많아야 함
        if (oCount < xCount || oCount > xCount + 1) {
            return 0;
        }

        // 3. 승리 여부 확인
        boolean oWin = isWinner(board, 'O');
        boolean xWin = isWinner(board, 'X');

        // 4. 승리 상황 검증
        // 둘 다 이길 수 없음
        if (oWin && xWin) return 0;

        // O가 이겼는데 X를 더 두었다면 무효
        if (oWin && oCount != xCount + 1) return 0;

        // X가 이겼는데 O를 더 두었다면 무효
        if (xWin && oCount != xCount) return 0;

        return 1;
    }

    // 승리 여부를 체크하는 헬퍼 함수
    private boolean isWinner(String[] board, char player) {
        // 가로 3줄 체크
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == player &&
                    board[i].charAt(1) == player &&
                    board[i].charAt(2) == player) {
                return true;
            }
        }

        // 세로 3줄 체크
        for (int j = 0; j < 3; j++) {
            if (board[0].charAt(j) == player &&
                    board[1].charAt(j) == player &&
                    board[2].charAt(j) == player) {
                return true;
            }
        }

        // 대각선 체크
        if (board[0].charAt(0) == player &&
                board[1].charAt(1) == player &&
                board[2].charAt(2) == player) {
            return true;
        }

        // 반대 대각선 체크
        if (board[0].charAt(2) == player &&
                board[1].charAt(1) == player &&
                board[2].charAt(0) == player) {
            return true;
        }

        return false;
    }
}
