import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;

//가장 많이 사용된 알파벳 구하기
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        str = str.toUpperCase();
        char[] arr = str.toCharArray();

        int[] cnt = new int[100];
        for(char c : arr) cnt[c] += 1;
        int[] copy = Arrays.copyOf(cnt, cnt.length);

        Arrays.sort(cnt);
        if(cnt[99] == cnt[98]) System.out.println("?");
        else {
            for(int i=0; i<100; i++) {
                if(copy[i] == cnt[99]) {
                    System.out.println((char)i);
                    break;
                }
            }
        }

    }
}