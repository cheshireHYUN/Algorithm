import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 케빈 베이컨의 6단계법칙
 * 케빈베이컨의 수 : 자신을 제외한 나머지 사람들과의 최단거리 다 더한것
 * 한 정점에서 모든정점으로의 최단거리를 구하는 것은 다익스트라를 이용한다.
 * 모든 정점에서 모든 정점으로의 최단거리를 구하는 것은 플루이드워셜을 이용한다.
 * BFS로도 풀 수 있겠지만 일단 플루이드 워셜을 연습해보도록 하자
 * 플루이드 워셜 : 주어진 최단거리는 따로 저장해두고,
 * 이제 각 정점을 경유노선으로서 업데이트 Dac = Min(Dac, Dab+Dbc);
 *
 * 주의 : MAX여도 MAX_VLAUE를 넣으면 overflow가 나오면서 쓰레기값이 나올 수 있으니 주의하자.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //유저의 수
        int M = Integer.parseInt(st.nextToken()); //친구관계의 수

        int[][] matrix = new int[N+1][N+1];

        for(int i=1; i<=N; i++){
            Arrays.fill(matrix[i], N);
            matrix[i][i] = 0;
        }

        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            matrix[a][b] = 1;
            matrix[b][a] = 1;
        }

        System.out.println(floyd_warshall(matrix));
    }

    private static int floyd_warshall(int[][] matrix) {
        for(int mid=1; mid<matrix.length; mid++){ //경유노드
            for(int start=1; start<matrix.length; start++){ //시작노드
                for(int end=1; end<matrix.length; end++){ //목적노드
                    if(matrix[start][end] > matrix[start][mid]+matrix[mid][end]){
                        matrix[start][end] = matrix[start][mid]+matrix[mid][end];
                    }
                }
            }
        }
        
        //케빈베이컨의 수 가장 작은 사람 구하기
        int[] bacon = new int[matrix.length];
        int min = 1;
        for(int i=1; i<matrix.length; i++){ //경유노드
            for(int j=1; j<matrix.length; j++) { //시작노드
                if(i!=j) bacon[i] += matrix[i][j];
            }
            if(bacon[i] < bacon[min]) min = i;
        }

        return min;
    }
}