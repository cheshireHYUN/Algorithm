import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/** 감시
 * 5가지 종류의 cctv가 있을때, 사각지대를 최소로 할 수 있도록 배치하여 최소크기를 구하자!
 * 0은 공간, 6은 벽, 1~5는 cctv이다. (cctv는 cctv를 통과하여 볼 수 있으나 벽을 통과하진 못함)
 * 
 * 1번 : 한쪽만 감시 : 경우의수(4)
 * 2번 : 두방향감시(수평으로) : 경우의수(2)
 * 3번 : 두방향감시(직각으로) : 경우의수(4)
 * 4번 : 세방향감시 :경우의수(4)
 * 5번 : 사방감시 : 경우의수(1)
 * 
 * 또한 씨씨티비는 90도씩 회전시킬 수 있다.
 *    (-1,0)
 * (0,-1)  (0,1)
 *     (1,0)
 *     
 * 회전하는 경우의 수가 필요한듯요?
 * 1번*4     (0,1)
 *             (1,0)
 *             (0,-1)
 *             (-1,0)
 * 
 * 2번*2     ((0,1),(0,-1)),
 *             ((1,0),(-1,0))
 * 
 * 3번*4 ((-1,0)(0,1))
 *         ((-1,0)(0,-1))
 *         ((0,-1)(1,0))
 *         ((1,0)(0,1))
 * 
 * 4번     ((0,-1)(1,0)(0,1))
 *         ((-1,0)(0,-1),(1,0))
 *         ((0,-1)(1,0)(0,1))
 *         ((1,0)(0,1)(-1,0))
 * 
 * 5번     ((0,1),(0,-1),(1,0),(-1,0))
 * 
 * 풀이 : 모든경우(씨씨티비 방향)에 대해 완전탐색
 * 씨씨티비가 동시에 돌아가므로 중복순열을 통해서 모든 방향에 대한 경우의 수를 찾는다.
 * 이때 방향값이 설정된 cctv는 배열에 저장. *cctv1~cctv5 순서대로 배열에 넣을것임
 * 따라서 순열 구할때 if문을 통해 몇번째의 배열에 / 몇개의 경우의수중에 선택해서 넣을것인지 선택
 * 순서가 정렬되어있으므로 뽑아서 cctv 돌릴때도 각 cctv를 순서대로 꺼내줘야함. 즉 우선순위 큐를 이용한다
 */
public class Main {
    static int[][] dxdy = {{-1,0},{0,1},{1,0},{0,-1}}; //시계방향으로 넣어둠. 순서대로 0,1,2,3
    static int[][] cctv1 = {{0},{1},{2},{3}}; //경우의 수 4가지. dxdy[cctv1[0]],dxdy[cctv1[1]],dxdy[cctv1[2]].. 이런식으로 사용
    static int[][] cctv2 = {{1,3},{2,0}};
    static int[][] cctv3 = {{0,1},{0,3},{3,2},{2,1}};
    //static int[][] cctv4 = {{3,2,1},{0,3,2},{3,2,1},{2,1,0}};
    static int[][] cctv4 = {{0,3,1},{0,3,2},{1,2,3},{2,1,0}};
    static int[][] cctv5 = {{0,1,2,3}};
    static int N,M,map[][],originMap[][],select[],c1=0,c2=0,c3=0,c4=0,c5=0,answer=Integer.MAX_VALUE;
    static PriorityQueue<Cctv> originPq = new PriorityQueue<>();
    static PriorityQueue<Cctv> cctvPq = new PriorityQueue<>();
    
