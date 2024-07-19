import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/** 그룹단어 체커
 * 풀이 : 그룹단어인지만 확인하면 되므로 Set을 활용하여 있나없나 보면될듯
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashSet<Character> set;
        int cnt = 0;
        for(int i=0; i<N; i++){
            boolean flag = true;
            set = new HashSet<>();
            char[] arr = br.readLine().toCharArray();
            set.add(arr[0]);

            for(int j=1; j<arr.length; j++) {
                if(arr[j-1] != arr[j] && set.contains(arr[j])) {
                    flag = false;
                    break;
                }
                else set.add(arr[j]);
            }
            if(flag) cnt++;
        }
        System.out.println(cnt);
    }
}