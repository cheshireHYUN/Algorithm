import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 게임
 * 게임횟수 X, 이긴게임 Y(Z%)일때 Z는 형택이의 승률이고 소수점은 버린다.
 * X와 Y사 주어졌을때 게임을 최소 몇번 더해야 Z가 변하는지 구하라
 * Z가 절대 변하지 않으면 -1을 출력한다
 *
 * 풀이
 * 전체 게임 횟수가 10억이므로 max=10억, min=0으로 셋팅하고 이분탐색으로 Z가 변하는 ans를 찾는다.
 * 계산할땐 result=(int)(((double)Y+mid/X+mid)*100)이고 result!=Z이면 그때가 ans임
 * 가장 작은값을 구해야함
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int Z = (int)(((long)Y*100/X));
        int mid, ans=Integer.MAX_VALUE, max=1000000000, min=0;
        while(min<=max) {
            mid = (min+max)/2;
            int result=(int)((((long)(Y+mid))*100/(X+mid)));
            if(Z!=result) {
                ans = Math.min(ans, mid);
                max = mid-1;
            } else min = mid+1;
        }

        if(ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }
}
