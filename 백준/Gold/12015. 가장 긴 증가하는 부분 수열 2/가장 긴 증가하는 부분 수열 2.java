import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/** 가장 긴 증가하는 부분수열 2
 * 수열 A가 주어졌을때 가장 긴 증가하는 부분수열을 구하는 프로그램을 작성하라
 * 문제는 똑같은데 수열 크기가 100만까지임
 * 원래 풀이는 이중포문 돌면서 각 i번원소값보다 작은 원소중 가장 큰 길이에 +1 해주는 방식이었음
 * 즉 O(n^2)이였고, 1000000000000 = 1조;; 1억 이하여야 하니까 불가능함
 * 효율적인 풀이는 다음과같다.
 * 결국 길이를 구하는것이니까 길이를 남기면서 뒤의 경우의수를 어떻게 탐색하는가에 대한 문제다.
 * 10 20 10 30 40 10 12 14 16 18 의 경우
 * 10 20 30 40 형태로 쭉 이어나간다. 즉, 탐색중인 값이 현재 누적값 안에 있다면 덮어쓰고 그보다 크다면 추가한다
 * 10 12 30 40 그러나 그 중간의 값이 존재하면 해당 위치를 업데이트 해준다.
 * 최대길이를 지키면서 LIS를 탐색하는 방법이라고 볼 수 있다!
 * 이때 탐색에 이분탐색을 사용하여 logN으로 계싼한다. 즉 NlogN이 되며 1900만의 시간복잡도가 나온다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] lis = new int[N]; //최대 LIS는 N개

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        //arr을 전체 반복 돌면서 현재 값을 세팅할 lis 인덱스를 찾는다
        lis[0] = arr[0];
        int len = 1;

        for(int i=1; i<N; i++){
            int value = arr[i];
            //현재 lis의 마지막 원소 값이 탐색중인 value값보다 작으면 뒤에 추가
            if(lis[len-1] < value) {
                lis[len] = value;
                len++;
            } else { //작지 않으면, 즉 lis내부의 값을 업데이트 해야하는 경우 업데이트할 위치를 찾는다
                int start = 0, end = len;
                while(start<end) {
                    //lower Bound
                    int middle = (start+end)/2;
                    if(value > lis[middle]) start = middle+1;
                    else end = middle;
                }
                lis[start] = value;
            }
        }

        System.out.println(len);
    }

}