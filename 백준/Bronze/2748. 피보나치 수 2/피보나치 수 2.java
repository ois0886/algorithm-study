import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] arr = new long[91];
        int n = Integer.parseInt(br.readLine());
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= 90; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        System.out.println(arr[n]);
    }
}