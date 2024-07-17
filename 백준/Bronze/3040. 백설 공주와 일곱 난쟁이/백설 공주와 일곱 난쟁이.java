import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/** 백설공주와 일곱난쟁이
 * 각 난쟁이 모자에 100보다 작은 정수가 적혀있고, 그 합이 100이다
 * 이제 진짜 난쟁이 일곱명만 찾아보자. 즉 아홉개 수중 합이 100이 되는 일곱개 수를 찾자
 * 풀이 : 조합으로 찾는게 가장 먼저 떠오르네요..
 */
public class Main {
    static int[] arr = new int[9];
    static int[] comb = new int[7];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<9; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        getCombination(0,0);
    }

    private static void getCombination(int cnt, int start) {
        if(cnt == 7){
            int sum = 0;
            //현재 조합에서 모든 난쟁이 합이 100이하인지 찾기
            for(int i : comb) sum += i;
            if(sum == 100) {
                for(int c : comb) System.out.println(c);
            }

            return;
        }

        for(int i=start; i<9; i++){
            comb[cnt] = arr[i];
            getCombination(cnt+1, i+1);
        }
    }
}