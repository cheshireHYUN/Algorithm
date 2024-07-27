import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;

/** 올바른 배열
 * 어떤 배열속에 있는 원소 중 5개가 연속적인것(정렬시 1씩 차이)을 말한다.
 * 배열이 주어지면 이 배열이 올바른 배열이 되기위해 추가되어야할
 * 원소의 최소 갯수를 구해라
 * 배열의 크기는 50이하이고, 원소값은 10억 이하이다.
 *
 * 풀이 : 일단 정렬을 해야겠죠. 그리고 부분합은 투포인터를 쓰는게 유리하다
 * (1) 올바른 배열이 되려면 길이가5인 겹치지 않는 배열의 맨 끝의 차가 4이다.
 * (2) 해당 배열중에서 내가 가진 숫자의 갯수만큼 뺀다.
 *  => 애초에 최소갯수를 구해야하므로, 내가 가진 자원중에서 길이가 5인배열을 만드는 수들을 찾는다.
 *
 * 즉 내가 가진 정렬된 숫자 리스트에서 차가 4가 되는 인덱스를 찾는다.
 * 예제 2의 경우, get(2)-get(0)=4이다.
 * 그렇다면 어떤 연속수열에서, 양쪽 끝이 리스트의 0번과 2번에 들어가있고, 사이인 1번도 있을것이다.
 * 즉, 5개의 숫자중 0,1,2번 인덱스의 값들이 들어있다. 추가할건 단 2개라는 결론이 나온다! (5-(end-start+1))
 * 다만 그 차가 4일때만 계산하는건 아니고, 4이하에서 모두 계산해야된다. 예제1처럼 567만 있을수도 있으니까~!
 *
 * 그런데 이제 이 과정에 투포인터를 이용하면 된다. start와 end를 같이 출발시키고,
 * 차가 4이하일때까진 end를 늘리고, 차가 4이상이 되면 다시 start를 늘린다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        int start=0, end=0, result=4;
        while(start<=end){
            if(end >= N) break;
            if(arr[end] - arr[start] < 5){
                result = Math.min(result, 5-(end-start+1));
                end++;
            }else start++;
        }
        System.out.println(result);
    }
}