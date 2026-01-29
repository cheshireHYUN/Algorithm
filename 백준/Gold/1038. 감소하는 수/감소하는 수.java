import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 감소하는 수는 결국 중복수가 들어갈수없다.
 * 따라서 총 0~9까지 10개 숫자를 중복하지않고 선택하는 조합의 문제로 생각 가능.
 * 2^10 = 1024-1 = 1023개 경우의수(+공집합 제외)
 *
 * 힌자릿수(0~9)부터 시작해서 현재숫자의 마지막 자리보다 작은숫자만 뒤에 붙이며 재귀(DFS)
 */
public class Main {
    static List<Long> numbers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N<10) System.out.println(N);
        else if(N>=1023) System.out.println(-1);
        else {
            for(int i=0; i<10; i++) getComb(1,i);
            Collections.sort(numbers);
            System.out.println(numbers.get(N));
        }
    }

    private static void getComb(int idx, long num) {
        if(idx > 10) return;
        numbers.add(num);
        for(int i=0; i<num%10; i++) {//현재숫자의 가장 마지막자리보다 작아야 감소하는 수..
            getComb(idx+1, num*10+i);
        }
    }
}