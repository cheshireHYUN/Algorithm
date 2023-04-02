import java.util.*;
import java.math.BigInteger;
class Solution {
    public int solution(int a, int b) {
        // 조건 : 기약분수일때 분모 소인수가 2와 5만 존재한다.
        int answer = 2;
        // 기약분수 만들기 : 두 정수의 최소공배수로 나누기 ; 라이브러리 사용하기
        BigInteger b_a = BigInteger.valueOf(a);
        BigInteger b_b = BigInteger.valueOf(b);
        int max = (b_a.gcd(b_b)).intValue();
        
        // 분모의 소인수가 2와 5만 존재하면 유한소수.. 즉 2와 5의 곱으로 표현이 가능해야함
        b /= max;
        while(b%2==0){
            b /= 2;
        }
        while(b%5==0){
            b /= 5;
        }
        if(b==1) answer = 1;
        return answer;
    }
}