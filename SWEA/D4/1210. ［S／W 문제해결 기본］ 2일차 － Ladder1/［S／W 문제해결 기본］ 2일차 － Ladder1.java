import java.util.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 사다리타기
 * 100*100크기 이차원배열로(막대:1) 주어지며, 도착점 X에 대한 출발점 찾기
 * 도착점에서 역으로 올라간다. 왼,오부터 확인하고 위로 올라감
 */
class Node{
    int x,y;
    Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Solution {
    static int[] dx = {0,0,-1}; //(0,1)(0,-1) 즉 왼,오 부터 확인 후 위로올라감 (-1,0)
    static int[] dy = {1,-1,0};
    static int[][] map = new int[100][100];
    static int answer;
    
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("src/common/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int i=1; i<=10; i++) {
            int tc = Integer.parseInt(br.readLine());
            Node destination = null;
            for(int j=0; j<100; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int k=0; k<100; k++) {
                    String token = st.nextToken();
                    if(Integer.parseInt(token)==2) destination = new Node(j,k);
                    map[j][k] = Integer.parseInt(token);
                }
            }
            // map과 destiation이 세팅되었으니 출발점을 탐색한다.
            solution(destination);
            System.out.println("#"+tc+" "+answer);
            
        }
    }
    
    private static void solution(Node node) {
        
    	while(true) {
    		if(node.x == 0) {
    			answer = node.y; //node의 행이 0일때 열값을 찾고 멈춘다.
    			break;
    		}
    		for(int i=0; i<3; i++) {
    			int nx = node.x+dx[i];
    			int ny = node.y+dy[i];
    			// 좌,우 먼저 탐색하며, 만약 사다리가 이어져있다면 이동한다
    			if(nx>=0 && ny>=0 && nx<100 && ny<100 && map[nx][ny]==1) {
    				map[node.x][node.y]=3; //방문했던곳임을 표현하기위해 3 넣어줌
    				node.x = nx; //node의 값을 갈아끼는것으로 이동을 표현
    				node.y = ny;
    			}
    		}
    	}
        
    }


}