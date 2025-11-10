import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;

        // 1. 사용 가능한 전체 곡괭이 수 계산
        int totalPicks = picks[0] + picks[1] + picks[2];

        // 2. 광물을 5개씩 그룹으로 나누고, 각 그룹의 피로도 계산
        List<Group> groups = new ArrayList<>();

        for(int i = 0; i < minerals.length; i += 5){
            // 곡괭이가 부족하면 더 이상 캘 수 없음
            if(groups.size() >= totalPicks) break;

            int dia = 0, iron = 0, stone = 0;

            // 5개씩 묶어서 각 광물 개수 세기
            for(int j = i; j < Math.min(i + 5, minerals.length); j++){
                if(minerals[j].equals("diamond")){
                    dia++;
                } else if(minerals[j].equals("iron")){
                    iron++;
                } else {
                    stone++;
                }
            }

            // 각 곡괭이로 캤을 때 피로도 계산
            int diaFatigue = dia + iron + stone;
            int ironFatigue = dia * 5 + iron + stone;
            int stoneFatigue = dia * 25 + iron * 5 + stone;

            groups.add(new Group(diaFatigue, ironFatigue, stoneFatigue));
        }

        // 3. 돌 곡괭이 피로도 기준으로 내림차순 정렬 (어려운 구간 먼저)
        groups.sort((a, b) -> b.stoneFatigue - a.stoneFatigue);

        // 4. 좋은 곡괭이부터 순서대로 배정
        int pickIdx = 0; // 0: 다이아, 1: 철, 2: 돌

        for(Group group : groups){
            // 사용 가능한 곡괭이 찾기
            while(pickIdx < 3 && picks[pickIdx] == 0){
                pickIdx++;
            }

            // 곡괭이에 따라 피로도 추가
            if(pickIdx == 0){
                answer += group.diaFatigue;
                picks[0]--;
            } else if(pickIdx == 1){
                answer += group.ironFatigue;
                picks[1]--;
            } else {
                answer += group.stoneFatigue;
                picks[2]--;
            }
        }

        return answer;
    }

    static class Group {
        int diaFatigue;
        int ironFatigue;
        int stoneFatigue;

        Group(int dia, int iron, int stone){
            this.diaFatigue = dia;
            this.ironFatigue = iron;
            this.stoneFatigue = stone;
        }
    }
}
