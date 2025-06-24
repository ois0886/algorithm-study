class Solution {
    public int solution(int[][] info, int n, int m) {
        boolean[][] dp = new boolean[n][m];
        dp[0][0] = true;

        for (int[] ints : info) {
            boolean[][] next = new boolean[n][m];
            for (int a = 0; a < n; a++) {
                for (int b = 0; b < m; b++) {
                    if (!dp[a][b]) continue;

                    int na = a + ints[0];
                    if (na < n) next[na][b] = true;

                    int nb = b + ints[1];
                    if (nb < m) next[a][nb] = true;
                }
            }
            dp = next;
        }

        for (int a = 0; a < n; a++) {
            for (int b = 0; b < m; b++) {
                if (dp[a][b]) {
                    return a;
                }
            }
        }
        return -1;
    }
}