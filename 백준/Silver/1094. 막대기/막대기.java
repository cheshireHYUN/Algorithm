import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 막대기
 * 64cm의 막대기를 Xcm가 되도록 자른다.
 * 즉, 절반씩 나누면서 길이를 비교하다가 기준값보다 크면 반 버리고,
 * 기준값 X보다 작으면 반은 막대기의 부품이 되고, 반은 계속 자르는거임
 * 따라서 부품이된만큼 X는 줄어들고, 나머지 반을 또 이전처럼 비교하며 더 작게 만듦
 * 비트마스킹으로도 풀수있대!
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        int stick = 64;
        int count = 0;

        while(x > 0){
            if(stick>x) stick /= 2; //막대기가 x보다 크면 반으로 계속 나눔
            else {
                //막대기가 x보다 작으면 목표치 x를 채우도록 (즉 빼가면서 0이되도록) 하자
                x -= stick;
                count++;
            }
        }
        System.out.println(count);
    }
}