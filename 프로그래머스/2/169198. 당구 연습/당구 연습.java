class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        for (int i = 0; i < balls.length; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];
            int minDist = Integer.MAX_VALUE;
            
            // 4개 벽에 대해 거리 계산 (예외 조건 체크)
            // 아래 벽
            if (!(startX == targetX && startY > targetY)) {
                minDist = Math.min(minDist, distSq(startX, startY, targetX, -targetY));
            }
            // 왼쪽 벽
            if (!(startY == targetY && startX > targetX)) {
                minDist = Math.min(minDist, distSq(startX, startY, -targetX, targetY));
            }
            // 오른쪽 벽
            if (!(startY == targetY && startX < targetX)) {
                minDist = Math.min(minDist, distSq(startX, startY, 2*m - targetX, targetY));
            }
            // 위 벽
            if (!(startX == targetX && startY < targetY)) {
                minDist = Math.min(minDist, distSq(startX, startY, targetX, 2*n - targetY));
            }
            
            answer[i] = minDist;
        }
        return answer;
    }
    
    private int distSq(int x1, int y1, int x2, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
}
