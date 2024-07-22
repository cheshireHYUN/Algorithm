import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 수들의 합
 * 서로다른 N개의 자연수 합이 S고, S를 안다면 N의 최댓값은 무엇일까?
 * 풀이 : 가장 작은수들을 이용해서 더해서 S가 되는 N개를 구해야됨..
 * 즉 그냥 계속 더하다가 엥 다음수 더하면 넘네? 하기 직전수의 갯수를 말하면 되지않나~
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //S가 42억이라 Long써야됨
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long S = Long.parseLong(br.readLine());

        Long i =1L;
        Long sum = 1L;
        for(i=2L; i<S; i++){
            sum += i;
            if(sum>S) break;
        }

        System.out.println(i-1L);
    }
}