import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 경로찾기
 * 가중치없는 방향그래프 G가 주어졌을때 모든 정점(i,j)에 대해 i에서 j로 가는 길이가
 * 양수인 경로가 있는지 없는지 구하는 프로그램
 * 풀이 : 모든정점에서 모든정점 => 플로이드워셜 N^3 = 1000,000 
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        StringTokenizer st;
        for(int i=0; i<N; i++){
             st = new StringTokenizer(br.readLine());
             for(int j=0; j<N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        //플로이드 워셜
        for(int k=0; k<N; k++){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(map[i][k] == 1 && map[k][j] ==1) {
                        map[i][j] = 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int[] arr : map){
            for(int i: arr) sb.append(i).append(' ');
            sb.append('\n');
        }
        System.out.println(sb);
    }
}