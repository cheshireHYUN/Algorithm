import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 어두운 굴다리
 * 길은 0~N까지, 가로등 갯수는 M 가로등위치는 x, 가로등의 높이(H)x2만큼 길을 비출수있음
 * 최소한의 높이로 굴다리를 밝혀보자!
 * 풀이 : 완전탐색? 근데 연산을 좀 줄여보자면
 * 일단 최솟값은 양쪽끝점에서 양끝가로등까지 길이가 될듯.
 * 그리고 애초에 입력받을때 가로등사이의 길이를 재면 되지않나? 그중에서 최댓값을 찾으면 답 나오지!
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //굴다리 길이
        int M = Integer.parseInt(br.readLine()); //가로등 갯수
        StringTokenizer st = new StringTokenizer(br.readLine()); //M개의 위치
        int len = 0;
        int[] arr = new int[M];
        for(int i=0; i<M; i++){
            //양 끝점의 경우
            arr[i] = Integer.parseInt(st.nextToken());
            if(M==1) {
                len = Math.max(N-arr[i], arr[i]);
                break;
            }
            if(i==0) {
                len = Math.max(len, arr[i]);
                continue;
            }
            else if(i==M-1) {
                len = Math.max(len, N-arr[i]);
            }
            //중간지점의 경우 양쪽에서 비추는 길이를 고려한다.
            len = Math.max(len, Math.round((float) (arr[i] - arr[i - 1]) /2));
        }
        System.out.println(len);
    }
}