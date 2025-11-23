public class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            long x = numbers[i];
            if (x % 2 == 0) {
                answer[i] = x + 1;
            } else {
                long next = x + 1;
                long diff = next & ~(next - 1);
                answer[i] = next + ((diff / 2) - 1);
            }
        }
        return answer;
    }
}