    static class Cctv implements Comparable<Cctv>{
        int x,y;
        int number;
        public Cctv(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
        }
		@Override
		public int compareTo(Cctv o) {
			//number가 작은것부터, 같다면 x가 작은것부터, 같다면 y가 작은것부터
			if(o.number == this.number) {
				if(this.x == o.x) return this.y-o.y;
				return this.x-o.x;
			}
			return this.number - o.number;
		}
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        originMap = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
                originMap[i][j] = tmp;
                if(map[i][j]==1 ||map[i][j]==2 ||map[i][j]==3 ||map[i][j]==4 ||map[i][j]==5) originPq.offer(new Cctv(i, j, tmp));
                if(map[i][j]==1) c1++;
                else if(map[i][j]==2) c2++;
                else if(map[i][j]==3) c3++;
                else if(map[i][j]==4) c4++;
                else if(map[i][j]==5) c5++;
            }
        }
        
        select = new int[c1+c2+c3+c4+c5];
        getPerm(0); //방향을 정하는 중복순열 구하기
        System.out.println(answer);
        
    }
    
    //각 cctv 순서대로 몇번째 방향인덱스를 선택할건지 중복순열을 구함.
    //즉 cctv1과 cctv2가 한대씩 있을경우, 전자는 4가지 방향이 있고 후자는 2가지 방향이있다.
    //따라서 길이가 2인 배열을 만든다. 그리고 c1의 갯수만큼 방향 경우의수를 저장해주고, c2의 갯수만큼 방향 경우의수를 저장해준다
    private static void getPerm(int cnt) {
        if(cnt == c1+c2+c3+c4+c5) {
            //cctv켜기
            cctvOn();
            //사각지대 검사
            answer = Math.min(calBlindSpot(),answer);
            //맵초기화
            initializeMap();
            return;
        }
        
        if(cnt<c1) {
            for(int i=0; i<4; i++) {
                select[cnt] = i;
                getPerm(cnt+1);
            }
        }
        else if(cnt<c1+c2) {
            for(int i=0; i<2; i++) {
                select[cnt] = i;
                getPerm(cnt+1);
            }
        }
        else if(cnt<c1+c2+c3) {
            for(int i=0; i<4; i++) {
                select[cnt] = i;
                getPerm(cnt+1);
            }
        }
        else if(cnt<c1+c2+c3+c4) {
            for(int i=0; i<4; i++) {
                select[cnt] = i;
                getPerm(cnt+1);
            }
        }
        else if(cnt<c1+c2+c3+c4+c5) {
            for(int i=0; i<1; i++) {
                select[cnt] = i;
                getPerm(cnt+1);
            }
        }
        
    }
    
    //select에 방향인덱스가 셋팅되어있으므로 해당 인덱스를 꺼내서 적합한 방향벡터를 선택하도록 한다.
    //pq를 이용하므로 낮은순번의 cctv부터 진행함 -> select의 인덱스 순서와 동일
    private static void cctvOn() {
    	initializePq();
    	int index = 0;
    	while(!cctvPq.isEmpty()) {
            Cctv c = cctvPq.poll();
            switch (c.number) {
            case 1: 
                goRoad(c.x,c.y,cctv1[select[index++]]);
                break;
            case 2:
                goRoad(c.x,c.y,cctv2[select[index++]]);
                break;
            case 3:
                goRoad(c.x,c.y,cctv3[select[index++]]);
                break;
            case 4:
                goRoad(c.x,c.y,cctv4[select[index++]]);
                break;
            case 5:
                goRoad(c.x,c.y,cctv5[0]);
                index++;
                break;
            }
        }
    }

    
    //출발점과 방향인덱스가 주어지면 그 방향으로 볼수있는 구역을 체크(-1)해주는 메소드
    private static void goRoad(int x, int y, int[] index) {
        //각 방향으로 이동하며 -1로표시
        for(int in : index) {
            int turn = 1;
            while(true) {
                int nx = x+(dxdy[in][0])*turn;
                int ny = y+(dxdy[in][1])*turn;
                if(nx<0 || ny<0 || nx>=N || ny>=M || map[nx][ny]==6) break; 
                map[nx][ny] = -1;
                turn++;
            }
        }

    }


    //맵을 원본으로 초기화해주는 메소드 
    private static void initializeMap() {
        for(int i=0; i<N; i++) map[i] = originMap[i].clone();
    }
    
    //사용할 큐인 cctvPq를 원본 pq로 초기화해주는 메소드
    private static void initializePq() {
    	for(Cctv c : originPq) cctvPq.offer(c);
    }
    
    //사각지대를 계산하는 메소드
    private static int calBlindSpot() {
    	int cnt = 0;
        for(int i=0; i<N; i++) {
        	for(int j=0; j<M; j++) {
        		if(map[i][j]==0) cnt++;
        	}
        }
        return cnt;
    }
    
}