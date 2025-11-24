import java.util.*;

class Solution {

    // 조합 카운트를 담을 전역 맵
    Map<String, Integer> countMap;

    public String[] solution(String[] orders, int[] course) {

        List<String> answerList = new ArrayList<>();

        // 1. 각 주문을 알파벳 오름차순으로 정렬
        for (int i = 0; i < orders.length; i++) {
            char[] chars = orders[i].toCharArray();
            Arrays.sort(chars);
            orders[i] = new String(chars);
        }

        // 2. 각 코스 길이에 대해 처리
        for (int len : course) {
            countMap = new HashMap<>();

            // 각 주문에서 길이 len짜리 조합을 모두 만든다
            for (String order : orders) {
                if (order.length() < len) continue; // 길이가 안 되면 스킵
                makeComb(order.toCharArray(), 0, len, new StringBuilder());
            }

            // 이번 코스 길이(len)에 대해 가장 많이 주문된 횟수 찾기
            int maxCount = 0;
            for (int cnt : countMap.values()) {
                if (cnt >= 2) {
                    maxCount = Math.max(maxCount, cnt);
                }
            }

            // 2명 이상 주문한 조합이 없으면 패스
            if (maxCount < 2) continue;

            // 최댓값을 가진 조합들만 정답 후보에 추가
            for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
                if (entry.getValue() == maxCount) {
                    answerList.add(entry.getKey());
                }
            }
        }

        // 3. 사전 순 정렬 후 배열로 변환
        Collections.sort(answerList);
        return answerList.toArray(new String[0]);
    }

    // 조합 생성 DFS
    private void makeComb(char[] arr, int idx, int targetLen, StringBuilder sb) {
        // 원하는 길이에 도달하면 카운트
        if (sb.length() == targetLen) {
            String key = sb.toString();
            countMap.put(key, countMap.getOrDefault(key, 0) + 1);
            return;
        }

        // 더 이상 뽑을 수 없으면 종료
        if (idx == arr.length) return;

        for (int i = idx; i < arr.length; i++) {
            sb.append(arr[i]);
            makeComb(arr, i + 1, targetLen, sb);
            sb.deleteCharAt(sb.length() - 1); // 백트래킹
        }
    }
}
