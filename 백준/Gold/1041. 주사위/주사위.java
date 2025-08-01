import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 주사위
 * 전개도를 접는다.
 * N과 주사위의 수가 주어질때 5개의 면에 쓰여있는 수의 합의 최솟값을 출력하는 프로그램을 작성하시오.
 *
 * 풀이
 * 3면이 보이는경우, 2면이 보이는경우. 1면이 보이는 경우로 나눌 수 있음.
 * 3면이 보이는 경우 : 위 꼭짓점 4개로 고정 => 양립할수 없는 면 중에서 작은것 선택(AF, EB, DC끼리 비교해 작은거 선택)
 * 2면이 보이는 경우 : (n-2)*4 + (n-1)*4 => 마주보지 않는 조합중 가장 작은것 선택
 * 1면이 보이는 경우 : (n-2)*(n-1)*4 + (n-2)*(n-2) => 가장 작은것 선택
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Integer.parseInt(br.readLine());
        long[] cnt = new long[3]; //각 면이 보이는 갯수를 저장
        cnt[0] = (N-2)*(N-1)*4 + (N-2)*(N-2); //1면이 보이는 경우
        cnt[1] = (N-2)*4 + (N-1)*4; //2면이 보이는 경우
        cnt[2] = 4; //3면이 보이는 경우

        int[] arr = new int[6];
        long sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<6; i++) arr[i] = Integer.parseInt(st.nextToken());

        if(N==1) { //가장큰수 제외 합
            Arrays.sort(arr);
            for(int i=0; i<5; i++) sum+=arr[i];
        } else {
            //한면만 보일때의 총합 - 정렬후 가장 작은수 찾기
            int[] copy = arr.clone();
            Arrays.sort(copy);
            sum += copy[0]*cnt[0];

            //두면만 보일때 총합 - 마주보지 않는것중 최소합 찾기
            long min = Long.MAX_VALUE;
            for(int i=0; i<6; i++) {
                for(int j=i+1; j<6; j++) {
                    if(i+j!=5) min = Math.min(min, arr[i]+arr[j]);
                }
            }
            sum += min*cnt[1];

            //세면만 보일때 총합 - 0,1,2면들과 마주보는 면을 비교해 더 작은면들만 선택
            int num = 0;
            for(int i=0; i<3; i++) num += Math.min(arr[i], arr[5-i]);
            sum += num*cnt[2];
        }

        System.out.println(sum);
    }
}
