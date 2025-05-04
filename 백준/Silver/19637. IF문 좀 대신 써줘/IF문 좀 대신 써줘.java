import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 칭호 수
        int M = Integer.parseInt(st.nextToken()); // 캐릭터 수

        Title[] titles = new Title[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int power = Integer.parseInt(st.nextToken());
            titles[i] = new Title(name, power);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int targetPower = Integer.parseInt(br.readLine());
            sb.append(binarySearch(titles, targetPower)).append("\n");
        }

        System.out.print(sb);
    }

    // 이진 탐색 함수: 가장 먼저 조건을 만족하는 칭호 반환
    static String binarySearch(Title[] titles, int power) {
        int left = 0;
        int right = titles.length - 1;
        String result = titles[right].name;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (power <= titles[mid].power) {
                result = titles[mid].name;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }
}

class Title {
    String name;
    int power;

    Title(String name, int power) {
        this.name = name;
        this.power = power;
    }
}
