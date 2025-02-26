import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int num = Integer.parseInt(br.readLine());
        int[] A = new int[num];
        Integer[] B = new Integer[num];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(A); // A는 오름차순 정렬
        Arrays.sort(B, Collections.reverseOrder()); // B는 내림차순 정렬
        
        int sum = 0;
        for (int i = 0; i < num; i++) {
            sum += A[i] * B[i];
        }
        
        sb.append(sum);
        System.out.println(sb);
    }
}