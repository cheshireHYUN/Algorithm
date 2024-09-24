import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 곱셈
 * 자연수 A를 B번 곱한 수를 알고싶다.
 * 구하려는 수가 매우 커질 수 있으므로 이를 C로 나눈 나머지를 구하는 프로그램을 작성
 *
 * 입력 : A,B,C는 모두 21억 이하의 자연수
 * 출력 : A를 B번곱한수를 C로 나눈 나머지, 0.5초
 *
 * 풀이 : 분할정복과 모듈러 연산을 이용한문제!!! 접근이 어려웠따!!
 * 우선 지수가 21억이라는거 부터가 그냥 풀면 안됨 -> 지수법칙을 이용해서 연산을 확 줄인다
 * 예를들어 A^8 = (A^4)^2이다. 홀수일경우는 A^9=(A^4)^2*A
 * 이렇게 반으로 나누는 방식을 통해 A^4만 구하더라도 전체 결과를 알 수 있게 된다. (재귀로 구현)
 *
 * 그리고 두번째로 고려해야 하는점이 바로 나누기 연산의 출력이다.
 * temp*temp는 범위안에 있겠지만, temp*temp*A는 결국 21억을 곱하므로
 * long범위를 넘어갈 수 있다. 그래서 재귀 안쪽부터 모듈러연산을 통해 바르게 계산해준다.
 */
public class Main {
    public static long C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());
        System.out.println(pow(A,B));
    }
    private static long pow(long A, long exp) {
        //지수가 1일경우 A를 그대로 리턴
        if(exp == 1) return A%C;

        long temp = pow(A,exp/2);

        if(exp%2 == 1) {
            //(temp*temp*A)%C => (temp*temp%C)(A%C)%C
            return ((temp*temp%C)*(A%C))%C;
        }
        return temp*temp%C;
    }
}