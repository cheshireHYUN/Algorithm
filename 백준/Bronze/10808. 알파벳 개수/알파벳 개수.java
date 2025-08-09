import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
        int[] count = new int[26];
        for(char c : arr) count[c-97]++;

        StringBuilder sb = new StringBuilder();
        for(int c : count) sb.append(c).append(' ');

        System.out.println(sb);
    }
}
