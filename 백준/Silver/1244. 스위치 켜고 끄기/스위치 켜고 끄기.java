import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 스위치 켜고 끄기
 * 1번부터 연속적으로 번호가 붙어있는 스위치가 켜져있거나 꺼져있다.
 * 1은 켜져있고, 0은 꺼져있는 상태이다. 학생 몇명을 뽑은 뒤
 * 1이상이고 스위치갯수 이하인 자연수를 하나씩 나눠주었다.
 * 학생들은 자신의 성별과 받은 수에 따라 스위치를 조작하게 된다
 * 
 * 남학생 : 스위치번호가 자기가 받은수의 배수면 상태를 바꾼다.
 * (EX) 남학생이 3을 받았다면 3번, 6번 스위치를 바꿈
 * 
 * 여학생 : 자기가 받은 수와 같은번호가 붙은 스위치를 중심으로 
 * 좌우대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아 모두 바꾼다(이때 구간의 스위치 갯수는 항상 홀수)
 * (EX) 0 1 1 1 0 일때 3번 스위치를 받으면 3번기준으로 모두 대칭이므로 5개 전부 바꿈. 만약 없다면 자기자신만 바꿈
 * 
 * 입력 : 스위치 상태, 학생의 성별과 받은 숫자 (남:1 여:2)
 * 출력 : 1번 스위치에서 시작하여 마지막까지 한줄에 20개씩 출력한다. 예를들어 21번 스위치가 있다면
 * 이 스위치는 1번의 맨 앞에 출력하는것임. 
 *
 */
public class Main {
	static int[] sArr;
	static int[][] hArr;
	
	private static void solution() {
		for(int i=0; i<hArr.length; i++) {
			switch (hArr[i][0]) {
			case 1:
				boys(hArr[i][1]);
				break;
			case 2:
				girls(hArr[i][1]);
				break;
			}
		}
	}
	
	 // 스위치 번호가 자기가 받은 수의 배수면 상태를 바꿈
	private static void boys(int num) {
		// 주의 : 스위치는 1번부터 시작함. 
		for(int i=0; i<sArr.length; i++) {
			if((i+1)%num==0) sArr[i] = (sArr[i]== 0? 1:0);
		}
	}

	// 자기가 받은 수와 같은번호가 붙은 스위치를 중심으로 좌우대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아 모두 바꾼다
	private static void girls(int num) {
		//일단 자기자신 바꿈
		sArr[num-1] = (sArr[num-1] == 0? 1:0);
		// 좌우대칭을 확인..
		for(int i=1; i<sArr.length; i++) {
			if((num-1)+i < 0 || (num-1)-i < 0 || 
					(num-1)+i >= sArr.length || (num-1)-i >= sArr.length) break; //범위를 벗어나면 멈춰줘야함
			
			if(sArr[(num-1)+i] == sArr[(num-1)-i]) {
				//num번의 배열값, 즉 num-1을 기준으로 좌우가 대칭이면 바꾼다
				sArr[(num-1)+i] = (sArr[(num-1)+i] == 0? 1:0);
				sArr[(num-1)-i] = sArr[(num-1)+i];
			}else {
				return; //대칭아니면 바로 멈춤
			}
		}
	}


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int switchCnt = Integer.parseInt(br.readLine());
		sArr = new int[switchCnt];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<switchCnt; i++) sArr[i] = Integer.parseInt(st.nextToken());
		
		int humanCnt = Integer.parseInt(br.readLine());
		
		hArr = new int[humanCnt][2];
		for(int i=0; i<humanCnt; i++) {
			st = new StringTokenizer(br.readLine());
			hArr[i][0] = Integer.parseInt(st.nextToken());
			hArr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		solution();
		for(int i=1, cnt=1; i<=sArr.length; i++) {
			System.out.print(sArr[i-1]+" ");
			if(cnt%20 == 0) System.out.println();
			cnt++;
		}
	}

}