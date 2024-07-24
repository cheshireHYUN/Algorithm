import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/** 숫자카드 2
 * 상근이꺼N개하고 그냥M개 카드 주어졌을때 상근이가 몇개 갖고있는지 구해라
 * 이번엔 map을 이용하면 되겠다
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<N; i++){
            int key = Integer.parseInt(st.nextToken());
            int value = map.getOrDefault(key,0);
            map.put(key,value+1);
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            Integer value = map.get(Integer.parseInt(st.nextToken()));
            sb
            .append( value == null ? 0 : value)
            .append(" ");
        }


        System.out.println(sb);
    }
}