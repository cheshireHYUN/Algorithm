import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/** 웰컴키트
 * 티셔츠 한장과 펜한자루를 세트로 나눠주려고한다. 주문을 어떻게할지 알아보자
 * 티셔츠는 6가지 사이즈가 있고, 같은사이즈의 T장묶음으로만 주문 가능
 * 펜은 한종류로, P자루씩 묶음 판매 또는 한자루씩 주문 가능
 *
 * 풀이
 * 둘쨋줄 티셔츠 사이즈의 각 갯수들을 T로 나누고, 나머지가 있다면 몫에 +1을 해준채로 누적한다.
 * N을 P로 나누고, 나머지는 그냥 출력한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[6];
        for(int i=0; i<6; i++) arr[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int cnt = 0;
        for(int i=0; i<6; i++){
            cnt += arr[i]/T;
            if(arr[i]%T != 0) cnt++;
        }
        sb.append(cnt).append('\n');
        sb.append(N/P).append(' ').append(N%P);

        System.out.println(sb);
    }
}
