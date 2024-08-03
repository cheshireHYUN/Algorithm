import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 막대기
 * 막대기가 X보다 크면 반으로 자른다.
 * 잘린 반쪽이 X보다 크면 버리고 남은 절반을 반복,,
 * 잘린 반쪽이 X보다 작으면 풀로 붙여
 *
 * 반복문 돌면서 X보다 크면 반으로 계속 자르고,
 * 만약 반쪽이 X보다 작거나 같으면 X에서 빼버리고 반복한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        int stick = 64, cnt=0;
        while(X>0){
            if(stick > X){
                stick /= 2;
                continue;
            }
            X -= stick;
            cnt++;
        }
        System.out.println(cnt);
    }
}
