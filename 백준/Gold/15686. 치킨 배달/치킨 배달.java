import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/* 치킨배달
 * 
 * 1. 집x각치킨집 배열로 각 집마다 모든 치킨집에 가는데 걸리는 거리를 저장해두고
 * 2. chickenCchicken-M, 즉 폐업시킬 조합을 구함 (부분집합 아닌이유 : M개를 최대값일때 답일것이니까!)
 * 3. 조합마다 배열을 돌면서 해당 조합의 치킨집을 제외하고 가장 작은 거리를 구해서 더한다.
 * 4. 이때 조합의 합이 가장 작을때의 치킨거리를 구한다. 
 * 
 */
public class Main {
    static int N,M,map[][],answer=Integer.MAX_VALUE;
    static Node[][] map2;
    static List<int[]> homelist = new ArrayList<>();
    static List<int[]> chickenlist = new ArrayList<>();
    static int[][] selectDelChicken2;
    
    static class Node{
    	int x,y,len;
    	public Node(int x,int y, int len) {
    		this.x = x;
    		this.y = y;
    		this.len = len;
    	}
    	
    	@Override
    		public String toString() {
    			// TODO Auto-generated method stub
    			return "("+x+","+y+","+len+")";
    		}
    }
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //N*N배열
        M = Integer.parseInt(st.nextToken()); // M개의 치킨집을 선택해 유지하기

        
        int home=0, chicken=0;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
            	int num = Integer.parseInt(st.nextToken());
                if(num == 2) {
                	chicken++;
                	int[] tmp = {i,j};
                	chickenlist.add(tmp);
                }
                if(num == 1) {
                	int[] tmp = {i,j};
                	homelist.add(tmp);
                	home++;
                }
            }
        }
        
        // 0행 0열 = 0번집에서 0번치킨집까지의 거리, 0행1열 = 0번집에서 1번치킨집까지 거리.. 모두 저장
        map = new int[home][chicken];
        map2 = new Node[home][chicken];
        
        // 세로부터 채워야함
        for(int j=0; j<chickenlist.size(); j++) {
        	for(int i=0 ; i<homelist.size(); i++) {
                map2[i][j] = new Node(chickenlist.get(j)[0],chickenlist.get(j)[1],
                		Math.abs(homelist.get(i)[0]-chickenlist.get(j)[0])
                		+ Math.abs(homelist.get(i)[1]-chickenlist.get(j)[1]));
            }
        }
        
        selectDelChicken2 = new int[chickenlist.size()-M][2];
        // 없애버릴 치킨집 찾기 - 치킨C치킨-M
        solution(0,0);
        System.out.println(answer);
    }

	private static void solution(int cnt, int start) {
		if(cnt==chickenlist.size()-M) {
			deleteChicken();
			
			return;
		}
		
		for(int i=start; i<chickenlist.size(); i++) {
			int[] tmp = {chickenlist.get(i)[0],chickenlist.get(i)[1]};
			selectDelChicken2[cnt] = tmp;
			solution(cnt+1, i+1);
		}
	}
	 
	//selectedChiken의 치킨집을 제외하고 가장 작은 거리를 구해서 더한다.
	private static void deleteChicken() {
		int dist = 0;
		for(int i=0; i<map2.length; i++) {
			int min = Integer.MAX_VALUE;
			for(int j=0; j<map2[0].length; j++) {
				boolean flag = false;
				//조합중 하나일경우 경로에 더하지 않는다
				for(int[] a : selectDelChicken2) {
					if(map2[i][j].x==a[0] && map2[i][j].y==a[1]) flag = true;
				}
				//즉 map2[i][j]가 폐업하지 않는 치킨집이다. 그럼 그때 가장 가까운 치킨거리만 남김
				if(!flag) {
					min = Math.min(min, map2[i][j].len);
				}
			}
			dist += min;
		}
		
		answer = Math.min(answer, dist);
	}

}