import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/** 선긋기
 * 자의 한 점에서 다른 점까지 선을 긋는다.
 * 이미 존재하는 선에 겹쳐서 그을 수 있다.
 * 다만 여러번 그은곳과 한번 그은곳의 차이를 구별할 수 없다.
 *
 * 그려진 선들의 총 길이를 구하는 프로그램을 작성하라.
 * 풀이 : 인풋이 무려 백만이므로 그리디 같은걸로 풀어야할것같음.
 * 완탐인 배열체크나 hashset 이용은 O^2이라서 안되고,..
 * 
 * max값을 저장한 상태로 전개하는 방식은 어떨까
 * -> 정렬을 먼저 해서 시작점이 점점 커짐을 보장해두고!!
 * 1 3 에서 len=(3-1)=2, max = 3
 * 2 5 에서 2<max이므로 len+=(5-max)=2+2=4, max=5
 * 3 5 에서 3<max이므로 len+=(5-max)=4+0=4, max=5
 * 6 7 에서 6>max이므로 len+=(7-6)=4+1=5, max=7
 * 즉 len=0이고 max=0으로 셋팅해 둔 뒤,
 * 시작점<max면 이전선과 겹침 -> len+=(끝점-max), max=끝점
 * 시작점>=max면 겹치지 않음 -> len+=(끝점-시작점), max=끝점
 * (주의) 좌표가 음수일 수 있으므로, max를 0으로 두면 오류가 발생한다.
 * (주의) 시작점과 끝점이 모두 포함되는 조건을 명확히 표시한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int arr[][] = new int[N][2], max=Integer.MIN_VALUE, len=0;
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i] = new int[]{start, end};
        }

        Arrays.sort(arr, (o1, o2)-> {
           if(o1[0]==o2[0]) return o1[1]-o2[1];
           return o1[0]-o2[0];
        });

        for(int i=0; i<N; i++){
            int start = arr[i][0], end = arr[i][1];
            if(start<max) {
                if(end>max) len += end-max;
                else continue;
            }
            else len += end-start;
            max = end;
        }

        System.out.println(len);
    }
}