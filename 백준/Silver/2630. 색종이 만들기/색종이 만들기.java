import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 색종이
 * 풀이 : 너무나도 분할정복
 */
public class Main {
    static int N, map[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(0,0,N);
        System.out.println(white);
        System.out.println(color);
    }

    static int white=0, color=0;
    private static void solution(int start, int end, int length){
        int sum = 0;
        for(int i=start; i<start+length; i++){
            for(int j=end; j<end+length; j++) {
                sum += map[i][j];
            }
        }

        if(sum == 0) white++;
        else if(sum == length*length) color++;
        else{
            solution(start, end,length/2);
            solution(start+length/2, end,length/2);
            solution(start, end+length/2,length/2);
            solution(start+length/2, end+length/2,length/2);
        }
    }
}