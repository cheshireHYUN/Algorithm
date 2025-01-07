import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 등수 매기기
 * N명이 제출한 예상등수를 바탕으로 임의의 등수를 매긴다.
 * 예상등수가 A등인데 실제 B등일 경우, 불만도는 A와 B의 차이(|A-B|)로 수치화한다.
 * N명의 사람들의 불만도의 총합을 최소로 하면서 학생 등수를 매기자.
 *
 * 입력
 * 1<=N<=50만
 *
 * 풀이
 * 등수니까 일단 정렬을 해서 풀어야 될듯..
 * 1 5 3 1 2라고 예상등수가 주어질때,
 * 1 1 2 3 5로 정렬하고, 그대로 등수를 할당하면 가장 작은 차이가 날것임
 * 정렬하는 시간복잡도 nlogn+순회하는 시간복잡도 N
 * 
 * 주의) 입력이 50만명일경우, 모두가 최악의 예상을 했을때 50만*50만 = 2500 0000 0000 => 25억(int범위 넘음)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<N; i++) list.add(Integer.parseInt(br.readLine()));
        Collections.sort(list);
        Long cnt = 0L;
        for(int i=0; i<N; i++) cnt += Math.abs(list.get(i) - (i+1));
        System.out.println(cnt);
    }
}