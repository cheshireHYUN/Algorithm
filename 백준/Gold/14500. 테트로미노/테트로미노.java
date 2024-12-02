import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 테트로미노
 * 폴리오미노란 크기가 1x1인 정사각형을 여러개 붙인 도형이며, 다음 조건을 만족한다.
 * (1) 정사각형은 서로 겹치면 안된다
 * (2) 도형은 모두 연결되어있어야 한다
 * (3) 정사각형의 변끼리 연결되어있어야 한다. 즉, 꼭짓점과 꼭짓점만 맞닿아 있으면 안된다.
 *
 * 정사각형 4개를 이어붙인 폴리오미노는 테트로미노라고 하며, 5가지가 있다.
 * NxM크기의 종이 위에 테트로미노 하나!!를 놓으려고 한다. 종이는 1x1크기의 칸으로 나누어져 있으며
 * 각 칸에는 정수가 하나 쓰여있다. 테트로미노 하나를 적절히 놓아서 해당 칸에 쓰인 수의 합이
 * 최대일 수 있는 프로그램을 작성하시오. 테트로미노는 회전이나 대칭을 시켜도 된다.
 *
 * 풀이
 * 처음엔 문제를 착각했는데, 테트로미노 4개를 적당히 배치해서 최대가 되는 조합을 찾는줄알았다
 * 그래서 회전/대칭이 가능하면 완전탐색이 너무 복잡해질것이라 지레 겁먹었는데.. 하나만 놓는 문제였다 ㄷㄷ
 *
 * 그럼 결국 모든 가능한 시작점에 대해서 5개의 도형을 모두 올려보면서 dfs로 최댓값을 찾으면 된다 쉽네~!
 * 다만 주의할점은 회전/대칭이 가능하다는 점이고, 'ㅜ'모양의 경우 한붓그리기가 불가능하다는 점이다.
 * 회전/대칭을 포함한 모든 도형에 대해 방향벡터를 만들어도 되지만..
 * 잘 생각해보면, 결국 방향벡터를 통해 만들수있는 길이가 4인 도형들의 전부인것이다.
 * 따라서 상하좌우로 이동하는 벡터만 만들고, 길이가 4가 됐는지만 체크해도 되는것.('ㅜ'제외)
 */
public class Main {
    static int[] dx = {1,0,0,-1};
    static int[] dy = {0,1,-1,0};
    static boolean[][] visit;
    static int[][] metrix;
    static int n,m,max,maxVal;

    static void dfs(int x, int y, int depth, int sum) {
        if(depth == 3) { //4개 모두 탐색하면 종료
            max = Math.max(max, sum);
            return;
        }

        //조건추가 -> (metrix의 최댓값)X(남은칸수)+(현재 SUM) < (현재까지 MAX)면 가망없으니까 리턴
        if(maxVal*(4-depth)+sum < max) return;

        for(int i=0; i<4; i++) {
            int nowX = x+dx[i];
            int nowY = y+dy[i];
            if(nowX<0 || nowY<0 || nowX>=n || nowY>=m || visit[nowX][nowY]) continue;
            if(depth == 1) { //'ㅜ'의 경우는 따로 처리 (ㅓ,ㅜ,ㅏ,ㅗ)
                visit[nowX][nowY] = true;
                //한 칸을 두번 이용할 수 있도록 처리함
                dfs(x,y,depth+1,sum+metrix[nowX][nowY]);
                visit[nowX][nowY] = false;
            }
            visit[nowX][nowY] = true;
            dfs(nowX,nowY,depth+1,sum+metrix[nowX][nowY]);
            visit[nowX][nowY] = false;
        }
    }

    //(i,j)에서 테트로미노 놓기를 시작
    static int pro() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                visit[i][j] = true;
                dfs(i,j,0,metrix[i][j]);
                visit[i][j] = false;
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visit = new boolean[n][m];
        metrix = new int[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                metrix[i][j] = Integer.parseInt(st.nextToken());
                maxVal = Math.max(maxVal, metrix[i][j]);
            }
        }

        System.out.println(pro());
    }
}