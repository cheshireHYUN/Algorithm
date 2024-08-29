import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 개미
 * 시작시간 : 12시 5분
 * 종료시간 : 12시 46분에 풀이 세우고 구현
 *
 * 문제 :
 * Lcm의 막대위에 개미 여러마리가 있다. (속도일정, 각 1cm/s)
 * - 개미가 막대의 마지막까지 걸어가면 떨어진다.
 * - 두 개미가 만나면 방향을 반대로 바꾸어 걸어간다.
 * - 맨 처음에 개미의 위치만 주어진다(방향은 X)
 * 모든 개미가 땅으로 떨어질때까지 가능한 시간중 가장 빠른시간과 느린시간을 구해라.
 *
 * 입력 : 테케 n개, n<=10만, l<=100만
 * 풀이 : 우선 속도가 일정하므로 뒤의 개미가 앞의 개미를 따라잡을 일은 없음.
 * 즉, 방향이 서로 다른 개미들이 문제임. 근데 마주오는 개미들의 경우 뭐 따로 구현안해도 똑같은 결과
 * 우선 완전탐색을 한다고 가정하면, 즉 2^(n)의 시간복잡도 -> T*(2^10만) = 시간복잡도 초과
 * 
 * 결과적으로 
 * 가장 빠른 시간 = 중점과 가장 가까운점이 가장 가까운 출구로 나가는 길이
 * 가장 느린 시간 = 중점과 가장 먼 점이 가장 먼 출구로 나가는 길이 
 * (1)인덱스를 통해서 나가는데 걸리는 시간을 알 수 있다.
 *    빠른길 : Math.min(i, l-i), 느린길 : Math.max(i, l-i))
 * (2)중점과 가장 가까운 개미의 빠른시간, 중점과 가장 먼 개미의 느린시간을 출력
 *    개미 입력받을때 shortLenIdx와 longLenIdx를 만들어서 미리 찾는다
 *    shortLenIdx는 중점-개미위치가 이전의 len보다 작으면 갈아낀다
 *    longLenIdx는 중점-개미위치가 이전의 len보다 크면 갈아낀다
 *
 *  즉 (2)의 shortLenIdx와 longLenIdx을 구한다 (입력때 구하거나 이분탐색으로 구하거나)
 *  -> 두 인덱스가 구해졌으니 (1)의 식으로 답을 구한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()); //막대 길이
            int n = Integer.parseInt(st.nextToken()); //개미의 수

            int[] shortLenIdx = new int[]{l/2, l};
            int[] longLenIdx = new int[]{l/2, 0};
            int middle = l/2;
            if(l%2 != 0) middle++;
            for(int j=0; j<n; j++){
                int antIndex = Integer.parseInt(br.readLine());
                int value = Math.abs(middle-antIndex);
                if(value < shortLenIdx[1]) shortLenIdx = new int[]{antIndex, value};
                if(value > longLenIdx[1]) longLenIdx = new int[]{antIndex, value};
            }

            sb.append(Math.min(shortLenIdx[0], l-shortLenIdx[0]))
                    .append(' ')
                    .append(Math.max(longLenIdx[0], l-longLenIdx[0]))
                    .append('\n');
        }
        System.out.println(sb);
    }
}