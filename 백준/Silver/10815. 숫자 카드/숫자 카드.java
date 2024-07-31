import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/** 숫자카드
 * 상근이가 숫자카드 N개를 가지고있고, M개가 주어졌을때 상근이 카드중에 이 카드가 있는지 구하자
 * 풀이 : Set을 이용해서 contains()로 확인한다
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<N; i++) set.add(Integer.parseInt(st.nextToken()));

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            if(set.contains(Integer.parseInt(st.nextToken()))){
                sb.append(1).append(" ");
            } else{
                sb.append(0).append(" ");
            }
        }

        System.out.println(sb);
    }
}