import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 골드바흐의 추측
 * 소수 : 1보다 큰 자연수중 1과 자기자신을 제외한 약수가 없는 자연수
 * 골드바흐의 추측 : 2보다 큰 모든 짝수는 두 소수의 합으로 나타낼 수 있다.
 * 그리고 짝수를 두 소수의 합으로 나타내는 표현을 골드바흐 파티션이라고 한다.
 * n이 주어졌을때 골드바흐 파티션을 출력하라. 여러가지면 두 소수의 차이가 가장 작은걸 출력해라.
 * 입력 : 4<=N<=10000
 * 풀이 : 완전탐색은 말도 안되고, 소수니까 에라토스테네스의 체를 이용해 소수배열을 찾는다,
 * 두 소수차가 작은것을 출력 -> 짝수n을 절반으로 나눈 뒤 각자 얼마씩 차이나는지 찾는다.
 * 에라토스테네스의 체 : 2의배수지우고 3의배수 지우고 ... 루트n까지 반복
 * 소수는 2,3,5,7,11,13,17,19,23,29 ...
 * 4 = 2+2
 * 6 = 3+3
 * 8 = 3+5
 * 10 = 3+7, 5+5
 * 12 = 5+7
 * 14 = 3+11, 7+7
 * 16 = 3+13, 5+11
 */
public class Main {
    static boolean[] prime = new boolean[10001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        setPrime();
        for(int t=0; t<T; t++){
            int n = Integer.parseInt(br.readLine());
            int left = n/2, right = n/2;
            while(true){
                if(!prime[left] && !prime[right]){
                    sb.append(left).append(' ').append(right).append('\n');
                    break;
                }
                left--;
                right++;
            }
        }
        System.out.println(sb);
    }
    static void setPrime(){
        prime[0] = (prime[1] = true);
        for(int i=2; i< Math.sqrt(prime.length); i++){
            if(prime[i]) continue;
            for(int j=i*i; j< prime.length; j=j+i){
                prime[j] = true;
            }
        }
    }
}