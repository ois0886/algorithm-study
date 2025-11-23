import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] nums = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            nums[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(nums, (a, b) -> (b + a).compareTo(a + b));
        String result = String.join("", nums);
        // "0000..." 같은 케이스 처리
        if (result.startsWith("0")) return "0";
        return result;
    }
}