import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[3];
        for(int i=0; i<3; i++) arr[i] = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]+arr[1]-arr[2]).append('\n');

        String str = String.valueOf(arr[0])+String.valueOf(arr[1]);
        sb.append(Integer.parseInt(str) - arr[2]);

        System.out.println(sb);
    }
}