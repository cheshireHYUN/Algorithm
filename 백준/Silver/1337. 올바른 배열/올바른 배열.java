import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 올바른 배열
 * 어떤 배열속에 있는 원소중 5개가 연속적인것을 올바른 배열이라고 함
 * 올바른 배열이 되게 하기 위해 추가되어야 할 원소의 갯수의 최솟값을 출력하라.
 * 풀이 : 연속하는 부분합을 구하는 문제는 투포인터를 사용하면 O(N)으로 끝낼 수 있다.
 * 이 문제에서 빈곳을 채울 경우의수를 고민하면 너무 어렵다. 쉽게생각하는 방법은 다음과 같다
 * 포인터는 내가 가진 숫자배열 위에서 돈다.
 * (1) list에 넣어둔 자연수들중에서 그 차가 4이하인 것들을 찾는다. 그럼 1~5처럼 5개의 자연수 범위를 포함하게 된다.
 * (2) 이중에서 내가 채워넣을 갯수만 세는것! 즉, start와 End가 각각 0과 3이라면, 그래서 get(0)=1, get(3)=5였던거라면
 * (3) 해당 범위에서 get(0)~get(3)값은 내가 갖고있다는거다. End-start+1개를 이미 가지고있다. 숫자 단 하나만 더 필요함~!
 * 4이하에서만 실행하는 이유는, list의 자연수 사이 길이가 4가 아닌 경우들이 있다. 맨뒤에 두개를 더붙이는 방식으로 연속수열을 만들기 떄문이다.
 * 즉, 그냥 해당 범위에서 존재하는 수를 5에서 빼주는 개념인거다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<N; i++) list.add(Integer.parseInt(br.readLine()));
        Collections.sort(list);
        int startPointer = 0, endPointer = 0, result = 4;
        while(true){
            if(endPointer >= N) break; //범위밖으로 벗어나면 종료
            //두 값의 거리가 4가 될때까진 즉 연속된수가 5이하일때까진 endPointer를 증가시킴
            if(list.get(endPointer)-list.get(startPointer) < 5){
                result = Math.min(result, 5-(1+endPointer-startPointer)); //길이가 정확히 4일때만 갱신하진 않음. 맨뒤에 붙일수도 있으니까!
                endPointer++;
            }else startPointer++;
        }
        System.out.println(result);
    }
}