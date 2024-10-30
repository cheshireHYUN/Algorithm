import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 공유기 설치
 * 도현이의 집 N개가 수직선 위에 있고, 좌표는 X1~XN이다
 * 공유기를 C개 설치하려고 한다. 최대한 많은곳에서 와이파이를 사용하기위해
 * 집당 공유기 하나, 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치한다.
 * C개의 공유기를 N개의 집에 적당히 설치해서 가장 인접한 두 공유기 사이 거리를 최대로 하는 프로그램을 작성.
 *
 * 제한
 * 2<=N<=20만 (집)
 * 2<=C<=N (공유기)
 *
 * 풀이
 * 가장 인접한 두 공유기가 최대가 되도록 만들기 위해선 모든 거리가 최대한 떨어져 있어야한다.
 * 그렇다면 정답인 '거리' 자체를 이분탐색으로 적절히 선택했을때 현재 가능한 집 분포인지 판단한다.
 * (1) 집 분포를 오름차순 sort하고
 * (2) 정답 최대거리는 최댓값-최솟값+1 부터 탐색
 */
public class Main {
    static int N, C;
    static int[] homes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        homes = new int[N];
        for(int i=0; i<N; i++) {
            homes[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(homes);

        //이분탐색
        int lo = 1;
        int hi = homes[N-1] - homes[0] + 1;
        while(lo<hi) { //Upper Bound
            int mid = (lo+hi)/2;
            if(canInstall(mid) < C) {
                hi = mid;
            } else {
                lo = mid+1;
            }
        }

        System.out.println(lo-1); //처음으로 탐색값을 초과하는 값이므로

    }

    //distance기준으로 공유기를 설치할때 가능한 갯수를 리턴
    private static int canInstall(int distance) {
        int count = 1;
        int lastLocate = homes[0];

        for(int i=0; i< homes.length; i++) {
            int locate = homes[i];
            //현재 탐색위치와 직전 위치간 거리가 최소거리보다 크거나 같을때 공유기 설치갯수 +1, 설치위치 갱신
            if(locate - lastLocate >= distance) {
                count++;
                lastLocate = locate;
            }
        }
        return count;
    }
}