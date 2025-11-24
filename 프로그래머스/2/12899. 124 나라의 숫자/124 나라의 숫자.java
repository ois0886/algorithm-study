class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        String[] nums = {"4", "1", "2"};

        while (n > 0) {
            int r = n % 3;
            answer.append(nums[r]);
            n = (n - 1) / 3;
        }

        return answer.reverse().toString();
    }
}
