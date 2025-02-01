import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0;
        int copyN = N;
        do {
            int left = N / 10;
            int right = N % 10;
            if(left + right >= 10){
                int plus = left + right;
                int plus_right = plus % 10;
                N = right * 10 + (plus_right);
            } else{
                N = right * 10 + (left + right);
            }

            result++;
        } while(N != copyN);

        System.out.println(result );

    }


}