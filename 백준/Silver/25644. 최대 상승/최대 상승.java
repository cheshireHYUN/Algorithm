import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 최대상승
 * N일간의 주가를 예측한다. i번째날 사고 j번째날 팔면 aj-ai만큼의 이득
 * N일간 주가가 주어졌을때 적당한시점에 사고 팔아서 얻을수있는 최대이득?
 *
 * 풀이 : DP/그리디 문제임.. 내가 젤 약한거 흑흑흑
 * 그리디로 생각해보자면 => 앞에서부터 진행하면서 가장 작은 차를 구할 수 있을것이다.
 * 무슨말이냐면 가장 작은수를 갱신하면서 입력값과의 차를 계산한다.
 * 5 30 1 10 이렇게 있으면 최댓값25에 최솟값 5, 그다음엔 최댓값 0에 최소 1만나오므로 갱신할 필요가 없다.
 * 논리적이네
 *
 * 입력값을 하나씩 처리할때마다 현재입력값과 이전의 최소입력값의 차를 정답의 최댓값으로 갱신
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        //그리디
        int benefit = Integer.MIN_VALUE;
        int min = arr[0];
        for(int i=1; i<N; i++){
            benefit = Math.max(benefit, arr[i]-min);
            min = Math.min(min, arr[i]);
        }
        if(benefit < 0) System.out.println(0);
        else System.out.println(benefit);
    }
}