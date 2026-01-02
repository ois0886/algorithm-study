import java.util.*;
import java.io.*;

public class Main {
    static final String[] NUMS = {
            "####.##.##.####",  // 0
            "..#..#..#..#..#",  // 1
            "###..#####..###",  // 2
            "###..####..####",  // 3
            "#.##.####..#..#",  // 4
            "####..###..####",  // 5
            "####..####.####",  // 6
            "###..#..#..#..#",  // 7
            "####.#####.####",  // 8
            "####.####..####"   // 9
    };

    static int n;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[5][4 * n - 1];

        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                map[i][j] = line.charAt(j);
            }
        }

        // 각 자리에 올 수 있는 숫자 리스트 저장
        List<List<Integer>> possibleNumbers = new ArrayList<>();

        for (int digitIdx = 0; digitIdx < n; digitIdx++) {
            List<Integer> possible = new ArrayList<>();
            for (int num = 0; num < 10; num++) {
                if (isValidNumber(digitIdx, num)) {
                    possible.add(num);
                }
            }

            if (possible.isEmpty()) {
                System.out.println("-1");
                return;
            }
            possibleNumbers.add(possible);
        }

        // 평균 계산
        double sum = calculateSum(possibleNumbers);
        System.out.println(sum);
    }

    static boolean isValidNumber(int digitIdx, int num) {
        int startIdx = digitIdx * 4;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 3; col++) {
                char expected = NUMS[num].charAt(row * 3 + col);
                char actual = map[row][startIdx + col];
                // 입력에 #가 있는데 숫자 패턴에 .이면 불가능
                if (actual == '#' && expected == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    static double calculateSum(List<List<Integer>> possibleNumbers) {
        // 전체 경우의 수 계산
        int totalCases = 1;
        for (List<Integer> possible : possibleNumbers) {
            totalCases *= possible.size();
        }

        double sum = 0;

        // 각 자리에 대해 합 계산
        for (int i = 0; i < n; i++) {
            int casesForOtherDigits = totalCases / possibleNumbers.get(i).size();

            for (int num : possibleNumbers.get(i)) {
                double contribution = num * Math.pow(10, n - 1 - i) * casesForOtherDigits;
                sum += contribution;
            }
        }

        return sum / totalCases;
    }
}
