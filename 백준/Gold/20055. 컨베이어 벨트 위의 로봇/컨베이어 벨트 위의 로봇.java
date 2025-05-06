import java.util.*;
import java.io.*;

class Main {

    static int N, K;                // N: 벨트 절반 길이, K: 내구도 0인 칸 개수 조건
    static int[] arr;              // 컨베이어 벨트 각 칸의 내구도 (2N 크기)
    static boolean[] robot;        // 로봇 존재 여부를 저장 (길이 N: 올라가는 위치 ~ 내려가는 위치)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N, K 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[2 * N];   // 0~N-1: 올라가는쪽, N~2N-1: 내려가는쪽
        robot = new boolean[N]; // 로봇이 올라가는 구간만 고려

        // 벨트 내구도 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 시뮬레이션 실행 결과 출력
        System.out.println(simulation());
    }

    // 시뮬레이션 로직
    private static int simulation() {
        int cnt = 0; // 단계 수

        // 내구도 0인 칸이 K개 이상이 될 때까지 반복
        while (isOk()) {
            rotate();         // 1. 벨트 회전
            robotMove();      // 2. 로봇 이동
            battingRobot();   // 3. 로봇 올리기
            cnt++;            // 단계 증가
        }

        return cnt;
    }

    // 벨트 및 로봇 회전
    private static void rotate(){
        // 벨트 회전 (내구도 배열 오른쪽으로 한 칸 shift)
        int tmp = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = tmp;

        // 로봇 회전 (로봇 배열도 오른쪽으로 한 칸 shift)
        for (int i = robot.length - 1; i > 0; i--) {
            robot[i] = robot[i - 1];
        }
        robot[0] = false;         // 회전 후 로봇은 무조건 0번 위치에 새로 없음
        robot[N - 1] = false;     // 내리는 위치에는 무조건 로봇이 없어야 함
    }

    // 로봇 이동
    private static void robotMove(){
        // 뒤에서부터 이동 체크 (i번 자리에 로봇이 없고 i-1에 로봇이 있으며 내구도가 1 이상일 경우 이동)
        for (int i = robot.length - 1; i > 0; i--) {
            if (robot[i - 1] && !robot[i] && arr[i] >= 1) {
                robot[i] = true;        // i번 칸으로 이동
                robot[i - 1] = false;   // i-1번 칸 비우기
                arr[i]--;              // 내구도 감소
            }
        }

        robot[N - 1] = false; // 내리는 위치에 로봇이 있으면 내림
    }

    // 로봇을 0번 위치에 올리기
    private static void battingRobot(){
        if (arr[0] > 0) {
            robot[0] = true;  // 로봇 올리기
            arr[0]--;         // 내구도 감소
        }
    }

    // 내구도 0인 칸의 개수가 K 이상인지 확인
    private static boolean isOk() {
        int cnt = 0;
        for (int j : arr) {
            if (j == 0) cnt++;
            if (cnt >= K) return false; // K개 이상이면 종료 조건 충족
        }
        return true; // 계속 진행
    }
}
