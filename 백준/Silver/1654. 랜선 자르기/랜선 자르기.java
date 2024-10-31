import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 랜선자르기
 * 캠프때 쓸 N개의 랜선을 만든다.
 * 초기에 길이가 제각각인 K개의 랜선이 있고, N개의 모두 같은 길이로 만들기 위해 잘라서 버린다
 * 즉 300cm짜리 랜선에서 140cm짜리 랜선을 두개 잘라내면 20cm는 버린다.
 * N개 이상을 만들어도 된다. 최대 랜선의 길이를 구하는 프로그램을 작성하라.
 *
 * 제한
 * 1<=K<=1만
 * 1<=N<=100만
 *
 * 풀이
 * 최대길이를 만드려면 N개 이상중 N개 일때가 당연히 가장 길것임
 * 예제는 4개의 랜선(802,743,457,539)이 주어졌을때 11개 이상의 랜선을 만드려고 한다.
 *
 * 그렇다면 임의의 길이 X를 만들고 해당 길이로 N개를 만들수있는지 확인하면 될듯?
 * (1) 즉 임의의 길이 X는 이분탐색으로 결정하고, (2) N개를 만들수있는지 유효성 검사를 한다.
 * 모두 같은 길이를 만들기 위해 가장 큰 길이 = 가장 작은 랜선의 길이
 * 모두 같은 길이를 만들기 위해 가장 작은 길이 = 1
 * N개를 만드는 여러개 나올 수 있으므로 그중 가장 최대길이를 찾으려면 Upper바운드를 찾고 -1하면 될듯
 *
 * (주의)
 * 만약 주어진 랜선이 {1,1}일경우, 첫 mid가 (1+0)/2=0이 되어 counting에서 에러를 뱉는다.
 *
 */
public class Main {
    static int[] lenArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); //갖고있는 랜선
        int N = Integer.parseInt(st.nextToken()); //만드려는 랜선의 갯수
        lenArr = new int[K];
        long hi = 0;
        for(int i=0; i<K; i++) {
            lenArr[i] = Integer.parseInt(br.readLine());
            hi = Math.max(hi, lenArr[i]);
        }
        hi++;

        long lo = 0;
        while(lo<hi) {
            long mid = (lo+hi)/2;
            //System.out.println(mid+"를 기준으로 자르면 총 "+sliceAndCounting(mid)+"개의 랜선이 나옴");
            //크기와 길이는 반비례함. 즉 카운팅 갯수가 N보다 크면 길이를 늘려야됨 = lo가 커져야됨
            //카운팅 갯수가 N보다 작으면 길이를 줄여야됨 = hi가 줄어야됨
            //내가 찾는거 = UpperBound즉 N을 초과하는 부분 = 이부분은 mid값을 넣어줘야함
            if(sliceAndCounting(mid) < N) hi=mid;
            else lo = mid+1;
        }
        System.out.println(lo-1);
    }

    //targetLength길이로 잘랐을때 나오는 랜선의 갯수를 리턴
    private static long sliceAndCounting(long targetLength) {
        long cnt = 0;
        for(int len : lenArr) {
            cnt += len/targetLength;
        }
        return cnt;
    }
}