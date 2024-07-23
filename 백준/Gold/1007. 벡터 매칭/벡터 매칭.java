import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 벡터매칭
 * 평면에 N개점이있고 집합P라고 부른다.
 * 두점을 연결해서 벡터를 만들며, 벡터는 이 점갯수의 절반이다.
 * 벡터매칭(각점끼리 연결조합을 만듦)에 있는 벡터 합의 최솟값을 출력하라.
 * 풀이 : 조합을 통해 출발점과 도착점을 만들 수 있다. 출발점 조합만 구하면 도착점 조합은 그외겠죠~~
 * 공식 : V1(x1,y2)->(x2,y2)=>(x2-x1,y2-y1),
 * 벡터합 => Math.sqrt(Math.pow((x2+x4-x1-x3,2)) + Math.pow((y2+y4-y1-y3,2)))
 * 시간복잡도 : nCr이니까 20C10, 20!/10!(20-10)! = 18만
 */
public class Main {
    static int[][] arr;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<T; t++){
            N = Integer.parseInt(br.readLine()); //점의 갯수
            arr = new int[N][2];
            selected = new boolean[N];
            min = Integer.MAX_VALUE;
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }
            solution(0,0);
            sb.append(min).append("\n");
        }
        System.out.println(sb);
    }

    //도착점 조합을 구한 뒤 Math.sqrt(Math.pow((x2+x4-x1-x3,2)) + Math.pow((y2+y4-y1-y3,2)))
    static boolean[] selected;
    static double min;
    private static void solution(int cnt, int start){
        if(cnt == N/2){
            int plusX=0, plusY=0, minusX=0, minusY=0;
            for(int i=0; i<N; i++){
                if(selected[i]) {
                    plusX += arr[i][0];
                    plusY += arr[i][1];
                } else{
                    minusX += arr[i][0];
                    minusY += arr[i][1];
                }
            }
            double sum = Math.sqrt(
                    Math.pow((plusX-minusX),2)
                    + Math.pow((plusY-minusY),2)
            );

            min = Math.min(min,sum);
            return;
        }
        for(int i=start; i<N; i++){
            selected[i] = true;
            solution(cnt+1, i+1);
            selected[i] = false;
        }
    }

}