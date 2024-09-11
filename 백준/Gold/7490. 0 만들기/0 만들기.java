import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** 0 만들기
 * 1부터 N까지 수를 오름차순으로 쓴 수열 1,2,3,...N
 * 그리고 +나-또는 공백을 사이에 입력한다, (각 더하기, 빼기, 숫자이어붙이기)
 * 수식의 계산결과가 0이되는 수식들을 찾는 프로그램을 작성하라.
 * (풀이) N은 최대 9, 순열구하고 백트래킹..? 어차피 정렬된 결과니까 그냥 백트래킹!
 * 리스트를 통해 숫자 이어붙이기가 완료된 구조를 만들고, 결과를 계산한다.
 * 그와중에 빼기의 아스키코드가 45니까 결과 이상 방지를 위해 1000,1001,1002로 대체해 품
 * (시간복잡도) 문자가 들어갈 위치는 N-1개, 그리고 문자는 총 세가지 => 3^(n-1) = 6561
 */
public class Main {
    static int N;
    static List<String> result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            N = Integer.parseInt(br.readLine());
            result = new ArrayList<>();
            //1~N이 주어진 수열을 백트래킹 하며 결과가 0인걸 찾는다.
            arr = new int[2*N-1];
            arr[0] = 1;
            backtracking(0, 1);
            Collections.sort(result);
            for(String str : result) sb.append(str).append('\n');
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static int[] arr;
    private static void backtracking(int index, int num){
        if(index == 2*N-2) {
            List<Integer> list = new ArrayList<>();
            for(int i=0; i<arr.length; i++){
                if(arr[i] == 1002)  {
                    String str = String.valueOf(list.get(list.size()-1))+String.valueOf(arr[i+1]);
                    list.remove(list.size()-1);
                    list.add(Integer.parseInt(str));
                    i++;
                } else list.add(arr[i]);
            }
            int sum = list.get(0);
            for(int i=1; i<list.size(); i++){
                if(list.get(i) == 1000) sum += list.get(i+1);
                else if(list.get(i) == 1001) sum -= list.get(i+1);
            }
            if(sum == 0) {
                StringBuilder sb = new StringBuilder();
                for(int a : arr) {
                    if(a==1000) sb.append('+');
                    else if(a==1001) sb.append('-');
                    else if(a==1002) sb.append(' ');
                    else sb.append(a);
                }
                result.add(String.valueOf(sb));
            }
            return;
        }

        arr[index+2] = num+1;
        arr[index+1] = 1000;
        backtracking(index+2, num+1);

        arr[index+1] = 1001;
        backtracking(index+2, num+1);

        arr[index+1] = 1002;
        backtracking(index+2, num+1);
    }
}