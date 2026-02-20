import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/** 배
 * (1) 크레인과 박스를 무게순으로 내림차순 정렬한뒤 무거운박스부터 옮긴다
 * (2) 각 크레인은 들 수 있는것중 가장 무거운 박스를 들어 옮긴다
 */
public class Main {
    static List<Integer> cranes=new ArrayList<>(), boxes=new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) cranes.add(Integer.parseInt(st.nextToken()));

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) boxes.add(Integer.parseInt(st.nextToken()));

        Collections.sort(cranes, Collections.reverseOrder());
        Collections.sort(boxes, Collections.reverseOrder());

        if(boxes.get(0)>cranes.get(0)) System.out.println(-1);
        else {
            int time = 0;
            while(!boxes.isEmpty()) {
                time++;
                for(int i=0; i<N; i++) {
                    for(int j=0; j<boxes.size(); ++j) {
                        if(cranes.get(i)>=boxes.get(j)) {
                            boxes.remove(j);
                            break;
                        }
                    }
                }
            }
            System.out.println(time);
        }
    }
}
