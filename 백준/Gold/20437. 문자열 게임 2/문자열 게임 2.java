import java.io.*;

/*
문자열 W와 정수 K가 주어진다.
문자열에서 같은 문자가 K번 등장하는 가장 짧은/긴 연속 부분 문자열의 길이를 구하는 문제다.
전체 문자의 등장 횟수를 미리 카운팅하고, 가능한 시작 위치를 순회하며 조건을 만족하는 K번째 문자까지 탐색한다.
조건을 만족하는 부분 문자열의 길이를 업데이트하고, 최소/최대 길이를 출력한다.
 */

class Main {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 BufferedReader 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        StringBuilder sb = new StringBuilder(); // 출력 누적용

        while (T-- > 0) { // 각 테스트 케이스마다 수행
            int min = Integer.MAX_VALUE; // K개 문자를 포함한 최소 부분 문자열 길이
            int max = Integer.MIN_VALUE; // K개 문자를 포함한 최대 부분 문자열 길이

            String W = br.readLine(); // 문자열 입력
            int inputLen = W.length();
            int K = Integer.parseInt(br.readLine()); // 반복 횟수

            if (K == 1) {
                // K가 1이면, 최소/최대는 항상 1이므로 바로 출력
                sb.append("1 1").append("\n");
                continue;
            }

            // 알파벳 등장 횟수 카운트 (a~z → 인덱스 0~25)
            int[] alphaCount = new int[26];
            for (int i = 0; i < inputLen; i++) {
                char c = W.charAt(i);
                alphaCount[c - 'a']++;
            }

            // 문자열의 각 위치에서 시작해서 길이 K인 동일 문자 부분 문자열을 탐색
            for (int i = 0; i <= inputLen - K; i++) {
                char start = W.charAt(i);

                // 해당 문자가 전체적으로 K번 이상 나올 경우만 탐색
                if (alphaCount[start - 'a'] >= K) {
                    int tmpCount = 1; // 현재 같은 문자의 누적 개수

                    // i 다음부터 같은 문자가 K번 등장하는지 찾기
                    for (int j = i + 1; j < inputLen; j++) {
                        char cur = W.charAt(j);

                        if (start == cur) {
                            tmpCount++;

                            // K번째 문자 찾음
                            if (tmpCount == K) {
                                int len = (j - i) + 1;
                                min = Math.min(min, len);
                                max = Math.max(max, len);
                                break; // 해당 시작점에서 더 탐색할 필요 없음
                            }
                        }
                    }
                }
            }

            // 유효한 결과가 없을 경우 -1 출력
            if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
                sb.append("-1");
            } else {
                sb.append(min).append(" ").append(max);
            }
            sb.append("\n");
        }

        // 마지막 줄 개행 제거
        sb.deleteCharAt(sb.length() - 1);
        System.out.print(sb);
    }
}
