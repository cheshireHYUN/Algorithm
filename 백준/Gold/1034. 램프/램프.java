import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 램프
 * 어떤 행의 램프가 모두 켜짐 = 행이 켜져있다.
 * 스위치를 K번 누른다(같은스위치 두번눌러도 ㄱㅊ) 누를떄마다 해당 열의 램프 상태가 전부 반대가 됨
 * 지민이의 탁자에 있는 램프의 상태와 K가 주어졌을때 스위치를 K번 누른후 켜진 행의 최댓값?
 *
 * 풀이
 * 스위치를 누르면 해당 열이 전부 반대가 된다 = 즉 각 행별로 패턴이 같은녀석들끼린 늘 똑같이 변경된다.
 * 결국 같은 패턴의 행이 몇개인지가 정답의 갯수가 되는것!
 * 단, 만약 패턴이 0001인데 K가 1이면 행이 켜지지 못하니까 (0의갯수)<=K 여야하고
 *      K-(0의갯수)=홀수라면 이 패턴은 불가능하다
 * (1) 입력된 숫자를 String 그대로 일차원배열에 담는다(이후 패턴 비교 편하게)
 *      이때 각 행별로 0의갯수가 몇개인지 체크 필요.
 * (2) 순회하면서 가능조건에 해당한다면 그때 똑같이 생긴 행이 몇개인지 찾는다(어차피 N<50이니까)
 */
public class Main {
    static int N,M,K;
    static String[] map;
    static int[] zeroCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new String[N];
        zeroCount = new int[N];

        for(int i=0; i<N; i++) {
            map[i] = br.readLine();
            for(int j=0; j<M; j++) {
                if(map[i].charAt(j) == '0') zeroCount[i]++;
            }
        }
        K = Integer.parseInt(br.readLine());

        int max = 0;
        for(int i=0; i<N; i++) {
            if(zeroCount[i]> K || (zeroCount[i]-K)%2!=0) continue;
            int samePattern = 1;
            for(int j=i+1; j<N; j++) {
                if(map[i].equals(map[j])) samePattern++;
            }
            max = Math.max(max, samePattern);
        }

        System.out.println(max);
    }
}
