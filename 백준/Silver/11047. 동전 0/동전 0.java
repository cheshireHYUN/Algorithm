import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 동전 0
 * 준규가 가진 동전은 N종류이다.
 * 적절히 골라서 그 가치의 합을 K로 만드는데 필요한 동전 갯수의 최솟값
 * 입력 : 1<=N<=10, 1<=K<=1억
 * 풀이 : 딱봐도 그리디 큰 동전으로 만드는게 이득 (동전의 가치가 배수관계이기에 ㄱㅊ)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Integer[] arr = new Integer[N];
        for (int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr, (o1, o2) -> o2 - o1);
        int cnt = 0;
        for(int coin : arr){
            cnt += K/coin;
            K %= coin;
        }
        System.out.println(cnt);

    }
}