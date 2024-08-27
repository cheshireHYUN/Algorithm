import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/** 가장 긴 증가하는 부분수열
 * 수열 A가 주어졌을때, 가장 긴 증가하는 부분 수열을 구하라.
 * 풀이 : 각 원소의 위치에서 자기 이전에 자기보다 작은데 겹치진 않는 원소의 갯수를 찾는다.
 * 다만 효율적으로 찾으려면, 이전에 저장된걸 이용하는 방법을 쓴다,
 * 내가 30이라면 직전인 20까지의 갯수에 +1만 하면됨
 * {10,20,10,30,20,50}의 경우에서
 * a[0]=10에서 => 10보다 작은 원소중 길이가 가장 큰것 => 0이므로 len[0]= 0+1 = 1;
 * a[1]=20에서 => 20보다 작은 원소중 길이가 가장 큰것 => a[0]=10, len[0]=1, len[1]=1+1=2;
 * a[2]=10에서 => 10보다 작은 원소중 길이가 가장 큰것 => 0이므로 len[2]=0+1=1;
 * a[3]=30에서 => 30보다 작은 원소중 길이가 가장 큰것 => a[1]=20, len[1]=2, len[3]=2+1=3;
 * a[4]=20에서 => 20보다 작은 원소중 길이가 가장 큰것 => a[2]=10, len[2]=1, len[4]=1+1=2;
 * a[5]=50에서 => 50보다 작은 원소중 길이가 가장 큰것 => a[3]=30, len[3]=3, len[5]=3+1=4;
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] len = new int[N];
        len[0] = 1;
        int max = 0;
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            int preLen = 0;
            for(int j=0; j<i; j++) {
                if(arr[j] < arr[i]) preLen = Math.max(len[j], preLen);
            }
            len[i] = preLen+1;
            max = Math.max(max, len[i]);
        }
        System.out.println(max);
    }
}