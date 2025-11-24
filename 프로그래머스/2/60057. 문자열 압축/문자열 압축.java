class Solution {
    public int solution(String s) {
        int n = s.length();
        // 길이가 1이면 그대로 1
        if (n == 1) return 1;

        int answer = n; // 압축 안 했을 때 길이로 초기화

        // 자르는 단위를 1 ~ n/2 까지 전부 시도
        for (int cut = 1; cut <= n / 2; cut++) {
            StringBuilder compressed = new StringBuilder();

            String prev = s.substring(0, cut); // 이전 조각
            int count = 1;

            int idx;
            for (idx = cut; idx + cut <= n; idx += cut) {
                String cur = s.substring(idx, idx + cut);

                if (cur.equals(prev)) {
                    count++;
                } else {
                    // 지금까지의 prev를 압축 문자열에 추가
                    if (count > 1) compressed.append(count);
                    compressed.append(prev);

                    // 새 조각으로 초기화
                    prev = cur;
                    count = 1;
                }
            }

            // 마지막 prev 처리
            if (count > 1) compressed.append(count);
            compressed.append(prev);

            // 남은 꼬리 문자열(딱 안 나누어 떨어진 부분) 붙이기
            if (idx < n) {
                compressed.append(s.substring(idx));
            }

            // 가장 짧은 길이 갱신
            answer = Math.min(answer, compressed.length());
        }

        return answer;
    }
}
