import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

/** 알파벳 찾기
 * 처음 등장하는 위치 / 없으면 -1
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        int[] arr = new int[26];
        Arrays.fill(arr, -1);
        for(int i=0; i<str.length(); i++) arr[str.charAt(i)-97] = str.indexOf(str.charAt(i));
        for(int a : arr) sb.append(a).append(' ');
        System.out.println(sb);
    }
}