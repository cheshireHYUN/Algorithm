import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/** 좌표압축
 * 수직선위 N개의 좌표에 압축을 적용한다.
 * Xi를 압축한 결과는 Xi>Xj를 만족하는 서로다른 좌표 Xj의 갯수
 * 즉 압축 = 자기보다 작은 좌표의 갯수
 * 입력 : 1<=N<=100만
 * 풀이 : 완전탐색은 당연히 안되고,
 * 정렬후 인덱스를 사용하면 어떨까
 * 2 4 -10 4 -9
 * -10 -9 2 4 4
 * 정렬한 뒤 값과 인덱스(압축값)을 HashMap에 저장하고, 원래배열 순서대로 출력
 * -> 아 인덱스를 쓰면 2번예제에서 틀린다. 0과 3이 나오기때문.. 그럼 순위를 다른 변수로 세주면 되죠?ㅋ
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);

        //뒤에서부터 put함으로써 같은수의 압축값을 자연스럽게 업데이트
        HashMap<Integer,Integer> map = new HashMap<>();
        int rank=0;
        for(int i=0; i<N; i++) {
            if(!map.containsKey(copy[i])) map.put(copy[i], rank++);
        }

        StringBuilder sb = new StringBuilder();
        for(int a : arr) sb.append(map.get(a)).append(' ');
        System.out.println(sb);
    }
}