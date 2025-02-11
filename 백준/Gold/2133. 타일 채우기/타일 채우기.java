import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[31];
        arr[0] = 1;
        arr[2] = 3;
        for (int i = 4; i < 31; i++) {
            arr[i] = arr[i - 2] * arr[2];
            for (int j = 4; j <= i; j++) {
                arr[i] += arr[i - j] * 2;
                j++;
            }
            i++;
        }
        System.out.println(arr[N]);
    }

}