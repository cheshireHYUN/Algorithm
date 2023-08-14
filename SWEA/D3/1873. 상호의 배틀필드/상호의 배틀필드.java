import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 상호의 배틀필드
 * 사용자의 전차가 존재 - 다른 전차는 없음 - 맵에서 동작
 * 업,다운,렢,라잍,포탄발사 가능
 * 포탄 발사시 벽돌이나 강철벽에 충돌하거나 맵박으로 나가진다
 * 벽에 부딪히면 폭탄 소멸, 부딪힌 벽이 벽돌로 만들어진 벽이라면 이 벽은 파괴되어 칸은 평지가됨 (강철벽은 아무일X)
 * 
 * 입력이 주어질때 모든 입력 처리하면 맵 상태 어떻게 될지 구해라
 * 
 * 
 */
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static char[][] map;
	static char[] input;
	static int H,W,N; 
	static int[][] dxy = {{0,1},{0,-1},{1,0},{-1,0}}; //오왼하상 순
	static class Node{
		int x,y;
		int direction;
		public Node(int x,int y, int d) {
			this.x = x;
			this.y = y;
			this.direction = d;
		}
		
		public Node(int x,int y, char d) {
			this.x = x;
			this.y = y;
			if(d=='>') this.direction = 0;
			else if(d=='<') this.direction = 1;
			else if(d=='v') this.direction = 2;
			else if(d=='^') this.direction = 3;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("src/algo0814/input (7).txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			for(int i=0; i<H; i++) map[i] = br.readLine().toCharArray();
			
			Node start = null;
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					if(map[i][j]=='>'||map[i][j]=='<'||map[i][j]=='v'||map[i][j]=='^') {
						start = new Node(i,j,map[i][j]);
						map[i][j] = '.';
					}
				}
			}
			
			N = Integer.parseInt(br.readLine());
			input = new char[N];
			input = br.readLine().toCharArray();
			
			solution(start);
			sb.append("#").append(tc).append(' ');
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void solution(Node currentNode) {
		//방향은 오,왼,하,상 순이다.
		for(char in : input) {
			if(in == 'U') { //바라보는 방향 위로 바꾸고 평지라면 이동
				currentNode.direction = 3;
				if(!check(currentNode,1)) continue;
				move(currentNode);
			}
			else if(in == 'D') { //바라보는 방향 아래로 바꾸고 평지라면 이동
				currentNode.direction = 2;
				if(!check(currentNode,1)) continue;
				move(currentNode);
			}
			else if(in == 'L') { //바라보는 방향 왼쪽으로 바꾸고 평지라면 이동
				currentNode.direction = 1;
				if(!check(currentNode,1)) continue;
				move(currentNode);
			}
			else if(in == 'R') { //바라보는 방향 오른쪽으로 바꾸고 평지라면 이동
				currentNode.direction = 0;
				if(!check(currentNode,1)) continue;
				move(currentNode);
			}
			else if(in == 'S') { //바라보는 방향으로 포탄 발사(강철이면 변화X, 벽돌이면 평지됨)
				shoot(currentNode);
			}
		}
		
		//탐색이 끝났을때 마지막 currentNode에 주차하자..
		parking(currentNode);
	}

	private static void parking(Node currentNode) {
		switch (currentNode.direction) {
		case 0:
			map[currentNode.x][currentNode.y] = '>';
			break;
		case 1:
			map[currentNode.x][currentNode.y] = '<';
			break;
		case 2:
			map[currentNode.x][currentNode.y] = 'v';
			break;
		case 3:
			map[currentNode.x][currentNode.y] = '^';
			break;
		}
		
	}

	private static void shoot(Node currentNode) {
		// 바라보는 방향으로 map이끝나거나 벽이 나올때까지 탐색
		int turn = 1;
		while(true) {
			if(!check(currentNode,turn)) break;
			
			if(map[currentNode.x+(dxy[currentNode.direction][0]*turn)]
					[currentNode.y+(dxy[currentNode.direction][1]*turn)]=='*') { //벽돌
				map[currentNode.x+(dxy[currentNode.direction][0]*turn)]
						[currentNode.y+(dxy[currentNode.direction][1]*turn)] = '.';
				break;
			}else if(map[currentNode.x+(dxy[currentNode.direction][0]*turn)]
					[currentNode.y+(dxy[currentNode.direction][1]*turn)]=='#') { //강철
				break;
			}
			
			turn++;
		}
		
	}

	private static boolean check(Node currentNode, int turn) {
		if(currentNode.x+(dxy[currentNode.direction][0]*turn) < 0 ||
				currentNode.y+(dxy[currentNode.direction][1]*turn)<0 ||
				currentNode.x+(dxy[currentNode.direction][0]*turn)>=H ||
				currentNode.y+(dxy[currentNode.direction][1]*turn)>=W ||
				currentNode.y+(dxy[currentNode.direction][1]*turn) == '-'
				) return false;

				
		return true;
	}
	
	private static void move(Node currentNode) {
		//평지라면 이동
		if(map[currentNode.x+dxy[currentNode.direction][0]]
				[currentNode.y+dxy[currentNode.direction][1]]=='.') {
			currentNode.x = currentNode.x+dxy[currentNode.direction][0];
			currentNode.y = currentNode.y+dxy[currentNode.direction][1];
		}
	}
}