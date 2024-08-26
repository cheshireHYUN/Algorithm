import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 토마토
 * 익은 토마토 근처의 토마토들은 하루마다 익는다. (사방향 + 앞뒤)
 * 보관된 토마토가 며칠이 지나면 다 익는지 최소일수를 구하라
 * (풀이) 앞뒤가 존재하므로 3차원 배열로 만들고, 익은토마토와 안익은 토마토 갯수 세서
 * 시뮬레이션 (BFS처럼) 레벨탐색 돌리면서 안익은토마토=0이면 종료하기!
 */
public class Main {
    private static int N,M,H,map[][][],green=0;
    private static boolean check[][][];
    private static List<Node> redTomatoList = new ArrayList<>();
    private static class Node {
        int x,y,z;
        public Node(int x, int y, int z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //세로칸의수 즉 열
        M = Integer.parseInt(st.nextToken()); //가로칸의 수 행
        H = Integer.parseInt(st.nextToken());
        map = new int[M][N][H];
        check = new boolean[M][N][H];

        //1은 익음, 0은 안익음, -1은 비었음
        for(int i=0; i<H; i++){
            for(int j=0; j<M; j++){
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<N; k++){
                    map[j][k][i] = Integer.parseInt(st.nextToken());
                    if(map[j][k][i] == 1)redTomatoList.add(new Node(j,k,i));
                    else if (map[j][k][i] == 0) green++;
                }
            }
        }

        //모든 토마토가 익어있는 상태
        if(green == 0) System.out.println(0);
        else System.out.println(solution());
    }

    private static int[] dx = {0,0,1,-1,0,0};
    private static int[] dy = {1,-1,0,0,0,0};
    private static int[] dz = {0,0,0,0,1,-1};
    private static int solution(){
        Queue<Node> q = new ArrayDeque<>();
        for(Node n : redTomatoList) q.add(n);
        int day = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                Node curr = q.poll();
                for(int k=0; k<6; k++){
                    int newX = curr.x+dx[k];
                    int newY = curr.y+dy[k];
                    int newZ = curr.z+dz[k];
                    if(newX<0 || newY<0 || newZ<0
                            || newX>=M || newY>=N || newZ>=H
                            || check[newX][newY][newZ]) continue;
                    if(map[newX][newY][newZ] == 0 && !check[newX][newY][newZ]) {
                        check[newX][newY][newZ] = true;
                        map[newX][newY][newZ] = 1;
                        q.add(new Node(newX,newY,newZ));
                        green --;
                    }
                }
            }
            day++;
            if(green == 0) break;
        }

        if(green == 0) return day;
        else return -1;
    }
}